package com.sportscenter.domain.repository;

import com.sportscenter.domain.entities.SaleDetail;
import java.util.List;

public interface SaleDetailRepository {
    void save(SaleDetail detail);
    void update(SaleDetail detail);
    void delete(int id);
    void deleteBySaleId(int saleId);
    SaleDetail findById(int id);
    List<SaleDetail> findBySaleId(int saleId);
    List<SaleDetail> findAll();
}