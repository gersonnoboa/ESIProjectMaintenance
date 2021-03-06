package com.maintenance.inventory.application.dto;

import com.maintenance.inventory.domain.model.EquipmentCondition;
import com.maintenance.inventory.domain.model.PlantInventoryItemStatus;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

/**
 * Created by Gerson Noboa on 17/3/2017.
 */
@Data
public class PlantInventoryItemDTO extends ResourceSupport {
    String _id;
    String serialNumber;
    EquipmentCondition equipmentCondition;
    PlantInventoryEntryDTO plantInfo;
    PlantInventoryItemStatus plantStatus;
}
