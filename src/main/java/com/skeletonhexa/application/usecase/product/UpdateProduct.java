package com.skeletonhexa.application.usecase.product;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.skeletonhexa.application.usecase.category.CategoryUseCase;
import com.skeletonhexa.application.usecase.supplier.SupplierUseCase;
import com.skeletonhexa.domain.entities.Category;
import com.skeletonhexa.domain.entities.Supplier;

public class UpdateProduct {
    private final CategoryUseCase categoryUseCase;
    private final SupplierUseCase supplierUseCase;

    public UpdateProduct(CategoryUseCase categoryUseCase, SupplierUseCase supplierUseCase) {
        this.categoryUseCase = categoryUseCase;
        this.supplierUseCase = supplierUseCase;
    }

    public void update(Scanner sc, ProductUseCase productUseCase) {
        try {
            System.out.print("Ingrese el nombre del producto: ");
            String newName = sc.nextLine();

            System.out.print("Ingrese la descripción del producto: ");
            String newDescription = sc.nextLine();

            double newUnitPrice;
            while (true) {
                System.out.print("Ingrese el precio unitario del producto: ");
                String input = sc.nextLine();
                try {
                    newUnitPrice = Double.parseDouble(input);
                    if (newUnitPrice <= 0) {
                        throw new IllegalArgumentException("El precio unitario debe ser un número positivo.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Error: El precio unitario debe ser un número válido.");
                }
            }

            System.out.print("Ingrese el tamaño del producto: ");
            String newSize = sc.nextLine();

            System.out.print("Ingrese el newColor del producto: ");
            String newColor = sc.nextLine();

            int newCurrentStock;
            while (true) {
                System.out.print("Ingrese el stock actual del producto: ");
                String input = sc.nextLine();
                try {
                    newCurrentStock = Integer.parseInt(input);
                    if (newCurrentStock < 0) {
                        throw new IllegalArgumentException("El stock actual no puede ser negativo.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Error: El stock actual debe ser un número entero.");
                }
            }

            int newMinimumStock;
            while (true) {
                System.out.print("Ingrese el stock mínimo del producto: ");
                String input = sc.nextLine();
                try {
                    newMinimumStock = Integer.parseInt(input);
                    if (newMinimumStock < 0) {
                        throw new IllegalArgumentException("El stock mínimo no puede ser negativo.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Error: El stock mínimo debe ser un número entero.");
                }
            }

            LocalDate entryDate;
            while (true) {
                System.out.print("Ingrese la fecha de entrada del producto (YYYY-MM-DD): ");
                String entryDateStr = sc.nextLine();
                try {
                    entryDate = LocalDate.parse(entryDateStr);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("❌ Error: Formato de fecha inválido. Use YYYY-MM-DD.");
                }
            }

            Date newSqlEntryDate = Date.valueOf(entryDate);

            System.out.print("Ingrese el ID de la categoría: ");
            int categoryId = Integer.parseInt(sc.nextLine());
            Category category = categoryUseCase.getCategory(categoryId);
            if (category == null) {
                System.out.println("❌ Error: No se encontró una categoría con el ID proporcionado.");
                return;
            }

            System.out.print("Ingrese el ID del proveedor: ");
            int supplierId = Integer.parseInt(sc.nextLine());
            Supplier supplier = supplierUseCase.getSupplier(supplierId);
            if (supplier == null) {
                System.out.println("❌ Error: No se encontró un proveedor con el ID proporcionado.");
                return;
            }
            productUseCase.registerProduct(newName, newDescription, newUnitPrice, newSize, newColor, newCurrentStock,
                    newMinimumStock,
                    newSqlEntryDate, category, supplier);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
}