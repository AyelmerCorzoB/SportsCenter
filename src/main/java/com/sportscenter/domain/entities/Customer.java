package com.sportscenter.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a customer entity in the system.
 */
public class Customer {
    private int id;
    private int customerTypeId;
    private String name;
    private String identityDocument;
    private String phone;
    private String address;
    private LocalDate registrationDate;
    private int createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Customer() {
    }

    public Customer(int id) {
        this.id = id;
    }

    public Customer(int customerTypeId, String name, String identityDocument, String phone, String address, LocalDate registrationDate) {
        this.customerTypeId = customerTypeId;
        this.name = name;
        this.identityDocument = identityDocument;
        this.phone = phone;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerTypeId() { return customerTypeId; }
    public void setCustomerTypeId(int customerTypeId) { this.customerTypeId = customerTypeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIdentityDocument() { return identityDocument; }
    public void setIdentityDocument(String identityDocument) { this.identityDocument = identityDocument; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerTypeId=" + customerTypeId +
                ", name='" + name + '\'' +
                ", identityDocument='" + identityDocument + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", registrationDate=" + registrationDate +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
