package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.Supplier;

public interface SupplierRepository {
    void save(Supplier supplieSupplier);
    Supplier searchById(int id);
    List<Supplier> listAll();
    void update(Supplier supplieSupplier);
    void delete(int id);
}
