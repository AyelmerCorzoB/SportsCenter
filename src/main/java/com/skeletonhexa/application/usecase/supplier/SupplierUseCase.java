package com.skeletonhexa.application.usecase.supplier;

import java.util.List;

import com.skeletonhexa.domain.entities.Supplier;
import com.skeletonhexa.domain.repository.SupplierRepository;

public class SupplierUseCase {
    private final SupplierRepository repository;

    public SupplierUseCase(SupplierRepository repository){
        this.repository = repository;
    }

    public void registerSupplier(String name, int phone, String email, String address, String tax_id){
        Supplier supplier = new Supplier(name, phone, email, address, tax_id);
        repository.save(supplier);
    }

    public Supplier getSupplier(int id){
        return repository.searchById(id);
    }

    public List<Supplier> ListSuppliers(){
        return repository.listAll();
    }

    public void updateCategry(int id, String name, int phone, String email, String address, String tax_id){
        Supplier supplier = new Supplier(id, name, phone, email, address, tax_id);
        repository.update(supplier);
    }

    public void deleteSupplier(int id){
        repository.delete(id);
    }
}