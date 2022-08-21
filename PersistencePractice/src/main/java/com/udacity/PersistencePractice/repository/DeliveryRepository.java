package com.udacity.PersistencePractice.repository;

import com.udacity.PersistencePractice.entity.delivery.Delivery;
import com.udacity.PersistencePractice.entity.delivery.RecipientAndPrice;
import com.udacity.PersistencePractice.entity.inventory.Plant;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Delivery delivery) {
        entityManager.persist(delivery);
    }

    public Delivery find(Long id) {
        return entityManager.find(Delivery.class, id);
    }

    public Delivery merge(Delivery delivery) {
        return entityManager.merge(delivery);
    }

    public void delete(Long id) {
        entityManager.remove(entityManager.find(Delivery.class, id));
    }

    public List<Delivery> findDeliveriesByName(String name) {
        TypedQuery<Delivery> query = entityManager.createQuery("Delivery.findByName", Delivery.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public RecipientAndPrice getBill(Long deliveryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> criteria = cb.createQuery(RecipientAndPrice.class);

        Root<Plant> root = criteria.from(Plant.class);
        criteria.select(
                cb.construct(
                        RecipientAndPrice.class,
                        root.get("delivery").get("name"),
                        cb.sum(root.get("price"))))
                .where(cb.equal(root.get("delivery").get("id"), deliveryId));

        return entityManager.createQuery(criteria).getSingleResult();
    }
}
