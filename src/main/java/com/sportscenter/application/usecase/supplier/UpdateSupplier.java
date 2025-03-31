package com.sportscenter.application.usecase.supplier;

import java.util.Scanner;


public class UpdateSupplier {
    public static void update(Scanner sc, SupplierUseCase supplierUseCase) {
        System.out.println("\n=== ACTUALIZAR PROVEEDOR ===");
    
        System.out.print("Ingrese el ID a actualizar: ");
        int id = getValidatedInt(sc);
        sc.nextLine();  // Consumir el salto de línea pendiente
    
        System.out.print("Nuevo Nombre: ");
        String name = getValidatedString(sc);
    
        System.out.print("Nuevo Teléfono: ");
        int phone = getValidatedInt(sc);
        sc.nextLine();  // Consumir el salto de línea pendiente
    
        System.out.print("Nueva Dirección: ");
        String address = getValidatedString(sc);
    
        System.out.print("Nueva RUC/NIT: ");
        String taxId = getValidatedString(sc);
    
        try {
            supplierUseCase.updateSupplier(id, name, phone, address, taxId);
            System.out.println(":D Proveedor Actualizado exitosamente.");
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
            sc.next();  // Descarta la entrada no válida
        }
        return sc.nextInt();
    }

}