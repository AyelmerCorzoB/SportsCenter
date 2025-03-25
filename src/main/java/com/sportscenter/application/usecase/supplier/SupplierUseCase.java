package com.sportscenter.application.usecase.supplier;

import com.sportscenter.domain.entities.Supplier;
import com.sportscenter.domain.repository.SupplierRepository;
import java.util.List;

public class SupplierUseCase {
    private final SupplierRepository repository;

    public SupplierUseCase(SupplierRepository repository) {
        this.repository = repository;
    }

    public void registerSupplier(String name, String phone, String email, 
                               String address, String taxId) {
        Supplier supplier = new Supplier();
        supplier.setName(name);
        supplier.setPhone(phone);
        supplier.setEmail(email);
        supplier.setAddress(address);
        supplier.setTaxId(taxId);
        repository.save(supplier);
    }

    public Supplier getSupplierById(int id) {
        return repository.findById(id);
    }

    public List<Supplier> getAllSuppliers() {
        return repository.findAll();
    }

    public void updateSupplier(int id, String name, String phone, String email, 
                             String address, String taxId) {
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setName(name);
        supplier.setPhone(phone);
        supplier.setEmail(email);
        supplier.setAddress(address);
        supplier.setTaxId(taxId);
        repository.update(supplier);
    }

    public void deleteSupplier(int id) {
        repository.delete(id);
    }
}