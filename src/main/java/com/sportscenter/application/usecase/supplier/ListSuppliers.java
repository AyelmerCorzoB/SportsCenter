package com.sportscenter.application.usecase.supplier;

public class ListSuppliers {
    public void List(SupplierUseCase supplierUseCase) {
        System.out.println("\n=== LISTADO DE PROVEEDORES ===");
        System.out.printf("%-5s %-20s %-15s %-25s %-15s%n",
                "ID", "NOMBRE", "TELÉFONO", "RUC/NIT");
        System.out.println("------------------------------------------------------------------");

        try {
            supplierUseCase.getAllSuppliers().forEach(s -> System.out.printf("%-5d %-20s %-15s %-25s %-15s%n",
                    s.getId(),
                    s.getName(),
                    s.getPhone(),
                    s.getTaxId()));
        } catch (Exception e) {
            System.out.println("X Error al obtener a los proveedores: " + e.getMessage());
        }
    }
}