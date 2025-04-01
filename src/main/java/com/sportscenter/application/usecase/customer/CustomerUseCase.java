package com.sportscenter.application.usecase.customer;

import com.sportscenter.domain.entities.Customer;
import com.sportscenter.domain.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.List;

public class CustomerUseCase {
    private final CustomerRepository repository;

    public CustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public void registerCustomer(int customerTypeId, String name, String identityDocument,
            String phone, String address, LocalDate registrationDate) {
        Customer customer = new Customer();
        customer.setCustomerTypeId(customerTypeId);
        customer.setName(name);
        customer.setIdentityDocument(identityDocument);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setRegistrationDate(registrationDate);
        repository.save(customer);
    }

    public Customer getCustomerById(int id) {
        return repository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public void updateCustomer(int id, int customerTypeId, String name, String identityDocument, String phone, String address,LocalDate registrationDate) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCustomerTypeId(customerTypeId);
        customer.setName(name);
        customer.setIdentityDocument(identityDocument);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setRegistrationDate(registrationDate);
        repository.update(customer);
    }

    public void deleteCustomer(int id) {
        repository.delete(id);
    }
}