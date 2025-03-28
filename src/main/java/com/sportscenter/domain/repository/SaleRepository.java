package com.sportscenter.domain.repository;

import java.util.List;

import com.sportscenter.domain.entities.Sale;

public interface SaleRepository {
    void save(Sale sale);
    Sale findById(int id);
    List<Sale> findAll();
    List<Sale> findByUserId(int userId);
    void update(Sale sale);
    void delete(int id);
}