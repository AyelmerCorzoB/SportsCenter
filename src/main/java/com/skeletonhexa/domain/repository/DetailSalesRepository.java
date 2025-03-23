package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.DetailSales;

public interface DetailSalesRepository {
    void save(DetailSales detailSales);
    DetailSales searchById(int id);
    List<DetailSales> listAll();
    void update(DetailSales detailSales);
    void delete(int id);
}
