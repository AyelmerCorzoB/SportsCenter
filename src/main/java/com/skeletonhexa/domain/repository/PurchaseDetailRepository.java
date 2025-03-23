package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.PurchaseDetail;

public interface PurchaseDetailRepository {
    void save(PurchaseDetail PurchaseDetail);

    PurchaseDetail searchById(int id);

    List<PurchaseDetail> listAll();

    void update(PurchaseDetail PurchaseDetail);

    void delete(int id);

}
