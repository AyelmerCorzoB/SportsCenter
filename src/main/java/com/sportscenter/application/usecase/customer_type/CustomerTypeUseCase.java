package com.sportscenter.application.usecase.customer_type;

import com.sportscenter.domain.entities.CustomerType;
import com.sportscenter.domain.repository.CustomerTypeRepository;
import java.util.List;

public class CustomerTypeUseCase {
    private final CustomerTypeRepository repository;

    public CustomerTypeUseCase(CustomerTypeRepository repository) {
        this.repository = repository;
    }

    public void registerCustomerType(String typeName, String description) {
        CustomerType customerType = new CustomerType();
        customerType.setTypeName(typeName);
        customerType.setDescription(description);
        repository.save(customerType);
    }

    public CustomerType getCustomerTypeById(int id) {
        return repository.findById(id);
    }

    public List<CustomerType> getAllCustomerTypes() {
        return repository.findAll();
    }

    public void updateCustomerType(int id, String typeName, String description) {
        CustomerType customerType = new CustomerType();
        customerType.setId(id);
        customerType.setTypeName(typeName);
        customerType.setDescription(description);
        repository.update(customerType);
    }

    public void deleteCustomerType(int id) {
        repository.delete(id);
    }
}