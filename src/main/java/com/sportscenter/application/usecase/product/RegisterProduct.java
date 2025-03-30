package com.sportscenter.application.usecase.product;

import java.time.LocalDate;
import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationDouble;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.User;

public class RegisterProduct {

    public void Register(Scanner sc, ProductUseCase productUseCase, User currentUser) {
        System.out.println("\n=== REGISTRO DE PRODUCTO ===");

        if (currentUser == null || currentUser.getId() <= 0) {
            System.out.println("❌ No se ha iniciado sesión. Registre un producto como administrador.");
            return;
        }

        System.out.print("Nombre: ");
        String name = sc.nextLine();
        while (name.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío");
            System.out.print("Nombre: ");
            name = sc.nextLine();
        }

        System.out.print("Descripción (opcional): ");
        String description = sc.nextLine();

        double unitPrice;
        do {
            System.out.print("Precio unitario: ");
            ValidationDouble.validate(sc);
            unitPrice = sc.nextDouble();
            if (unitPrice < 0) {
                System.out.println("El precio no puede ser negativo");
            }
        } while (unitPrice < 0);
        sc.nextLine();

        String size;
        do {
            System.out.println("Tallas disponibles: S, M, L, XL");
            System.out.print("Talla: ");
            size = sc.nextLine().toUpperCase();
            if (!size.matches("^[SMLXL]$") && !size.isEmpty()) {
                System.out.println("Talla inválida. Use S, M, L, XL o deje vacío");
            }
        } while (!size.matches("^[SMLXL]$") && !size.isEmpty());
        if (size.isEmpty())
            size = null;

        int currentStock;
        do {
            System.out.print("Stock actual: ");
            ValidationInt.validate(sc);
            currentStock = sc.nextInt();
            if (currentStock < 0) {
                System.out.println("El stock no puede ser negativo");
            }
        } while (currentStock < 0);
        sc.nextLine();

        int minimumStock;
        do {
            System.out.print("Stock mínimo: ");
            ValidationInt.validate(sc);
            minimumStock = sc.nextInt();
            if (minimumStock < 0) {
                System.out.println("El stock mínimo no puede ser negativo");
            }
            if (minimumStock > currentStock) {
                System.out.println("El stock mínimo no puede ser mayor al stock actual (" + currentStock + ")");
            }
        } while (minimumStock < 0 || minimumStock > currentStock);
        sc.nextLine();

        LocalDate entryDate;
        System.out.print("¿Desea ingresar una fecha personalizada? (S/N): ");
        String opcionFecha = sc.nextLine().trim().toUpperCase();

        if (opcionFecha.equals("S")) {
            while (true) {
                try {
                    System.out.print("Ingrese fecha (YYYY-MM-DD): ");
                    String entryDateStr = sc.nextLine();
                    entryDate = LocalDate.parse(entryDateStr);
                    if (entryDate.isAfter(LocalDate.now())) {
                        System.out.println("La fecha no puede ser futura");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Formato de fecha inválido. Use YYYY-MM-DD");
                }
            }
        } else {
            entryDate = LocalDate.now();
            System.out.println("Se asignará la fecha actual: " + entryDate);
        }

        int categoryId;
        do {
            System.out.print("ID de categoría: ");
            ValidationInt.validate(sc);
            categoryId = sc.nextInt();
        } while (categoryId <= 0);
        sc.nextLine();

        int supplierId;
        do {
            System.out.print("ID de proveedor: ");
            ValidationInt.validate(sc);
            supplierId = sc.nextInt();
        } while (supplierId <= 0);
        sc.nextLine();

        int colorId;
        do {
            System.out.print("ID de color: ");
            ValidationInt.validate(sc);
            colorId = sc.nextInt();
        } while (colorId <= 0);
        sc.nextLine();

        try {
            productUseCase.registerProduct(
                    name,
                    description.isEmpty() ? null : description,
                    unitPrice,
                    size,
                    currentStock,
                    minimumStock,
                    entryDate,
                    categoryId,
                    supplierId,
                    colorId,
                    currentUser.getId());
            System.out.println("🚀 Producto registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al registrar producto: " + e.getMessage());
        }
    }
}