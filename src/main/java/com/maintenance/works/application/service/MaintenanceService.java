package com.maintenance.works.application.service;

import com.maintenance.common.domain.model.BusinessPeriod;
import com.maintenance.inventory.application.dto.PlantInventoryItemDTO;
import com.maintenance.inventory.application.service.PlantInventoryEntryAssembler;
import com.maintenance.inventory.application.service.PlantInventoryItemAssembler;
import com.maintenance.inventory.domain.model.EquipmentCondition;
import com.maintenance.inventory.domain.model.PlantInventoryEntry;
import com.maintenance.inventory.domain.model.PlantInventoryItem;
import com.maintenance.inventory.domain.model.PlantReservation;
import com.maintenance.inventory.domain.repository.PlantInventoryEntryRepository;
import com.maintenance.inventory.domain.repository.PlantInventoryItemRepository;
import com.maintenance.inventory.domain.repository.PlantReservationRepository;
import com.maintenance.inventory.infrastructure.PlantReservationIdentifierFactory;
import com.maintenance.works.application.dto.MaintenanceTaskDTO;
import com.maintenance.works.application.dto.MaintenanceTaskReservationDTO;
import com.maintenance.works.domain.model.MaintenanceTask;
import com.maintenance.works.domain.model.TypeOfWork;
import com.maintenance.works.domain.repository.MaintenanceTaskRepository;
import com.maintenance.works.infrastructure.MaintenanceTaskIdentifierFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gkgranada on 24/05/2017.
 */
@Service
public class MaintenanceService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MaintenanceTaskIdentifierFactory taskIdentifierFactory;

    @Autowired
    PlantReservationIdentifierFactory reservationIdentifierFactory;

    @Autowired
    PlantInventoryItemAssembler itemAssembler;

    @Autowired
    PlantInventoryEntryAssembler entryAssembler;

    @Autowired
    MaintenanceTaskRepository taskRepository;

    @Autowired
    PlantReservationRepository reservationRepository;

    @Autowired
    PlantInventoryEntryRepository entryRepository;

    @Autowired
    PlantInventoryItemRepository itemRepository;

    @Autowired
    MaintenanceTaskAssembler taskAssembler;


    @PostConstruct
    private void setUpAuth() {
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("user2", "user2"));
    }

    // inventory domain
    public List<PlantInventoryItemDTO> findReturnedPlants() {

        PlantInventoryItemDTO[] plants = restTemplate.getForObject(
                "http://localhost:8080/api/inventory/plants/items",
                PlantInventoryItemDTO[].class);
        System.out.println("Plants: " + Arrays.asList(plants));

        return Arrays.asList(plants);
    }

    public MaintenanceTaskDTO createMaintenanceTask(MaintenanceTaskReservationDTO dto) {
        System.out.println("dto: "+dto);

        PlantInventoryEntry entry = entryAssembler.toResource(dto.getPlant().getPlantInfo());
        PlantInventoryItem item = PlantInventoryItem.of(
                dto.getPlant().get_id(),
                dto.getPlant().getSerialNumber(),
                EquipmentCondition.UNSERVICEABLE_REPAIRABLE,
                entry,
                dto.getPlant().getPlantStatus()
        );


        PlantReservation plantReservation = PlantReservation.of(
                reservationIdentifierFactory.nextPlantReservationId(),
                item,
                BusinessPeriod.of(dto.getSchedule().getStartDate(),dto.getSchedule().getEndDate())
        );

        MaintenanceTask task = MaintenanceTask.of(
                taskIdentifierFactory.nextMaintenanceTaskId(),
                dto.getDescription(),
                TypeOfWork.valueOf(dto.getType_of_work()),
                dto.getPrice(),
                plantReservation
        );

        //change equipmentcondition of plant
        restTemplate.postForObject(
                "http://localhost:8080/api/inventory/plants/{id}/returned/maintenance",
                dto.getPlant().get_id(),PlantInventoryItemDTO.class,dto.getPlant().get_id());

        entryRepository.save(entry);
        itemRepository.save(item);
        reservationRepository.save(plantReservation);
        //save maintenancetask
        return taskAssembler.toResource(taskRepository.save(task));
    }
}
