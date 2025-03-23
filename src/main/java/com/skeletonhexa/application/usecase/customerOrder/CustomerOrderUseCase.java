package com.skeletonhexa.application.usecase.customerOrder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.skeletonhexa.domain.entities.CustomerOrder;
import com.skeletonhexa.domain.repository.CustomerOrderRepository;

public class CustomerOrderUseCase {
    private final CustomerOrderRepository repository;

    public CustomerOrderUseCase(CustomerOrderRepository repository){
        this.repository = repository;
    }

    public void registerCustomerOrder(int customerId, Date orderDate, String status, BigDecimal total, int userId){
        CustomerOrder customerOrder = new CustomerOrder(customerId, orderDate, status, total, userId);
        repository.save(customerOrder);
    }

    public CustomerOrder getCustomerOrder(int id){
        return repository.searchById(id);
    }

    public List<CustomerOrder> ListCustomerOrders(){
        return repository.listAll();
    }

    public void updateCategry(int id, int customerId, Date orderDate, String status, BigDecimal total, int userId){
        CustomerOrder customerOrder = new CustomerOrder(id, customerId, orderDate, status, total, userId);
        repository.update(customerOrder);
    }

    public void deleteCustomerOrder(int id){
        repository.delete(id);
    }
}