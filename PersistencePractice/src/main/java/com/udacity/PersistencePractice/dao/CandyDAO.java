package com.udacity.PersistencePractice.dao;

import com.udacity.PersistencePractice.data.CandyData;

import java.util.List;

public interface CandyDAO {
    List<CandyData> list();     // get all available candy
    void addToDelivery(Long candyId, Long deliveryId);
    List<CandyData> findByDelivery(Long deliveryId);
}
