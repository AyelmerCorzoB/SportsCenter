package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.SaleDetail;

public interface SaleDetailRepository {
    void save(SaleDetail saleDetail);
    SaleDetail searchById(int id);
    List<SaleDetail> listAll();
    void update(SaleDetail SaleDetail);
    void delete(int id);
    
}
