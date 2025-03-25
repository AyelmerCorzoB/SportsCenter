package com.sportscenter.application.usecase.product;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Product;

public class BuscarProduct {
    public void buscar(Scanner sc, ProductUseCase productUseCase) {
        System.out.println("\n=== BUSCAR PRODUCTO ===");
        
        System.out.print("ID del producto: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        Product product = productUseCase.getProductById(id);
        if(product != null) {
            System.out.println("\nInformación del producto:");
            System.out.println("ID: " + product.getId());
            System.out.println("Nombre: " + product.getName());
            System.out.println("Descripción: " + (product.getDescription() != null ? product.getDescription() : "N/A"));
            System.out.println("Precio: $" + product.getUnitPrice());
            System.out.println("Talla: " + product.getSize());
            System.out.println("Stock: " + product.getCurrentStock() + " (Mín: " + product.getMinimumStock() + ")");
            System.out.println("Fecha ingreso: " + product.getEntryDate());
            System.out.println("Categoría ID: " + product.getCategoryId());
            System.out.println("Proveedor ID: " + product.getSupplierId());
            System.out.println("Color ID: " + product.getColorId());
        } else {
            System.out.println("❌ No se encontró el producto con ID: " + id);
        }
    }
}