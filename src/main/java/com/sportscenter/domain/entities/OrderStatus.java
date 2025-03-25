package com.sportscenter.domain.entities;

public class OrderStatus {
    private int id;
    private String statusName;
    private String description;

    public OrderStatus() {}

    public OrderStatus(int id) {
        this.id = id;
    }

    public OrderStatus(String statusName, String description) {
        this.statusName = statusName;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStatusName() {
        return statusName;
    }
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrderStatus [id=" + id + ", statusName=" + statusName + "]";
    }
}