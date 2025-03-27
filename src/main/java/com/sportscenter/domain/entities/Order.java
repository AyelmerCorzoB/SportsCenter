package com.sportscenter.domain.entities;

import java.time.LocalDate;

public class Order {
    private int id;
    private int userId;
    private LocalDate orderDate;
    private OrderStatus status;

    public Order(int id, int userId, LocalDate orderDate, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    // Métodos auxiliares para compatibilidad con tu UI
    public LocalDate getFecha() {
        return orderDate;
    }

    public OrderStatus getEstado() {
        return status;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               "\nFecha: " + orderDate +
               "\nEstado: " + status +
               "\n-------------------------";
    }
}
