package com.maintenance.inventory.application.dto;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

/**
 * Created by Gerson Noboa on 17/3/2017.
 */
@Data
public class PlantInventoryItemDTO extends ResourceSupport {
    String _id;
    String name;
    String description;
    BigDecimal price;
    String plant_href;
}
