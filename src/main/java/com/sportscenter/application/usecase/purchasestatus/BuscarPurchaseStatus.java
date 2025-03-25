package com.sportscenter.application.usecase.purchasestatus;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.PurchaseStatus;
public class BuscarPurchaseStatus {
    public void buscar(Scanner sc, PurchaseStatusUseCase purchaseStatusUseCase) {
        System.out.println("\n=== BUSCAR ESTADO DE COMPRA ===");
        
        System.out.print("ID del estado: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        PurchaseStatus status = purchaseStatusUseCase.getPurchaseStatusById(id);
        if(status != null) {
            System.out.println("\nInformación del estado:");
            System.out.println("ID: " + status.getId());
            System.out.println("Estado: " + status.getStatusName());
            System.out.println("Descripción: " + (status.getDescription() != null ? status.getDescription() : "N/A"));
        } else {
            System.out.println("❌ No se encontró el estado con ID: " + id);
        }
    }
}