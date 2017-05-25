package com.maintenance.inventory.application.service;

import com.maintenance.inventory.application.dto.PlantInventoryItemDTO;
import com.maintenance.inventory.domain.model.PlantInventoryItem;
import com.maintenance.inventory.domain.repository.PlantInventoryItemRepository;
import com.maintenance.works.web.controller.MaintenanceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

/**
 * Created by gkgranada on 25/05/2017.
 */
@Service
public class PlantInventoryItemAssembler extends ResourceAssemblerSupport<PlantInventoryItem, PlantInventoryItemDTO> {

    @Autowired
    PlantInventoryItemRepository repository;

    @Autowired
    PlantInventoryEntryAssembler plantInventoryEntryAssembler;

    public PlantInventoryItemAssembler() {
        super(MaintenanceController.class, PlantInventoryItemDTO.class);
    }
    public PlantInventoryItemDTO toResource(PlantInventoryItem plantInventoryItem) {
        PlantInventoryItemDTO dto = createResourceWithId(plantInventoryItem.get_id(), plantInventoryItem);
        dto.set_id(plantInventoryItem.get_id());
        dto.setEquipmentCondition(plantInventoryItem.getEquipmentCondition());
        dto.setPlantInfo(plantInventoryEntryAssembler.toResource(plantInventoryItem.getPlantInfo()));
        dto.setPlantStatus(plantInventoryItem.getPlantStatus());
        dto.setSerialNumber(plantInventoryItem.getSerialNumber());
        return dto;
    }

    public  PlantInventoryItem toResource(PlantInventoryItemDTO dto){
        PlantInventoryItem entry = PlantInventoryItem.of(
                dto.get_id(),
                dto.getSerialNumber(),
                dto.getEquipmentCondition(),
                plantInventoryEntryAssembler.toResource(dto.getPlantInfo()),
                dto.getPlantStatus());
        return entry;

    }
}
