package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.Customer;


public interface CustomerRepository {
    void save(Customer customer);
    Customer searchById(int id);
    List<Customer> listAll();
    void update(Customer customer);
    void delete(int id);
}
