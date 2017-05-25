package com.maintenance.inventory.application.dto;

import com.maintenance.common.application.dto.BusinessPeriodDTO;
import com.maintenance.common.domain.model.BusinessPeriod;
import com.maintenance.inventory.domain.model.PlantInventoryItem;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by gkgranada on 25/05/2017.
 */
@Data
public class PlantReservationDTO extends ResourceSupport {

    String _id;
    PlantInventoryItemDTO plant;
    BusinessPeriodDTO schedule;
}
