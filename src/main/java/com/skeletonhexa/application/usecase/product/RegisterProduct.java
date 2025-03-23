package com.skeletonhexa.application.usecase.product;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.skeletonhexa.application.usecase.category.CategoryUseCase;
import com.skeletonhexa.application.usecase.supplier.SupplierUseCase;
import com.skeletonhexa.domain.entities.Category;
import com.skeletonhexa.domain.entities.Supplier;

public class RegisterProduct {
    private final CategoryUseCase categoryUseCase;
    private final SupplierUseCase supplierUseCase;

    public RegisterProduct(CategoryUseCase categoryUseCase, SupplierUseCase supplierUseCase) {
        this.categoryUseCase = categoryUseCase;
        this.supplierUseCase = supplierUseCase;
    }

    public void register(Scanner sc, ProductUseCase productUseCase) {
        try {
            System.out.print("Ingrese el nombre del producto: ");
            String name = sc.nextLine();

            System.out.print("Ingrese la descripción del producto: ");
            String description = sc.nextLine();

            double unitPrice;
            while (true) {
                System.out.print("Ingrese el precio unitario del producto: ");
                String input = sc.nextLine();
                try {
                    unitPrice = Double.parseDouble(input);
                    if (unitPrice <= 0) {
                        throw new IllegalArgumentException("El precio unitario debe ser un número positivo.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Error: El precio unitario debe ser un número válido.");
                }
            }

            System.out.print("Ingrese el tamaño del producto: ");
            String size = sc.nextLine();

            System.out.print("Ingrese el color del producto: ");
            String color = sc.nextLine();

            int currentStock;
            while (true) {
                System.out.print("Ingrese el stock actual del producto: ");
                String input = sc.nextLine();
                try {
                    currentStock = Integer.parseInt(input);
                    if (currentStock < 0) {
                        throw new IllegalArgumentException("El stock actual no puede ser negativo.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("❌ Error: El stock actual debe ser un número entero.");
                }
            }

            int minimumStock;
            while (true) {
                System.out.print("Ingrese el stock mínimo del producto: ");
                String input = sc.nextLine();
                try {
                    minimumStock = Integer.parseInt(input);
                    if (minimumStock < 0) {
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

            Date sqlEntryDate = Date.valueOf(entryDate);

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
            productUseCase.registerProduct(name, description, unitPrice, size, color, currentStock, minimumStock,
                    sqlEntryDate, category, supplier);
            System.out.println("✅ Producto registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
}