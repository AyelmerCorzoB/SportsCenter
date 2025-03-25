package com.sportscenter.domain.repository;

import java.util.List;
import com.sportscenter.domain.entities.PurchaseStatus;

public interface PurchaseStatusRepository {
    void save(PurchaseStatus purchaseStatus);
    PurchaseStatus findById(int id);
    List<PurchaseStatus> findAll();
    void update(PurchaseStatus purchaseStatus);
    void delete(int id);
}