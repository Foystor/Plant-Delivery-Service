package com.udacity.PersistencePractice.service;

import com.udacity.PersistencePractice.entity.inventory.Plant;
import com.udacity.PersistencePractice.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PlantService {
    @Autowired
    PlantRepository plantRepository;

    public Long save(Plant plant) {
        return plantRepository.save(plant).getId();
    }

    public Boolean checkDelivered(Long plantId) {
        // return plantRepository.existsPlantByIdAndDeliveryCompleted(plantId, true);
        return plantRepository.isDeliveryCompleted(plantId);
    }

    public List<Plant> findPlantsBelowPrice(BigDecimal price) {
        return plantRepository.findByPriceLessThan(price);
    }
}
