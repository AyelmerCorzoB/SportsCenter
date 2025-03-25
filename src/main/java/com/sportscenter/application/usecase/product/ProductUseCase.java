package com.sportscenter.application.usecase.product;

import com.sportscenter.domain.entities.Product;
import com.sportscenter.domain.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;

public class ProductUseCase {
    private final ProductRepository repository;

    public ProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public void registerProduct(String name, String description, double unitPrice, String size, int currentStock,
            int minimumStock,
            LocalDate entryDate, int categoryId, int supplierId, int colorId, int createdBy) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setUnitPrice(unitPrice);
        product.setSize(size);
        product.setCurrentStock(currentStock);
        product.setMinimumStock(minimumStock);
        product.setEntryDate(entryDate);
        product.setCategoryId(categoryId);
        product.setSupplierId(supplierId);
        product.setColorId(colorId);
        product.setCreatedBy(createdBy);
        repository.save(product);
    }

    public Product getProductById(int id) {
        return repository.findById(id);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public void updateProduct(int id, String name, String description, double unitPrice, String size, int currentStock,
            int minimumStock,
            LocalDate entryDate, int categoryId, int supplierId, int colorId, int createdBy) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setUnitPrice(unitPrice);
        product.setSize(size);
        product.setCurrentStock(currentStock);
        product.setMinimumStock(minimumStock);
        product.setEntryDate(entryDate);
        product.setCategoryId(categoryId);
        product.setSupplierId(supplierId);
        product.setColorId(colorId);
        product.setCreatedBy(createdBy);
        repository.update(product);
    }

    public void deleteProduct(int id) {
        repository.delete(id);
    }
}