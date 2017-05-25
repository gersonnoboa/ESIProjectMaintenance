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
import com.maintenance.works.domain.model.TypeOfWork;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by gkgranada on 24/05/2017.
 */
@Controller
@RequestMapping("/")
public class MaintenanceController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MaintenanceService maintenanceService;

    @PostConstruct
    private void setUpAuth() {
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("user2", "user2"));
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        return "dashboard/login";
    }


    @GetMapping("/")
    public String getReturnedPlants(Model model) {
        List<PlantInventoryItemDTO> plants = maintenanceService.findReturnedPlants();
        List<PlantInventoryItemDTO> serviceables = new ArrayList<>();
        List<PlantInventoryItemDTO> unServiceables = new ArrayList<>();
        for (PlantInventoryItemDTO dto: plants) {
            switch (dto.getEquipmentCondition()) {
                case SERVICEABLE:
                    serviceables.add(dto);
                    break;
                default:
                    unServiceables.add(dto);
                    break;
            }
        }
        System.out.println("the plants: "+plants);
        model.addAttribute("plants", serviceables);
        model.addAttribute("repairs", unServiceables);
        return "/dashboard/home";
    }
    @GetMapping("/tasks/new/{id}")
    public String createMaintenanceTaskForm(Model model, PlantInventoryItemDTO plant, @PathVariable String id) throws PlantNotFoundException {

        System.out.println("id: "+id);
        plant.set_id(id);
        System.out.println("plant: "+plant);
        model.addAttribute("plant",plant);
        return "/dashboard/newtask";
    }

    @GetMapping("/tasks")
    public String createMaintenanceTask(Model model, @ModelAttribute MaintenanceTaskReservationDTO myEntity) {
        System.out.println("reservationDTO: "+myEntity);

        maintenanceService.createMaintenanceTask(myEntity);

        return "forward:/";
    }

    @GetMapping("/tasks/complete/{id}")
    public String completeMaintenanceTask(Model model, PlantInventoryItemDTO plant, @PathVariable String id) throws PlantNotFoundException {
        maintenanceService.completeMaintenanceTask(id);
        return "forward:/";
    }

    @ModelAttribute(value = "myEntity")
    public MaintenanceTaskReservationDTO newEntity()
    {
        return new MaintenanceTaskReservationDTO();
    }

}
