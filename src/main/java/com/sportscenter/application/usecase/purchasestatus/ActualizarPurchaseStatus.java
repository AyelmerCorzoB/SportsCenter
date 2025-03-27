package com.sportscenter.application.usecase.purchasestatus;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class ActualizarPurchaseStatus {
    public void actualizar(Scanner sc, PurchaseStatusUseCase purchaseStatusUseCase) {
        System.out.println("\n=== ACTUALIZAR ESTADO DE COMPRA ===");
        
        System.out.print("ID del estado a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Opciones: \n1. PENDING \n2. RECEIVED \n3. CANCELED");
        System.out.print("Estado: ");
        ValidationInt.validate(sc);
        String newStatusName = null;
        int methodNameOption = sc.nextInt();
        switch (methodNameOption) {
            case 1:
                newStatusName = "CASH";
                break;
            case 2:
                newStatusName = "CARD";
                break;
            case 3:
                newStatusName = "TRANSFER";
                break;
            default:
                System.out.println("Opcion invalido debe ser 1,2 o 3");
                break;
        }
        System.out.print("Nueva descripción (opcional): ");
        String newDescription = sc.nextLine();
        
        
        try {
            purchaseStatusUseCase.updatePurchaseStatus(id, newStatusName, newDescription.isEmpty() ? null : newDescription);
            System.out.println("✅ Estado de compra actualizado exitosamente.");

        } catch (Exception e) {
            System.out.println("X Error al actualizar el estado de venta: " + e.getMessage());
        }

    }
}