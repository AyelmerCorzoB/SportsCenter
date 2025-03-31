package com.sportscenter.application.usecase.purchasestatus;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
public class RegisterPurchaseStatus {
    public void Register(Scanner sc, PurchaseStatusUseCase purchaseStatusUseCase) {
        System.out.println("\n=== REGISTRO DE ESTADO DE COMPRA ===");
        
        
        System.out.println("Opciones: \n1. PENDING \n2. RECEIVED \n3. CANCELED");
        System.out.print("Estado: ");
        ValidationInt.validate(sc);
        String statusName = null;
        int methodNameOption = sc.nextInt();
        switch (methodNameOption) {
            case 1:
                statusName = "CASH";
                break;
            case 2:
                statusName = "CARD";
                break;
            case 3:
                statusName = "TRANSFER";
                break;
            default:
                System.out.println("Opcion invalido debe ser 1,2 o 3");
                break;
        }
        System.out.print("Descripción (opcional): ");
        String description = sc.nextLine();
        
        try {
            purchaseStatusUseCase.registerPurchaseStatus(statusName, description.isEmpty() ? null : description);
        System.out.println(":D Estado de compra registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al registrar el estado de venta: " + e.getMessage());
        }
    }
}