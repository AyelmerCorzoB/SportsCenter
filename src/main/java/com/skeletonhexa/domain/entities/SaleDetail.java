package com.skeletonhexa.domain.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class SaleDetail {
    private int id;
    private int saleId; // Referencia a la venta (Sale)
    private int productId; // Referencia al producto (Product)
    private int quantity; // Cantidad vendida
    private BigDecimal unitPrice; // Precio unitario del producto
    private BigDecimal subtotal; // Subtotal (quantity * unitPrice)

    // Constructor vacío
    public SaleDetail() {}

    // Constructor con todos los campos
    public SaleDetail(int id, int saleId, int productId, int quantity, BigDecimal unitPrice, BigDecimal subtotal) {
        this.id = id;
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    // Getters y Setters
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    // Método toString para representación en cadena
    @Override
    public String toString() {
        return "SaleDetail{" +
                "id=" + id +
                ", saleId=" + saleId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subtotal=" + subtotal +
                '}';
    }

    // Método equals y hashCode para comparar objetos SaleDetail
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleDetail that = (SaleDetail) o;
        return id == that.id && saleId == that.saleId && productId == that.productId && quantity == that.quantity && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(subtotal, that.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saleId, productId, quantity, unitPrice, subtotal);
    }
}