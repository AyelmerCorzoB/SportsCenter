package com.skeletonhexa.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Sale {
    private int id;
    private int customerId;
    private Date saleDate;
    private String paymentMethod;
    private BigDecimal discount;
    private BigDecimal total;
    private int userId;

    public Sale() {
    }

    public Sale(int customerId, Date saleDate, String paymentMethod, BigDecimal discount, BigDecimal total,
            int userId) {
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.paymentMethod = paymentMethod;
        this.discount = discount;
        this.total = total;
        this.userId = userId;
    }

    public Sale(int id, int customerId, Date saleDate, String paymentMethod, BigDecimal discount, BigDecimal total,
            int userId) {
        this.id = id;
        this.customerId = customerId;
        this.saleDate = saleDate;
        this.paymentMethod = paymentMethod;
        this.discount = discount;
        this.total = total;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", saleDate=" + saleDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", discount=" + discount +
                ", total=" + total +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Sale sale = (Sale) o;
        return id == sale.id && customerId == sale.customerId && userId == sale.userId
                && Objects.equals(saleDate, sale.saleDate) && Objects.equals(paymentMethod, sale.paymentMethod)
                && Objects.equals(discount, sale.discount) && Objects.equals(total, sale.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, saleDate, paymentMethod, discount, total, userId);
    }
}
