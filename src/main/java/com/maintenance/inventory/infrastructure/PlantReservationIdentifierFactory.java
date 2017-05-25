package com.maintenance.inventory.infrastructure;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlantReservationIdentifierFactory {
    public String nextPlantReservationId() {
        return UUID.randomUUID().toString();
    }
}
