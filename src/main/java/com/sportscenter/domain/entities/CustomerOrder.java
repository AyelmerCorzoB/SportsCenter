package com.sportscenter.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerOrder {
    private int id;
    private int customerId;
    private LocalDate orderDate;
    private int statusId;
    private double total;
    private int userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CustomerOrder() {}

    public CustomerOrder(int id) {
        this.id = id;
    }

    public CustomerOrder(int customerId, LocalDate orderDate, int statusId, int userId) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.statusId = statusId;
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
    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    public int getStatusId() {
        return statusId;
    }
    public void setStatusId(int statusId) {
        this.statusId = statusId;
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
        return "CustomerOrder [id=" + id + ", customerId=" + customerId + ", total=" + total + "]";
    }
}