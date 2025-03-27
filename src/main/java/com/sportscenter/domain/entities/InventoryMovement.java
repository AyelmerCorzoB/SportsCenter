package com.sportscenter.domain.entities;

import java.time.LocalDateTime;

public class InventoryMovement {

    public enum MovementType {
        IN, OUT
    }

    private int id;
    private int productId;
    private int quantity;
    private MovementType movementType;
    private LocalDateTime movementDate;
    private String reason;

    // Getters y setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getProductId() { return productId; }

    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public MovementType getMovementType() { return movementType; }

    public void setMovementType(MovementType movementType) { this.movementType = movementType; }

    public LocalDateTime getMovementDate() { return movementDate; }

    public void setMovementDate(LocalDateTime movementDate) { this.movementDate = movementDate; }

    public String getReason() { return reason; }

    public void setReason(String reason) { this.reason = reason; }
}
