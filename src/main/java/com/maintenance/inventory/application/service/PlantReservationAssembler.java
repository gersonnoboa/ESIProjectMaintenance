package com.maintenance.inventory.application.service;

import com.maintenance.common.application.dto.BusinessPeriodDTO;
import com.maintenance.inventory.application.dto.PlantReservationDTO;
import com.maintenance.inventory.domain.model.PlantReservation;
import com.maintenance.works.web.controller.MaintenanceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

/**
 * Created by gkgranada on 25/05/2017.
 */
@Service
public class PlantReservationAssembler extends ResourceAssemblerSupport<PlantReservation, PlantReservationDTO> {

    @Autowired
    PlantInventoryItemAssembler itemAssembler;

    public PlantReservationAssembler() {
        super(MaintenanceController.class, PlantReservationDTO.class);
    }

    public PlantReservationDTO toResource (PlantReservation reservation) {
        BusinessPeriodDTO periodDTO = new BusinessPeriodDTO();
        periodDTO.setStartDate(reservation.getSchedule().getStartDate());
        periodDTO.setEndDate(reservation.getSchedule().getEndDate());

        PlantReservationDTO dto = new PlantReservationDTO();
        dto.set_id(reservation.get_id());
        dto.setPlant(itemAssembler.toResource(reservation.getPlant()));
        dto.setSchedule(periodDTO);

        return dto;
    }
}
