package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.PurchaseOrder;

public interface PurchaseOrderRepository {
    void save(PurchaseOrder purchaseOrder);
    PurchaseOrder searchById(int id);
    List<PurchaseOrder> listAll();
    void update(PurchaseOrder purchaseOrder);
    void delete(int id);
}
