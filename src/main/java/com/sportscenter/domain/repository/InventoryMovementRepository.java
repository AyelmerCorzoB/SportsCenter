package com.sportscenter.domain.repository;

import com.sportscenter.domain.entities.InventoryMovement;

import java.util.List;

public interface InventoryMovementRepository {
    void save(InventoryMovement movement);
    List<InventoryMovement> findByProductId(int productId);
    List<InventoryMovement> findAll();
}

