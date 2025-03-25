package com.sportscenter.application.usecase.purchasestatus;

import com.sportscenter.domain.entities.PurchaseStatus;
import com.sportscenter.domain.repository.PurchaseStatusRepository;
import java.util.List;

public class PurchaseStatusUseCase {
    private final PurchaseStatusRepository repository;

    public PurchaseStatusUseCase(PurchaseStatusRepository repository) {
        this.repository = repository;
    }

    public void registerPurchaseStatus(String statusName, String description) {
        PurchaseStatus purchaseStatus = new PurchaseStatus();
        purchaseStatus.setStatusName(statusName);
        purchaseStatus.setDescription(description);
        repository.save(purchaseStatus);
    }

    public PurchaseStatus getPurchaseStatusById(int id) {
        return repository.findById(id);
    }

    public List<PurchaseStatus> getAllPurchaseStatuses() {
        return repository.findAll();
    }

    public void updatePurchaseStatus(int id, String statusName, String description) {
        PurchaseStatus purchaseStatus = new PurchaseStatus();
        purchaseStatus.setId(id);
        purchaseStatus.setStatusName(statusName);
        purchaseStatus.setDescription(description);
        repository.update(purchaseStatus);
    }

    public void deletePurchaseStatus(int id) {
        repository.delete(id);
    }
}