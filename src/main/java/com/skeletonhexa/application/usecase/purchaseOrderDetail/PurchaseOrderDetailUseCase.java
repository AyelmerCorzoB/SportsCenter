package com.skeletonhexa.application.usecase.purchaseOrderDetail;

import java.math.BigDecimal;
import java.util.List;

import com.skeletonhexa.domain.entities.PurchaseOrderDetail;
import com.skeletonhexa.domain.repository.PurchaseOrderDetailRepository;

public class PurchaseOrderDetailUseCase {
    private final PurchaseOrderDetailRepository repository;

    public PurchaseOrderDetailUseCase(PurchaseOrderDetailRepository repository){
        this.repository = repository;
    }

    public void registerPurchaseOrderDetail(int purchaseOrderId, int productId, int quantity, BigDecimal unitPrice,
            BigDecimal subtotal){
        PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail(purchaseOrderId, productId, quantity, unitPrice, subtotal);
        repository.save(purchaseOrderDetail);
    }

    public PurchaseOrderDetail getPurchaseOrderDetail(int id){
        return repository.searchById(id);
    }

    public List<PurchaseOrderDetail> ListPurchaseOrderDetails(){
        return repository.listAll();
    }

    public void updatePurchaseOrderDetail(int id, int purchaseOrderId, int productId, int quantity, BigDecimal unitPrice,
    BigDecimal subtotal){
        PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail(id, purchaseOrderId, productId, quantity, unitPrice, subtotal);
        repository.update(purchaseOrderDetail);
    }

    public void deletePurchaseOrderDetail(int id){
        repository.delete(id);
    }
}