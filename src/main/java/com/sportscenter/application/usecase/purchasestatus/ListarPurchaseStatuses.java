package com.sportscenter.application.usecase.purchasestatus;

public class ListarPurchaseStatuses {
    public void listar(PurchaseStatusUseCase purchaseStatusUseCase) {
        System.out.println("\n=== LISTADO DE ESTADOS DE COMPRA ===");
        System.out.printf("%-5s %-15s %-30s%n", "ID", "ESTADO", "DESCRIPCIÓN");
        System.out.println("--------------------------------------------------");

        try {
            purchaseStatusUseCase.getAllPurchaseStatuses().forEach(s -> System.out.printf("%-5d %-15s %-30s%n",
                    s.getId(),
                    s.getStatusName(),
                    s.getDescription() != null ? s.getDescription() : "N/A"));

        } catch (Exception e) {
            System.out.println("❌ Error al obtener los estados de venta: " + e.getMessage());
        }
    }
}