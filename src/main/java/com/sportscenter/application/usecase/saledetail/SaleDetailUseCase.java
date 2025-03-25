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
        SaleDetail detail = new SaleDetail();
        detail.setSaleId(saleId);
        detail.setProductId(productId);
        detail.setQuantity(quantity);
        detail.setUnitPrice(unitPrice);
        repository.save(detail);
    }

    public List<SaleDetail> getDetailsBySaleId(int saleId) {
        return repository.findBySaleId(saleId);
    }

    public void deleteSaleDetails(int saleId) {
        repository.deleteBySaleId(saleId);
    }
}