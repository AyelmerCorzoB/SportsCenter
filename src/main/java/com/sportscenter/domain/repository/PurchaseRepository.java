package com.sportscenter.domain.repository;

import java.util.List;

import com.sportscenter.domain.entities.Purchase;

public interface PurchaseRepository {
    void save(Purchase purchase);
    Purchase findById(int id);
    List<Purchase> findAll();
    void update(Purchase purchase);
    void delete(int id);
}