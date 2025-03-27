package com.sportscenter.application.usecase.purchasestatus;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class EliminarPurchaseStatus {
    public void eliminar(Scanner sc, PurchaseStatusUseCase purchaseStatusUseCase) {
        System.out.println("\n=== ELIMINAR ESTADO DE COMPRA ===");
        
        System.out.print("ID del estado a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        

        try {
            purchaseStatusUseCase.deletePurchaseStatus(id);
            System.out.println("✅ Estado de compra eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al eliminar el estado de venta: " + e.getMessage());
        }
    }
}