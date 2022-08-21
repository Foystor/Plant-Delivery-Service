package com.udacity.PersistencePractice.repository;

import com.udacity.PersistencePractice.entity.delivery.Delivery;
import com.udacity.PersistencePractice.entity.inventory.Plant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
public class PlantRepositoryTest {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testPriceLessThan() {
        Plant p = testEntityManager.persist(new Plant());
        p.setPrice(BigDecimal.valueOf(15));
        testEntityManager.persist(new Plant()).setPrice(BigDecimal.valueOf(30));

        List<Plant> cheapPlants = plantRepository.findByPriceLessThan(BigDecimal.valueOf(20));
        Assertions.assertEquals(1, cheapPlants.size(), "Size");
        Assertions.assertEquals(p.getId(), cheapPlants.get(0).getId(), "Id");
    }

    @Test
    void testDeliveryCompleted() {
        Plant p = testEntityManager.persist(new Plant());
        Delivery d = testEntityManager.persist(new Delivery());

        p.setDelivery(d);
        d.setPlants(List.of(p));

        //test both before and after
        Assertions.assertFalse(plantRepository.isDeliveryCompleted(p.getId()));
        d.setCompleted(true);
        Assertions.assertTrue(plantRepository.isDeliveryCompleted(p.getId()));
    }
}
