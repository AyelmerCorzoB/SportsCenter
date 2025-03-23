package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.Purchase;

public interface PurchaseRepository {
    void save(Purchase Purchase);
    Purchase searchById(int id);
    List<Purchase> listAll();
    void update(Purchase Purchase);
    void delete(int id);
}
