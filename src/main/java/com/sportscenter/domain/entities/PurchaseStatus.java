package com.sportscenter.domain.entities;

public class PurchaseStatus {
    private int id;
    private String statusName;
    private String description;

    public PurchaseStatus() {}

    public PurchaseStatus(int id) {
        this.id = id;
    }

    public PurchaseStatus(String statusName, String description) {
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
        return "PurchaseStatus [id=" + id + ", statusName=" + statusName + "]";
    }
}