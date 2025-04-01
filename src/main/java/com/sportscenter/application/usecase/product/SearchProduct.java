package com.sportscenter.application.usecase.product;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Product;

public class SearchProduct {
    public void Search(Scanner sc, ProductUseCase productUseCase) {
        System.out.println("\n=== BUSCAR PRODUCTO ===");

        System.out.print("ID del producto: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        Product product = productUseCase.getProductById(id);
        if (product != null) {
            System.out.println("╔═════════════════════════════════════════════════════════╗");
            System.out.println("║                INFORMACIÓN DEL PRODUCTO                 ║");
            System.out.println("╠═════════════════════════════════════════════════════════╣");
            System.out.println(String.format("║   %-15s: %-34s   ║", "ID", product.getId()));
            System.out.println(String.format("║   %-15s: %-34s   ║", "Nombre", product.getName()));
            System.out.println(String.format("║   %-15s: %-34s   ║", "Descripción",
                    product.getDescription() != null ? (product.getDescription().length() > 34
                            ? product.getDescription().substring(0, 31) + "..."
                            : product.getDescription()) : "N/A"));
            System.out.println(String.format("║   %-15s: $%-33.2f   ║", "Precio", product.getUnitPrice()));
            System.out.println(String.format("║   %-15s: %-34s   ║", "Talla", product.getSize()));
            System.out.println(String.format("║   %-15s: %-34s   ║", "Stock",
                    product.getCurrentStock() + " (Mín: " + product.getMinimumStock() + ")"));
            System.out.println(String.format("║   %-15s: %-34s   ║", "Fecha ingreso", product.getEntryDate()));
            System.out.println(String.format("║   %-15s: %-34s   ║", "Categoría ID", product.getCategoryId()));
            System.out.println(String.format("║   %-15s: %-34s   ║", "Proveedor ID", product.getSupplierId()));
            System.out.println(String.format("║   %-15s: %-34s   ║", "Color ID", product.getColorId()));
            System.out.println("╚═════════════════════════════════════════════════════════╝");
        } else {
            System.out.println("X No se encontró el producto con ID: " + id);
        }
    }
}