package com.maintenance.inventory.domain.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by Oleksandr on 3/21/2017.
 */
@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class PlantInventoryEntry {
    @Id
    String id;

    String name;
    String description;
    BigDecimal price;
}
