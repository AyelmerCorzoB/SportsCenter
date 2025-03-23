package com.skeletonhexa.domain.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private int id;
    private int customerId;
    private Date orderDate;
    private String status;
    private BigDecimal total;
    private int userId;

    public Order() {
    }

    public Order(int customerId, Date orderDate, String status, BigDecimal total, int userId) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
        this.userId = userId;
    }

    public Order(int id, int customerId, Date orderDate, String status, BigDecimal total, int userId) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", total=" + total +
                ", userId=" + userId +
                '}';
    }
}