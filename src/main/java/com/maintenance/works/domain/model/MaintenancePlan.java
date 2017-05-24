package com.maintenance.works.domain.model;

import com.maintenance.common.domain.model.BusinessPeriod;
import com.maintenance.inventory.domain.model.PlantInventoryItem;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Oleksandr on 2/20/2017.
 */
@Entity
@Getter

public class MaintenancePlan {
    @Id
    String id;

    Integer year_of_action;

    @Embedded
    BusinessPeriod period;

    @ManyToOne
    PlantInventoryItem plant;

    @OneToMany(cascade={CascadeType.ALL})
    List<MaintenanceTask> task;

    protected MaintenancePlan() {}

    public static MaintenancePlan of(String id, Integer year, BusinessPeriod period, PlantInventoryItem plant){
        MaintenancePlan plan = new MaintenancePlan();
        plan.id = id;
        plan.year_of_action = year;
        plan.period = period;
        plan.plant = plant;

        return plan;
    }
}
