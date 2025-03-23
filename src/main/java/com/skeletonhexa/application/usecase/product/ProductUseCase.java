package com.skeletonhexa.application.usecase.product;

import java.sql.Date;
import java.util.List;

import com.skeletonhexa.domain.entities.Category;
import com.skeletonhexa.domain.entities.Product;
import com.skeletonhexa.domain.entities.Supplier;
import com.skeletonhexa.domain.repository.ProductRepository;

public class ProductUseCase {
    private final ProductRepository repository;

    public ProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public void registerProduct(String name, String description, double unit_price, String size, String color,
            int current_stock,
            int minimum_stock, Date entry_date, Category category, Supplier supplier) {
        Product product = new Product(name, description, unit_price, size, color, current_stock, minimum_stock,
                entry_date, category, supplier);
        repository.save(product);
    }

    public Product getProduct(int id) {
        return repository.searchById(id);
    }

    public List<Product> ListProducts() {
        return repository.listAll();
    }

    public void updateCategry(int id, String name, String description, double unit_price, String size, String color,
    int current_stock, int minimum_stock, Date entry_date, Category category, Supplier supplier){
        Product product = new Product(id, name, description, unit_price, size, color, current_stock, minimum_stock, entry_date, category, supplier);
        repository.update(product);
    }

    public void deleteProduct(int id) {
        repository.delete(id);
    }
}