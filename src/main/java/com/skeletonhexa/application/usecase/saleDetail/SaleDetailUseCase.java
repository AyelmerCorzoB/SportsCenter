package com.skeletonhexa.application.usecase.saleDetail;

import java.math.BigDecimal;
import java.util.List;

import com.skeletonhexa.domain.entities.SaleDetail;
import com.skeletonhexa.domain.repository.SaleDetailRepository;

public class SaleDetailUseCase {
    private final SaleDetailRepository repository;

    public SaleDetailUseCase(SaleDetailRepository repository){
        this.repository = repository;
    }

    public void registerSaleDetail(int saleId, int productId, int quantity, BigDecimal unitPrice, BigDecimal subtotal){
        SaleDetail saleDetail = new SaleDetail(saleId, productId, quantity, unitPrice, subtotal);
        repository.save(saleDetail);
    }

    public SaleDetail getSaleDetail(int id){
        return repository.searchById(id);
    }

    public List<SaleDetail> ListSaleDetails(){
        return repository.listAll();
    }

    public void updateCategry(int id, int saleId, int productId, int quantity, BigDecimal unitPrice, BigDecimal subtotal){
        SaleDetail saleDetail = new SaleDetail(id, saleId, productId, quantity, unitPrice, subtotal);
        repository.update(saleDetail);
    }

    public void deleteSaleDetail(int id){
        repository.delete(id);
    }
}