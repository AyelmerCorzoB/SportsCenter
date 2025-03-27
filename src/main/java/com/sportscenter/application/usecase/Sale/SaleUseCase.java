package com.sportscenter.application.usecase.Sale;

import java.time.LocalDate;
import java.util.List;
import com.sportscenter.domain.entities.Sale;
import com.sportscenter.domain.repository.SaleRepository;

public class SaleUseCase {
    private final SaleRepository repository;

    public SaleUseCase(SaleRepository repository) {
        this.repository = repository;
    }

    public void registerSale(int customerId, LocalDate saleDate,
            int paymentMethodId, double total, int userId) {
        Sale sale = new Sale();
        sale.setCustomerId(customerId);
        sale.setSaleDate(saleDate);
        sale.setPaymentMethodId(paymentMethodId);
        sale.setTotal(total);
        sale.setUserId(userId);
        repository.save(sale);
    }

    public Sale getSaleById(int id) {
        return repository.findById(id);
    }

    public List<Sale> getAllSales() {
        return repository.findAll();
    }

    public void updateSale(int id, int customerId, LocalDate saleDate,
            int paymentMethodId, double total) {
        Sale sale = repository.findById(id);
        if (sale != null) {
            sale.setCustomerId(customerId);
            sale.setSaleDate(saleDate);
            sale.setPaymentMethodId(paymentMethodId);
            sale.setTotal(total);
            repository.update(sale);
        }
    }

    public void deleteSale(int id) {
        repository.delete(id);
    }
}