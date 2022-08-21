package com.udacity.PersistencePractice.service;

import com.udacity.PersistencePractice.entity.delivery.Delivery;
import com.udacity.PersistencePractice.entity.delivery.RecipientAndPrice;
import com.udacity.PersistencePractice.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    public Long save(Delivery delivery) {
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }

    public RecipientAndPrice getBill(Long deliveryId) {
        return deliveryRepository.getBill(deliveryId);
    }
}
