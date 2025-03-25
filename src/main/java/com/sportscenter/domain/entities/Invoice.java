package com.sportscenter.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Invoice {
    private int id;
    private int saleId;
    private String invoiceNumber;
    private LocalDate issueDate;
    private double totalAmount;
    private double taxes;
    private LocalDateTime createdAt;

    public Invoice() {}

    public Invoice(int id) {
        this.id = id;
    }

    public Invoice(int saleId, String invoiceNumber, LocalDate issueDate) {
        this.saleId = saleId;
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSaleId() {
        return saleId;
    }
    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public double getTaxes() {
        return taxes;
    }
    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Invoice [id=" + id + ", invoiceNumber=" + invoiceNumber + ", totalAmount=" + totalAmount + "]";
    }
}