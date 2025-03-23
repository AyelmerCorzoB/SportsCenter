package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.CustomerOrder;

public interface CustomerOrderRepository {
    void save(CustomerOrder customerOrderCustomerOrder);
    CustomerOrder searchById(int id);
    List<CustomerOrder> listAll();
    void update(CustomerOrder customerOrderCustomerOrder);
    void delete(int id);
}
