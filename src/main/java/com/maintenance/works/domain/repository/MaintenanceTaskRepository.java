package com.maintenance.works.domain.repository;

import com.maintenance.inventory.domain.model.EquipmentCondition;
import com.maintenance.inventory.domain.model.PlantInventoryEntry;
import com.maintenance.inventory.domain.model.PlantInventoryItem;
import com.maintenance.works.domain.model.MaintenanceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gkgranada on 25/05/2017.
 */
@Repository
public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, String> {
}