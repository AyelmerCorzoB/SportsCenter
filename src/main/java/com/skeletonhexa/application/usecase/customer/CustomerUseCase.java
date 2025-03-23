package com.skeletonhexa.application.usecase.customer;

import java.sql.Date;
import java.util.List;

import com.skeletonhexa.domain.entities.Customer;
import com.skeletonhexa.domain.repository.CustomerRepository;

public class CustomerUseCase {
    private final CustomerRepository repository;

    public CustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public void registerCustomer(String customerType, String name, String identityDocument, String email, String phone,
            String address, Date registrationDate){
        Customer customer = new Customer(customerType, name, identityDocument, email, phone, address, registrationDate);
        repository.save(customer);
    }

    public Customer getCustomer(int id) {
        return repository.searchById(id);
    }

    public List<Customer> ListCustomers() {
        return repository.listAll();
    }

    public void updateCustomer(int id, String customerType, String name, String identityDocument, String email,
            String phone,
            String address, Date registrationDate) {
        Customer customer = new Customer(id, customerType, name, identityDocument, email, phone, address,
                registrationDate);
        repository.update(customer);
    }

    public void deleteCustomer(int id) {
        repository.delete(id);
    }
}