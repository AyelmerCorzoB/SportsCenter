package com.skeletonhexa.domain.entities;

import java.util.Date;
import java.util.Objects;

import com.skeletonhexa.domain.model.StatusType;

public class PurchaseOrder {
    private int id;
    private Date date;
    private int supplierId; // Referencia al proveedor (Supplier)
    private StatusType status; // Enum para el estado de la orden
    private int employeeId; // Referencia al empleado (Employee)

    // Constructor vacío
    public PurchaseOrder() {}

    // Constructor con todos los campos
    public PurchaseOrder(int id, Date date, int supplierId, StatusType status, int employeeId) {
        this.id = id;
        this.date = date;
        this.supplierId = supplierId;
        this.status = status;
        this.employeeId = employeeId;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    // Método toString para representación en cadena
    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", date=" + date +
                ", supplierId=" + supplierId +
                ", status=" + status +
                ", employeeId=" + employeeId +
                '}';
    }

    // Método equals y hashCode para comparar objetos PurchaseOrder
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrder that = (PurchaseOrder) o;
        return id == that.id && supplierId == that.supplierId && employeeId == that.employeeId && Objects.equals(date, that.date) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, supplierId, status, employeeId);
    }
}