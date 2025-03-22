package com.skeletonhexa.domain.entities;

//FACTURA
import java.math.BigDecimal;
import java.util.Date;

public class Invoice {
    private int id;
    private int saleId;
    private String invoiceNumber;
    private Date issueDate;
    private BigDecimal totalAmount;
    private BigDecimal taxes;

    public Invoice() {
    }

    public Invoice(int id, int saleId, String invoiceNumber, Date issueDate, BigDecimal totalAmount, BigDecimal taxes) {
        this.id = id;
        this.saleId = saleId;
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.totalAmount = totalAmount;
        this.taxes = taxes;
    }

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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", saleId=" + saleId +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", issueDate=" + issueDate +
                ", totalAmount=" + totalAmount +
                ", taxes=" + taxes +
                '}';
    }
}