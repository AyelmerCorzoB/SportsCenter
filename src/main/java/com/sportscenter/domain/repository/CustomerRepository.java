package com.sportscenter.domain.repository;

import java.util.List;
import com.sportscenter.domain.entities.Customer;

public interface CustomerRepository {
    void save(Customer customer);
    Customer findById(int id);
    List<Customer> findAll();
    void update(Customer customer);
    void delete(int id);
}