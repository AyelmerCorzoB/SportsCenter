package com.skeletonhexa.application.usecase.purchaseOrder;

import java.util.Date;
import java.util.List;

import com.skeletonhexa.domain.entities.PurchaseOrder;
import com.skeletonhexa.domain.model.StatusType;
import com.skeletonhexa.domain.repository.PurchaseOrderRepository;

public class PurchaseOrderUseCase {
    private final PurchaseOrderRepository repository;

    public PurchaseOrderUseCase(PurchaseOrderRepository repository){
        this.repository = repository;
    }

    public void registerPurchaseOrder(Date date, int supplierId, StatusType status, int employeeId){
        PurchaseOrder purchaseOrder = new PurchaseOrder(date, supplierId, status, employeeId);
        repository.save(purchaseOrder);
    }

    public PurchaseOrder getPurchaseOrder(int id){
        return repository.searchById(id);
    }

    public List<PurchaseOrder> ListPurchaseOrders(){
        return repository.listAll();
    }

    public void updateCategry(int id, Date date, int supplierId, StatusType status, int employeeId){
        PurchaseOrder purchaseOrder = new PurchaseOrder(id, date, supplierId, status, employeeId);
        repository.update(purchaseOrder);
    }

    public void deletePurchaseOrder(int id){
        repository.delete(id);
    }
}