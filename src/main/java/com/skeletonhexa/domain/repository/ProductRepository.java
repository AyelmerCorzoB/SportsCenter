package com.skeletonhexa.domain.repository;

import java.util.List;

import com.skeletonhexa.domain.entities.Product;

public interface ProductRepository {
    void save(Product product);
    Product searchById(int id);
    List<Product> listAll();
    void update(Product product);
    void delete(int id);
}
