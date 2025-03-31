package com.sportscenter.application.usecase.saledetail;

import com.sportscenter.domain.entities.SaleDetail;
import com.sportscenter.domain.repository.SaleDetailRepository;
import java.util.List;

public class SaleDetailUseCase {
    private final SaleDetailRepository repository;

    public SaleDetailUseCase(SaleDetailRepository repository) {
        this.repository = repository;
    }

    public void registerSaleDetail(int saleId, int productId, int quantity, double unitPrice) {
        SaleDetail detail = new SaleDetail(saleId, productId, quantity, unitPrice);
        repository.save(detail);
    }

    public void updateSaleDetail(int id, int quantity, double unitPrice) {
        SaleDetail detail = repository.findById(id);
        if (detail != null) {
            detail.setQuantity(quantity);
            detail.setUnitPrice(unitPrice);
            repository.update(detail);
        }
    }
    
    public List<SaleDetail> getAllDetails() {
        return repository.findAll();
    }

    public SaleDetail getDetailById(int id) {
        return repository.findById(id);
    }

    public List<SaleDetail> getDetailsBySaleId(int saleId) {
        return repository.findBySaleId(saleId);
    }

    public void deleteSaleDetail(int id) {
        repository.delete(id);
    }

    public void deleteDetailsBySaleId(int saleId) {
        repository.deleteBySaleId(saleId);
    }
}