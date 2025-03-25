package com.sportscenter.domain.entities;

public class CustomerType {
    private int id;
    private String typeName;
    private String description;

    public CustomerType() {}

    public CustomerType(int id) {
        this.id = id;
    }

    public CustomerType(String typeName, String description) {
        this.typeName = typeName;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CustomerType [id=" + id + ", typeName=" + typeName + "]";
    }
}