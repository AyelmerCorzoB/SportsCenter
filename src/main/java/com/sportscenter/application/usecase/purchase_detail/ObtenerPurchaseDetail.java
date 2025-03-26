package com.sportscenter.application.usecase.purchase_detail;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.purchase.PurchaseUseCase;
import com.sportscenter.domain.entities.Purchase;

public class ObtenerPurchaseDetail {
    public void obtenerPorId(Scanner sc, PurchaseUseCase purchaseUseCase) {
        System.out.println("\n=== CONSULTAR COMPRA POR ID ===");

        System.out.print("ID de la compra: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        try {
            Purchase purchase = purchaseUseCase.getPurchaseById(id);
            if (purchase != null) {
                System.out.println("\n📋 Detalles de la compra:");
                System.out.println("ID: " + purchase.getId());
                System.out.println("Fecha: " + purchase.getDate());
                System.out.println("Proveedor ID: " + purchase.getSupplierId());
                System.out.println("Estado ID: " + purchase.getStatusId());
                System.out.println("Empleado ID: " + purchase.getEmployeeId());
            } else {
                System.out.println("⚠️ No se encontró una compra con ese ID.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al consultar compra: " + e.getMessage());
        }
    }
}
