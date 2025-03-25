package com.sportscenter.domain.repository;

import java.util.List;

import com.sportscenter.domain.entities.CustomerOrder;

public interface CustomerOrderRepository {
    void save(CustomerOrder customerOrder);
    CustomerOrder findById(int id);
    List<CustomerOrder> findAll();
    void update(CustomerOrder customerOrder);
    void delete(int id);
}