package com.maintenance.inventory.application.service;

import com.maintenance.inventory.application.dto.PlantInventoryEntryDTO;
import com.maintenance.inventory.domain.model.PlantInventoryEntry;
import com.maintenance.inventory.domain.repository.PlantInventoryEntryRepository;
import com.maintenance.works.web.controller.MaintenanceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class PlantInventoryEntryAssembler extends ResourceAssemblerSupport<PlantInventoryEntry, PlantInventoryEntryDTO> {

    @Autowired
    PlantInventoryEntryRepository repository;

    public PlantInventoryEntryAssembler() {
        super(MaintenanceController.class, PlantInventoryEntryDTO.class);
    }
    public PlantInventoryEntryDTO toResource(PlantInventoryEntry plantInventoryEntry) {
        PlantInventoryEntryDTO dto = createResourceWithId(plantInventoryEntry.getId(), plantInventoryEntry);
        dto.set_id(plantInventoryEntry.getId());
        dto.setName(plantInventoryEntry.getName());
        dto.setDescription(plantInventoryEntry.getDescription());
        dto.setPrice(plantInventoryEntry.getPrice());
        return dto;
    }

    public  PlantInventoryEntry toResource(PlantInventoryEntryDTO dto){
        PlantInventoryEntry entry = PlantInventoryEntry.of(dto.get_id(), dto.getName(), dto.getDescription(), dto.getPrice());
        return entry;

    }
}
