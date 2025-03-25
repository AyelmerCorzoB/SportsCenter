package com.sportscenter.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Sale {
    private int id;
    private int customerId;
    private LocalDate saleDate;
    private int paymentMethodId;
    private double discount;
    private double total;
    private int userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Sale() {}

    public Sale(int id) {
        this.id = id;
    }

    public Sale(int customerId, LocalDate saleDate, int paymentMethodId, int userId) {
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.paymentMethodId = paymentMethodId;
        this.userId = userId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public LocalDate getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
    public int getPaymentMethodId() {
        return paymentMethodId;
    }
    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
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
        return "Sale [id=" + id + ", customerId=" + customerId + ", total=" + total + "]";
    }
}