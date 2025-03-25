package com.sportscenter.domain.repository;

import java.util.List;

import com.sportscenter.domain.entities.PurchaseDetail;

public interface PurchaseDetailRepository {
    void save(PurchaseDetail PurchaseDetail);

    PurchaseDetail searchById(int id);

    List<PurchaseDetail> listAll(int id);

    void update(PurchaseDetail PurchaseDetail);

    void delete(int id);

}
