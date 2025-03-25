package com.sportscenter.application.usecase.purchase;

import com.sportscenter.domain.entities.Purchase;
import com.sportscenter.domain.repository.PurchaseRepository;
import java.time.LocalDate;
import java.util.List;

public class PurchaseUseCase {
    private final PurchaseRepository repository;

    public PurchaseUseCase(PurchaseRepository repository) {
        this.repository = repository;
    }

    public void registerPurchase(LocalDate date, int supplierId, int statusId, int employeeId) {
        Purchase purchase = new Purchase();
        purchase.setDate(date);
        purchase.setSupplierId(supplierId);
        purchase.setStatusId(statusId);
        purchase.setEmployeeId(employeeId);
        repository.save(purchase);
    }

    public Purchase getPurchaseById(int id) {
        return repository.findById(id);
    }

    public List<Purchase> getAllPurchases() {
        return repository.findAll();
    }

    public void updatePurchase(int id, LocalDate date, int supplierId, int statusId) {
        Purchase purchase = new Purchase();
        purchase.setId(id);
        purchase.setDate(date);
        purchase.setSupplierId(supplierId);
        purchase.setStatusId(statusId);
        repository.update(purchase);
    }

    public void deletePurchase(int id) {
        repository.delete(id);
    }
}