package com.sportscenter.domain.repository;

import java.util.List;
import com.sportscenter.domain.entities.Product;

public interface ProductRepository {
    void save(Product product);
    void save(Product product, int userId); // Nuevo método sobrecargado
    Product findById(int id);
    List<Product> findAll();
    void update(Product product);
    void delete(int id);
}
