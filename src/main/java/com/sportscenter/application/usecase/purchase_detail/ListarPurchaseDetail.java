package com.sportscenter.application.usecase.purchase_detail;

import java.util.List;

import com.sportscenter.application.usecase.purchase.PurchaseUseCase;
import com.sportscenter.domain.entities.Purchase;

public class ListarPurchaseDetail {

    public void listarTodos(PurchaseUseCase purchaseUseCase) {
        System.out.println("\n=== LISTADO DE TODAS LAS COMPRAS ===");

        try {
            List<Purchase> purchases = purchaseUseCase.getAllPurchases();

            if (purchases.isEmpty()) {
                System.out.println("No hay compras registradas.");
                return;
            }

            System.out.printf("%-5s %-12s %-12s %-10s %-10s%n",
                    "ID", "Fecha", "Proveedor", "Estado", "Empleado");
            System.out.println("------------------------------------------------");

            for (Purchase p : purchases) {
                System.out.printf("%-5d %-12s %-12d %-10d %-10d%n",
                        p.getId(),
                        p.getDate(),
                        p.getSupplierId(),
                        p.getStatusId(),
                        p.getEmployeeId());
            }
        } catch (Exception e) {
            System.out.println("X Error al listar compras: " + e.getMessage());
        }
    }
}
