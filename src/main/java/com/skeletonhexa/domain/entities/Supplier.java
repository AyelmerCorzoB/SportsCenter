package com.skeletonhexa.domain.entities;

public class Supplier {

    private int id;
    private String name;
    private int phone;
    private String email;
    private String address;
    private String tax_id;

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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
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

    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    public Supplier(String name, int phone, String email, String address, String tax_id) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tax_id = tax_id;
    }

    public Supplier(int id, String name, int phone, String email, String address, String tax_id) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tax_id = tax_id;
    }

    @Override
    public String toString() {
        return "Supplier [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", address="
                + address + ", tax_id=" + tax_id + "]";
    }

}
