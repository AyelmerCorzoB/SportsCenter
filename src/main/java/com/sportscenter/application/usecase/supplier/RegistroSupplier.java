package com.sportscenter.application.usecase.supplier;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationString;

public class RegistroSupplier {
    public static void registro(Scanner sc, SupplierUseCase supplierUseCase) {
        System.out.println("\n=== REGISTRO DE PROVEEDOR ===");

        System.out.print("Nombre: ");
        ValidationString.validate(sc);
        String name = sc.nextLine();

        System.out.print("Teléfono: ");
        ValidationString.validate(sc);
        String phone = sc.nextLine();

        System.out.print("Email: ");
        ValidationString.validate(sc);
        String email = sc.nextLine();

        System.out.print("Dirección: ");
        ValidationString.validate(sc);
        String address = sc.nextLine();

        System.out.print("RUC/NIT: ");
        ValidationString.validate(sc);
        String taxId = sc.nextLine();

        try {
            supplierUseCase.registerSupplier(name, phone, email, address, taxId);
            System.out.println("✅ Proveedor registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al obtener a los proveedores: " + e.getMessage());
        }
    }
}