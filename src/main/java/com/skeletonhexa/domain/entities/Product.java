package com.skeletonhexa.domain.entities;

import java.sql.Date;

public class Product {
    private int id;
    private String name;
    private String description;
    private double unit_price;
    private String size;
    private String color;
    private int current_stock;
    private int minimum_stock;
    private Date entry_date;
    private Category category;
    private Supplier supplier;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getUnit_price() {
        return unit_price;
    }
    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getCurrent_stock() {
        return current_stock;
    }
    public void setCurrent_stock(int current_stock) {
        this.current_stock = current_stock;
    }
    public int getMinimum_stock() {
        return minimum_stock;
    }
    public void setMinimum_stock(int minimum_stock) {
        this.minimum_stock = minimum_stock;
    }
    public Date getEntry_date() {
        return entry_date;
    }
    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", unit_price=" + unit_price
                + ", size=" + size + ", color=" + color + ", current_stock=" + current_stock + ", minimum_stock="
                + minimum_stock + ", entry_date=" + entry_date + ", category=" + category + ", supplier=" + supplier
                + "]";
    }

}
