package com.sportscenter.domain.entities;

public class ReportType {
    private int id;
    private String typeName;
    private String description;

    public ReportType() {}

    public ReportType(int id) {
        this.id = id;
    }

    public ReportType(String typeName, String description) {
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
        return "ReportType [id=" + id + ", typeName=" + typeName + "]";
    }
}