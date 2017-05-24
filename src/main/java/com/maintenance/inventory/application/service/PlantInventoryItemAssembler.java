package com.maintenance.inventory.application.service;

import com.maintenance.inventory.application.dto.PlantInventoryItemDTO;
import com.maintenance.inventory.domain.model.PlantInventoryItem;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

/**
 * Created by gkgranada on 22/03/2017.
 */
//@Service
//public class PlantInventoryItemAssembler extends ResourceAssemblerSupport<PlantInventoryItem, PlantInventoryItemDTO> {
//
//    public PlantInventoryItemAssembler() {
//        super(ProcurementRestController.class, PlantInventoryItemDTO.class);
//    }
//
//    public PlantInventoryItemDTO toResource(PlantInventoryItem plantInventoryItem) {
//        PlantInventoryItemDTO dto = new PlantInventoryItemDTO();
//        dto.setPlant_href(plantInventoryItem.getPlant_href());
//        dto.setName(plantInventoryItem.getName());
//
//        return dto;
//    }
//
//    public PlantInventoryItem toResource(PlantInventoryItemDTO plant){
//        PlantInventoryItem item = PlantInventoryItem.of(plant.getName(), plant.getPlant_href());
//        return item;
//    }
//
//}