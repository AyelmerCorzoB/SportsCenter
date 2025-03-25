package com.sportscenter.application.usecase.product;

import java.time.LocalDate;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationDouble;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationString;

public class ActualizarProduct {
    public void actualizar(Scanner sc, ProductUseCase productUseCase) {
        System.out.println("\n=== ACTUALIZAR PRODUCTO ===");

        System.out.print("ID del producto a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo nombre: ");
        ValidationString.validate(sc);
        String newName = sc.nextLine();
        System.out.print("Descripción (opcional): ");
        String newDescription = sc.nextLine();
        System.out.print("Precio unitario: ");
        ValidationDouble.validate(sc);
        double newUnitPrice = sc.nextDouble();
        sc.nextLine();
        System.out.println("Tallas disponibles: S, M, L, XL");
        System.out.print("Talla: ");
        String newSize = sc.nextLine().toUpperCase();
        System.out.print("Stock actual: ");
        ValidationInt.validate(sc);
        int newCurrentStock = sc.nextInt();
        sc.nextLine();
        System.out.print("Stock mínimo: ");
        ValidationInt.validate(sc);
        int newMinimumStock = sc.nextInt();
        sc.nextLine();
        System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
        String entryDateStr = sc.nextLine();
        LocalDate entryDate = LocalDate.parse(entryDateStr);
        System.out.print("ID de categoría: ");
        ValidationInt.validate(sc);
        int categoryId = sc.nextInt();
        sc.nextLine();
        System.out.print("ID de proveedor: ");
        ValidationInt.validate(sc);
        int supplierId = sc.nextInt();
        sc.nextLine();
        System.out.print("ID de color: ");
        ValidationInt.validate(sc);
        int colorId = sc.nextInt();
        sc.nextLine();

        productUseCase.updateProduct(id, newName, newDescription.isEmpty() ? null : newDescription, newUnitPrice,
                newSize, newCurrentStock, newMinimumStock, entryDate, categoryId, supplierId, colorId, colorId);
        System.out.println("✅ Producto actualizado exitosamente.");
    }
}