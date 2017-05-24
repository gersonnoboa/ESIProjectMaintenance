package com.maintenance.works.application.service;

import com.maintenance.inventory.application.dto.PlantInventoryItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gkgranada on 24/05/2017.
 */
@Service
public class MaintenanceService {

    @Autowired
    RestTemplate restTemplate;

    private void runInterceptors () {
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor("user1", "user1"));
    }

    // inventory domain
    public List<PlantInventoryItemDTO> findReturnedPlants() {
        runInterceptors();

        PlantInventoryItemDTO[] plants = restTemplate.getForObject(
                "http://localhost:8080/api/inventory/plants?name=&startDate=2018-01-10&endDate=2018-01-20",
                PlantInventoryItemDTO[].class);
        System.out.println("Plants: " + Arrays.asList(plants));

        return Arrays.asList(plants);
    }
}
