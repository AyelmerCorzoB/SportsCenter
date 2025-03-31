package com.sportscenter.application.usecase.supplier;

import java.util.Scanner;

public class RegisterSupplier {
    public static void Register(Scanner sc, SupplierUseCase supplierUseCase) {
        System.out.println("\n=== REGISTRO DE PROVEEDOR ===");

        System.out.print("Nombre: ");
        String name = getValidatedString(sc);

        System.out.print("Teléfono: ");
        int phone = getValidatedInt(sc);

        sc.nextLine();

        System.out.print("Dirección: ");
        String address = getValidatedString(sc);

        System.out.print("RUC/NIT: ");
        String taxId = getValidatedString(sc);

        try {
            supplierUseCase.registerSupplier(name, phone, address, taxId);
            System.out.println(":D Proveedor registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al obtener a los proveedores: " + e.getMessage());
        }
    }

    private static String getValidatedString(Scanner sc) {
        String input;
        while (true) {
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("¡El campo no puede estar vacío! Por favor, ingrese nuevamente.");
            } else {
                return input;
            }
        }
    }

    private static int getValidatedInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("¡Por favor ingrese un número válido!");
            sc.next();
        }
        return sc.nextInt();
    }

}