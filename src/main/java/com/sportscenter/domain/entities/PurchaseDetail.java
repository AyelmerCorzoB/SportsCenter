package com.sportscenter.domain.entities;

import java.time.LocalDateTime;

public class PurchaseDetail {
    private int id;
    private int purchaseId;
    private int productId;
    private int quantity;
    private double unitPrice;
    private double subtotal;
    private LocalDateTime createdAt;

    public PurchaseDetail() {}

    public PurchaseDetail(int id) {
        this.id = id;
    }

    public PurchaseDetail(int purchaseId, int productId, int quantity, double unitPrice) {
        this.purchaseId = purchaseId;
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
    public int getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
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
        return "PurchaseDetail [id=" + id + ", productId=" + productId + ", quantity=" + quantity + "]";
    }
}