package com.maintenance.works.application.service;

import com.maintenance.inventory.application.service.PlantReservationAssembler;
import com.maintenance.works.application.dto.MaintenanceTaskDTO;
import com.maintenance.works.domain.model.MaintenanceTask;
import com.maintenance.works.web.controller.MaintenanceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

/**
 * Created by gkgranada on 25/05/2017.
 */
@Service
public class MaintenanceTaskAssembler extends ResourceAssemblerSupport<MaintenanceTask, MaintenanceTaskDTO> {

    @Autowired
    PlantReservationAssembler reservationAssembler;

    public MaintenanceTaskAssembler() {
        super(MaintenanceController.class, MaintenanceTaskDTO.class);
    }

    public MaintenanceTaskDTO toResource(MaintenanceTask task) {
        MaintenanceTaskDTO dto = createResourceWithId(task.get_id(), task);
        dto.setDescription(task.getDescription());
        dto.setPrice(task.getPrice());
        dto.setReservation(reservationAssembler.toResource(task.getReservation()));
        dto.setType_of_work(task.getType_of_work());
        return dto;
    }
}
