package com.sportscenter.domain.repository;

import java.util.List;
import com.sportscenter.domain.entities.SaleDetail;

public interface SaleDetailRepository {
    void save(SaleDetail saleDetail);
    List<SaleDetail> findBySaleId(int saleId);
    void deleteBySaleId(int saleId);
}