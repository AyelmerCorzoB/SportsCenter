package com.sportscenter.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer {
    private int id;
    private int customerTypeId;
    private String name;
    private String identityDocument;
    private String email;
    private String phone;
    private String address;
    private LocalDate registrationDate;
    private int createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Customer() {}

    public Customer(int id) {
        this.id = id;
    }

    public Customer(int customerTypeId, String name, String identityDocument, 
                  String email, String phone, String address, LocalDate registrationDate) {
        this.customerTypeId = customerTypeId;
        this.name = name;
        this.identityDocument = identityDocument;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCustomerTypeId() {
        return customerTypeId;
    }
    public void setCustomerTypeId(int customerTypeId) {
        this.customerTypeId = customerTypeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdentityDocument() {
        return identityDocument;
    }
    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
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
        return "Customer [id=" + id + ", name=" + name + ", identityDocument=" + identityDocument + "]";
    }
}