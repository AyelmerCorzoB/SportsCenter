package com.skeletonhexa.domain.entities;

import java.sql.Date;

public class Customer {
    private int id;
    private String customerType;
    private String name;
    private String identityDocument;
    private String email;
    private String phone;
    private String address;
    private Date registrationDate;

    public Customer() {
    }

    public Customer(String customerType, String name, String identityDocument, String email, String phone,
            String address, Date registrationDate) {
        this.customerType = customerType;
        this.name = name;
        this.identityDocument = identityDocument;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    public Customer(int id, String customerType, String name, String identityDocument, String email, String phone,
            String address, Date registrationDate) {
        this.id = id;
        this.customerType = customerType;
        this.name = name;
        this.identityDocument = identityDocument;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerType='" + customerType + '\'' +
                ", name='" + name + '\'' +
                ", identityDocument='" + identityDocument + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}