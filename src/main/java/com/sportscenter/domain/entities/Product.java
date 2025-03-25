package com.sportscenter.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product {
    private int id;
    private String name;
    private String description;
    private double unitPrice;
    private String size;
    private int currentStock;
    private int minimumStock;
    private LocalDate entryDate;
    private int categoryId;
    private int supplierId;
    private int colorId;
    private int createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product() {}

    public Product(int id) {
        this.id = id;
    }

    

    public Product(String name, String description, double unitPrice, String size, int currentStock, int minimumStock,
            LocalDate entryDate, int categoryId, int supplierId, int colorId, int createdBy) {
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.size = size;
        this.currentStock = currentStock;
        this.minimumStock = minimumStock;
        this.entryDate = entryDate;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.colorId = colorId;
        this.createdBy = createdBy;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public int getCurrentStock() {
        return currentStock;
    }
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
    public int getMinimumStock() {
        return minimumStock;
    }
    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }
    public LocalDate getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    public int getColorId() {
        return colorId;
    }
    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
    public int getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", unitPrice=" + unitPrice + 
               ", currentStock=" + currentStock + "]";
    }
}