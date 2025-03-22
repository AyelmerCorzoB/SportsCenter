package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.Sale;

public interface SaleRepository {
    void save(Sale saleSale);
    Sale searchById(int id);
    List<Sale> listAll();
    void update(Sale saleSale);
    void delete(int id);
    
}
