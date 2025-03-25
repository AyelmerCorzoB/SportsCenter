package com.sportscenter.application.usecase.customerorder;

import com.sportscenter.domain.entities.CustomerOrder;
import com.sportscenter.domain.repository.CustomerOrderRepository;
import java.time.LocalDate;
import java.util.List;

public class CustomerOrderUseCase {
    private final CustomerOrderRepository repository;

    public CustomerOrderUseCase(CustomerOrderRepository repository) {
        this.repository = repository;
    }

    public void registerCustomerOrder(int customerId, LocalDate orderDate,
            int statusId, double total, int userId) {
        CustomerOrder order = new CustomerOrder();
        order.setCustomerId(customerId);
        order.setOrderDate(orderDate);
        order.setStatusId(statusId);
        order.setTotal(total);
        order.setUserId(userId);
        repository.save(order);
    }

    public CustomerOrder getCustomerOrderById(int id) {
        return repository.findById(id);
    }

    public List<CustomerOrder> getAllCustomerOrders() {
        return repository.findAll();
    }

    public void updateCustomerOrder(int id, int customerId, LocalDate orderDate,
            int statusId, double total) {
        CustomerOrder order = new CustomerOrder();
        order.setId(id);
        order.setCustomerId(customerId);
        order.setOrderDate(orderDate);
        order.setStatusId(statusId);
        order.setTotal(total);
        repository.update(order);
    }

    public void deleteCustomerOrder(int id) {
        repository.delete(id);
    }
}