package com.sportscenter.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Purchase {
    private int id;
    private LocalDate date;
    private int supplierId;
    private int statusId;
    private int employeeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Purchase() {}

    public Purchase(int id) {
        this.id = id;
    }

    public Purchase(LocalDate date, int supplierId, int statusId, int employeeId) {
        this.date = date;
        this.supplierId = supplierId;
        this.statusId = statusId;
        this.employeeId = employeeId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    public int getStatusId() {
        return statusId;
    }
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
        return "Purchase [id=" + id + ", date=" + date + ", supplierId=" + supplierId + "]";
    }
}