package com.sportscenter.domain.entities;

import java.time.LocalDateTime;

public class SaleDetail {
    private int id;
    private int saleId;
    private int productId;
    private int quantity;
    private double unitPrice;
    private double subtotal;
    private LocalDateTime createdAt;

    public SaleDetail() {}

    public SaleDetail(int id) {
        this.id = id;
    }

    public SaleDetail(int saleId, int productId, int quantity, double unitPrice) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = quantity * unitPrice;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSaleId() {
        return saleId;
    }
    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = this.quantity * this.unitPrice;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.subtotal = this.quantity * this.unitPrice;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SaleDetail [id=" + id + ", productId=" + productId + ", quantity=" + quantity + "]";
    }

    public void setSubtotal(double double1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSubtotal'");
    }
}