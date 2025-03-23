package com.skeletonhexa.application.usecase.purchaseOrderDetail;

import java.math.BigDecimal;
import java.util.List;

import com.skeletonhexa.domain.entities.PurchaseDetail;
import com.skeletonhexa.domain.repository.PurchaseDetailRepository;

public class PurchaseDetailUseCase {
    private final PurchaseDetailRepository repository;

    public PurchaseDetailUseCase(PurchaseDetailRepository repository){
        this.repository = repository;
    }

    public void registerPurchaseDetail(int PurchaseId, int productId, int quantity, BigDecimal unitPrice,
            BigDecimal subtotal){
        PurchaseDetail PurchaseDetail = new PurchaseDetail(PurchaseId, productId, quantity, unitPrice, subtotal);
        repository.save(PurchaseDetail);
    }

    public PurchaseDetail getPurchaseDetail(int id){
        return repository.searchById(id);
    }

    public List<PurchaseDetail> ListPurchaseDetails(){
        return repository.listAll();
    }

    public void updatePurchaseDetail(int id, int PurchaseId, int productId, int quantity, BigDecimal unitPrice,
    BigDecimal subtotal){
        PurchaseDetail PurchaseDetail = new PurchaseDetail(id, PurchaseId, productId, quantity, unitPrice, subtotal);
        repository.update(PurchaseDetail);
    }

    public void deletePurchaseDetail(int id){
        repository.delete(id);
    }
}