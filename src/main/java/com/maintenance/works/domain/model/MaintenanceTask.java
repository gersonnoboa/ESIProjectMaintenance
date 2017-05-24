package com.maintenance.works.domain.model;

/**
 * Created by Oleksandr on 2/20/2017.
 */


import com.example.reservations.domain.model.PlantReservation;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter

public class MaintenanceTask {

    @Id
    String id;

    String description;

    @Enumerated(EnumType.STRING)
    TypeOfWork type_of_work;

    @Column(precision=8,scale=2)
    BigDecimal price;

   // @Embedded
    //BusinessPeriod rentalPeriod;

    @ManyToOne
    PlantReservation reservation;

    protected MaintenanceTask() {}

    public static MaintenanceTask of(String id, String description, TypeOfWork typeOfWork, BigDecimal price, PlantReservation reservation){
        MaintenanceTask task = new MaintenanceTask();
        task.id = id;
        task.description = description;
        task.type_of_work = typeOfWork;
        task.price = price;
        task.reservation = reservation;

        return task;
    }
}
