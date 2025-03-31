package com.sportscenter.application.usecase.product;

import java.time.LocalDate;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Product;
import com.sportscenter.domain.entities.User;

public class UpdateProduct {
    public void Update(Scanner sc, ProductUseCase productUseCase, User currentUser) {
        System.out.println("\n=== ACTUALIZAR PRODUCTO ===");
        System.out.println("En parentesis aparecen los datos actuales");
        System.out.print("ID del producto a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        if (currentUser == null || currentUser.getId() <= 0) {
            System.out.println(" No se ha iniciado sesión. Registre un producto como administrador.");
            return;
        }

        Product existingProduct = null;
        try {
            existingProduct = productUseCase.getProductById(id);
            if (existingProduct == null) {
                System.out.println(" No se encontró un producto con el ID " + id);
                return;
            }
        } catch (Exception e) {
            System.out.println(" Error al obtener el producto: " + e.getMessage());
            return;
        }

        System.out.print("Nombre (" + existingProduct.getName() + "): ");
        String newName = sc.nextLine();
        if (newName.trim().isEmpty()) {
            newName = existingProduct.getName();
        }

        System.out.print("Descripción (" +
                (existingProduct.getDescription() != null ? existingProduct.getDescription() : "[vacío]") + "): ");
        String newDescription = sc.nextLine();
        if (newDescription.trim().isEmpty()) {
            newDescription = existingProduct.getDescription();
        }

        double newUnitPrice;
        do {
            System.out.print("Precio unitario (" + existingProduct.getUnitPrice() + "): ");
            String priceInput = sc.nextLine();
            if (priceInput.trim().isEmpty()) {
                newUnitPrice = existingProduct.getUnitPrice();
                break;
            }
            try {
                newUnitPrice = Double.parseDouble(priceInput);
                if (newUnitPrice < 0) {
                    System.out.println("El precio no puede ser negativo");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido");
                newUnitPrice = -1;
            }
        } while (newUnitPrice < 0);

        String newSize;
        do {
            System.out.println("Tallas disponibles: S, M, L, XL");
            System.out.print(
                    "Talla (" + (existingProduct.getSize() != null ? existingProduct.getSize() : "[vacío]") + "): ");
            newSize = sc.nextLine().toUpperCase();
            if (newSize.isEmpty()) {
                newSize = existingProduct.getSize();
                break;
            }
            if (!newSize.matches("^[SMLXL]$")) {
                System.out.println("Talla inválida. Use S, M, L, XL o deje vacío");
            }
        } while (!newSize.matches("^[SMLXL]$") && !newSize.isEmpty());
        if (newSize != null && newSize.isEmpty())
            newSize = null;

        int newCurrentStock;
        do {
            System.out.print("Stock actual (" + existingProduct.getCurrentStock() + "): ");
            String stockInput = sc.nextLine();
            if (stockInput.trim().isEmpty()) {
                newCurrentStock = existingProduct.getCurrentStock();
                break;
            }
            try {
                newCurrentStock = Integer.parseInt(stockInput);
                if (newCurrentStock < 0) {
                    System.out.println("El stock no puede ser negativo");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número entero válido");
                newCurrentStock = -1;
            }
        } while (newCurrentStock < 0);

        int newMinimumStock;
        do {
            System.out.print("Stock mínimo (" + existingProduct.getMinimumStock() + "): ");
            String minStockInput = sc.nextLine();
            if (minStockInput.trim().isEmpty()) {
                newMinimumStock = existingProduct.getMinimumStock();
                break;
            }
            try {
                newMinimumStock = Integer.parseInt(minStockInput);
                if (newMinimumStock < 0) {
                    System.out.println("El stock mínimo no puede ser negativo");
                }
                if (newMinimumStock > newCurrentStock) {
                    System.out.println("El stock mínimo no puede ser mayor al stock actual (" + newCurrentStock + ")");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número entero válido");
                newMinimumStock = -1;
            }
        } while (newMinimumStock < 0 || newMinimumStock > newCurrentStock);

        LocalDate entryDate;
        System.out.print(
                "¿Desea ingresar una fecha personalizada? (S/N) [actual: " + existingProduct.getEntryDate() + "]: ");
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
            entryDate = existingProduct.getEntryDate();
            System.out.println("Se mantendrá la fecha actual: " + entryDate);
        }

        int categoryId;
        do {
            System.out.print("ID de categoría (" + existingProduct.getCategoryId() + "): ");
            String catInput = sc.nextLine();
            if (catInput.trim().isEmpty()) {
                categoryId = existingProduct.getCategoryId();
                break;
            }
            try {
                categoryId = Integer.parseInt(catInput);
                if (categoryId <= 0) {
                    System.out.println("El ID de categoría debe ser positivo");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número entero válido");
                categoryId = -1;
            }
        } while (categoryId <= 0);

        int supplierId;
        do {
            System.out.print("ID de proveedor (" + existingProduct.getSupplierId() + "): ");
            String supInput = sc.nextLine();
            if (supInput.trim().isEmpty()) {
                supplierId = existingProduct.getSupplierId();
                break;
            }
            try {
                supplierId = Integer.parseInt(supInput);
                if (supplierId <= 0) {
                    System.out.println("El ID de proveedor debe ser positivo");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número entero válido");
                supplierId = -1;
            }
        } while (supplierId <= 0);

        int colorId;
        do {
            System.out.print("ID de color (" + existingProduct.getColorId() + "): ");
            String colInput = sc.nextLine();
            if (colInput.trim().isEmpty()) {
                colorId = existingProduct.getColorId();
                break;
            }
            try {
                colorId = Integer.parseInt(colInput);
                if (colorId <= 0) {
                    System.out.println("El ID de color debe ser positivo");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número entero válido");
                colorId = -1;
            }
        } while (colorId <= 0);

        try {
            productUseCase.updateProduct(id, newName,
                    newDescription != null && newDescription.isEmpty() ? null : newDescription,
                    newUnitPrice, newSize, newCurrentStock, newMinimumStock,
                    entryDate, categoryId, supplierId, colorId, currentUser.getId());
            System.out.println(":D Producto actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println(" Error al actualizar producto: " + e.getMessage());
        }
    }
}