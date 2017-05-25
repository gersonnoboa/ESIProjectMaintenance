package com.maintenance.inventory.domain.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Oleksandr on 3/21/2017.
 */
@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class PlantInventoryItem {
    @Id
    String _id;
    String serialNumber;

    @Enumerated(EnumType.STRING)
    EquipmentCondition equipmentCondition;
    @ManyToOne
    PlantInventoryEntry plantInfo;

    @Enumerated(EnumType.STRING)
    PlantInventoryItemStatus plantStatus;
}