package com.maintenance.inventory.domain.model;

import com.maintenance.common.domain.model.BusinessPeriod;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class PlantReservation {
    @Id
    String id;

    @ManyToOne
    PlantInventoryItem plant;

    @Embedded
    BusinessPeriod schedule;

    public void changeReservedPlant(PlantInventoryItem plant){
        this.plant = plant;
    }
}
