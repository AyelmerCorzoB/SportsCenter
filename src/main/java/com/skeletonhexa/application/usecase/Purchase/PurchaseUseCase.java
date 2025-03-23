package com.skeletonhexa.application.usecase.Purchase;

import java.util.Date;
import java.util.List;

import com.skeletonhexa.domain.entities.Purchase;
import com.skeletonhexa.domain.model.StatusType;
import com.skeletonhexa.domain.repository.PurchaseRepository;

public class PurchaseUseCase {
    private final PurchaseRepository repository;

    public PurchaseUseCase(PurchaseRepository repository){
        this.repository = repository;
    }

    public void registerPurchase(Date date, int supplierId, StatusType status, int employeeId){
        Purchase Purchase = new Purchase(date, supplierId, status, employeeId);
        repository.save(Purchase);
    }

    public Purchase getPurchase(int id){
        return repository.searchById(id);
    }

    public List<Purchase> ListPurchases(){
        return repository.listAll();
    }

    public void updateCategry(int id, Date date, int supplierId, StatusType status, int employeeId){
        Purchase Purchase = new Purchase(id, date, supplierId, status, employeeId);
        repository.update(Purchase);
    }

    public void deletePurchase(int id){
        repository.delete(id);
    }
}