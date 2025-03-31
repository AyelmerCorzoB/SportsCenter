package com.sportscenter.application.usecase.supplier;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Supplier;

public class SearchSupplier {
    public static void Search(Scanner sc, SupplierUseCase supplierUseCase) {
        System.out.println("\n=== BUSCAR PROVEEDOR ===");

        System.out.print("ID del proveedor: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        try {
            Supplier supplier = supplierUseCase.getSupplierById(id);
            if (supplier != null) {
                System.out.println("\nInformación del proveedor:");
                System.out.println("ID: " + supplier.getId());
                System.out.println("Nombre: " + supplier.getName());
                System.out.println("Teléfono: " + supplier.getPhone());
                System.out.println("Dirección: " + supplier.getAddress());
                System.out.println("RUC/NIT: " + supplier.getTaxId());
            } else {
                System.out.println("X No se encontró el proveedor con ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("X Error al buscar el proveedor: " + e.getMessage());
        }
    }
}