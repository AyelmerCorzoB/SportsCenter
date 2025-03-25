package com.sportscenter.domain.entities;

import java.time.LocalDateTime;

public class Supplier {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String taxId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int createdBy;

    public Supplier() {}

    public Supplier(int id) {
        this.id = id;
    }

    public Supplier(String name, String phone, String email, String address, String taxId) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.taxId = taxId;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTaxId() {
        return taxId;
    }
    public void setTaxId(String taxId) {
        this.taxId = taxId;
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
    public int getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Supplier [id=" + id + ", name=" + name + ", taxId=" + taxId + "]";
    }
}