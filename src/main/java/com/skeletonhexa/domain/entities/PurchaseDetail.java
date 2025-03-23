package com.skeletonhexa.domain.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class PurchaseDetail {
    private int id;
    private int PurchaseId;
    private int productId;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    public PurchaseDetail() {
    }

    public PurchaseDetail(int PurchaseId, int productId, int quantity, BigDecimal unitPrice,
            BigDecimal subtotal) {
        this.PurchaseId = PurchaseId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public PurchaseDetail(int id, int PurchaseId, int productId, int quantity, BigDecimal unitPrice,
            BigDecimal subtotal) {
        this.id = id;
        this.PurchaseId = PurchaseId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchaseId() {
        return PurchaseId;
    }

    public void setPurchaseId(int PurchaseId) {
        this.PurchaseId = PurchaseId;
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

    @Override
    public String toString() {
        return "PurchaseDetail{" +
                "id=" + id +
                ", PurchaseId=" + PurchaseId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subtotal=" + subtotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PurchaseDetail that = (PurchaseDetail) o;
        return id == that.id && PurchaseId == that.PurchaseId && productId == that.productId
                && quantity == that.quantity && Objects.equals(unitPrice, that.unitPrice)
                && Objects.equals(subtotal, that.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, PurchaseId, productId, quantity, unitPrice, subtotal);
    }
}