package com.maintenance.works.web.controller;

import com.maintenance.common.application.dto.PlantNotFoundException;
import com.maintenance.inventory.application.dto.PlantInventoryItemDTO;
import com.maintenance.inventory.application.dto.PlantReservationDTO;
import com.maintenance.inventory.domain.model.PlantInventoryItem;
import com.maintenance.inventory.domain.model.PlantReservation;
import com.maintenance.works.application.dto.MaintenanceTaskDTO;
import com.maintenance.works.application.dto.MaintenanceTaskReservationDTO;
import com.maintenance.works.application.service.MaintenanceService;
import com.maintenance.works.domain.model.MaintenanceTask;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by gkgranada on 24/05/2017.
 */
@Controller
@RequestMapping("/dashboard")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        return "dashboard/login";
    }

    @GetMapping("/home")
    public String getReturnedPlants(Model model) {
        List<PlantInventoryItemDTO> plants = maintenanceService.findReturnedPlants();
        model.addAttribute("plants", plants);
        return "dashboard/home";
    }
    @GetMapping("/tasks/new")
    public String createMaintenanceTaskForm(Model model, PlantInventoryItemDTO plant) throws PlantNotFoundException {
        System.out.println("plant: "+plant);
        PlantReservationDTO reservationDTO = new PlantReservationDTO();
        model.addAttribute("plant",plant);
        model.addAttribute("reservation",reservationDTO);
        return "dashboard/newtask";
    }

    @PostMapping("/tasks")
    public String createMaintenanceTask(Model model, @ModelAttribute MaintenanceTaskReservationDTO myEntity) {
        System.out.println("reservationDTO: "+myEntity);



        return "dashboard/home";
    }

//    @ModelAttribute(value = "myEntity")
//    public MaintenanceTaskReservationDTO newEntity()
//    {
//        return new MaintenanceTaskReservationDTO();
//    }

}
