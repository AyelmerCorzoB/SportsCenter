package com.sportscenter.application.usecase.purchase_detail;

import com.sportscenter.domain.entities.PurchaseDetail;
import com.sportscenter.domain.repository.PurchaseDetailRepository;
import java.util.List;

public class PurchaseDetailUseCase {
    private final PurchaseDetailRepository repository;

    public PurchaseDetailUseCase(PurchaseDetailRepository repository) {
        this.repository = repository;
    }

    public void registerPurchaseDetail(int purchaseId, int productId,
            int quantity, double unitPrice) {
        PurchaseDetail detail = new PurchaseDetail();
        detail.setPurchaseId(purchaseId);
        detail.setProductId(productId);
        detail.setQuantity(quantity);
        detail.setUnitPrice(unitPrice);
        repository.save(detail);
    }

    public PurchaseDetail getPurchaseDetailById(int id) {
        return repository.searchById(id);
    }

    public List<PurchaseDetail> getDetailsByPurchase(int purchaseId) {
        return repository.listAll(purchaseId);
    }

    public void updatePurchaseDetail(int id, int quantity, double unitPrice) {
        PurchaseDetail detail = repository.searchById(id);
        detail.setQuantity(quantity);
        detail.setUnitPrice(unitPrice);
        repository.update(detail);
    }

    public void deletePurchaseDetail(int id) {
        repository.delete(id);
    }
}