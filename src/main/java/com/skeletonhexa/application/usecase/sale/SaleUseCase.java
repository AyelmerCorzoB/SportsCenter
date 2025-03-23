package com.skeletonhexa.application.usecase.sale;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.skeletonhexa.domain.entities.Sale;
import com.skeletonhexa.domain.repository.SaleRepository;

public class SaleUseCase {
    private final SaleRepository repository;

    public SaleUseCase(SaleRepository repository){
        this.repository = repository;
    }

    public void registerSale(int customerId, Date saleDate, String paymentMethod, BigDecimal discount, BigDecimal total,
            int userId){
        Sale sale = new Sale(customerId, saleDate, paymentMethod, discount, total, userId);
        repository.save(sale);
    }

    public Sale getSale(int id){
        return repository.searchById(id);
    }

    public List<Sale> ListSales(){
        return repository.listAll();
    }

    public void updateCategry(int id, int customerId, Date saleDate, String paymentMethod, BigDecimal discount, BigDecimal total,
    int userId){
        Sale sale = new Sale(id, customerId, saleDate, paymentMethod, discount, total, userId);
        repository.update(sale);
    }

    public void deleteSale(int id){
        repository.delete(id);
    }
}