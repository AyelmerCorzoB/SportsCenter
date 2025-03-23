package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.PurchaseOrderDetail;

public interface PurchaseOrderDetailRepository {
    void save(PurchaseOrderDetail purchaseOrderDetail);

    PurchaseOrderDetail searchById(int id);

    List<PurchaseOrderDetail> listAll();

    void update(PurchaseOrderDetail purchaseOrderDetail);

    void delete(int id);

}
