package com.sportscenter.domain.repository;

import java.util.List;

import com.sportscenter.domain.entities.Supplier;

public interface SupplierRepository {
    void save(Supplier supplier);
    Supplier findById(int id);
    List<Supplier> findAll();
    void update(Supplier supplier);
    void delete(int id);
}
