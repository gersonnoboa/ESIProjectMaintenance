package com.maintenance.common.application.dto;

/**
 * Created by lgarcia on 2/27/2017.
 */
public class PlantNotFoundException extends Exception {
    public PlantNotFoundException(String msg) {
        super(msg);
    }
}
