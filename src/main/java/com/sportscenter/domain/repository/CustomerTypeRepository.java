package com.sportscenter.domain.repository;

import java.util.List;
import com.sportscenter.domain.entities.CustomerType;

public interface CustomerTypeRepository {
    void save(CustomerType customerType);
    CustomerType findById(int id);
    List<CustomerType> findAll();
    void update(CustomerType customerType);
    void delete(int id);
}
