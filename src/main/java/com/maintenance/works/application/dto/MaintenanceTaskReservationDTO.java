package com.maintenance.works.application.dto;

import com.maintenance.common.application.dto.BusinessPeriodDTO;
import com.maintenance.inventory.application.dto.PlantInventoryItemDTO;
import com.maintenance.inventory.domain.model.PlantReservation;
import com.maintenance.works.domain.model.TypeOfWork;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

/**
 * Created by gkgranada on 25/05/2017.
 */
@Data
public class MaintenanceTaskReservationDTO {
    PlantInventoryItemDTO plant;
    String description;
    BusinessPeriodDTO schedule;
    String type_of_work;
    BigDecimal price;
}