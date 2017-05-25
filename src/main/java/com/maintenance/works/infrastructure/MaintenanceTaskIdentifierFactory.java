package com.maintenance.works.infrastructure;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MaintenanceTaskIdentifierFactory {
    public String nextMaintenanceTaskId() {
        return UUID.randomUUID().toString();
    }
}
