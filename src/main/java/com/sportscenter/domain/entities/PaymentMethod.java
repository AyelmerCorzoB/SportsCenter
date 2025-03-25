package com.sportscenter.domain.entities;

public class PaymentMethod {
    private int id;
    private String methodName;
    private String description;

    public PaymentMethod() {}

    public PaymentMethod(int id) {
        this.id = id;
    }

    public PaymentMethod(String methodName, String description) {
        this.methodName = methodName;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PaymentMethod [id=" + id + ", methodName=" + methodName + "]";
    }
}