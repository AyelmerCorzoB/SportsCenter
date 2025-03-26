package com.sportscenter.application.usecase.product;

import java.util.List;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.domain.entities.Product;

public class ListarProducts {
    public void listar(ProductUseCase productUseCase) {
        ConsoleUtils.clear();
        System.out.println("\n=== LISTADO DE PRODUCTOS ===");
        
        List<Product> productos = productUseCase.getAllProducts();
        
        if (productos.isEmpty()) {
            System.out.println("===== NO HAY PRODUCTOS =====");
        } else {
            System.out.printf("%-5s %-20s %-10s %-8s %-6s %-6s %-15s%n",
                    "ID", "NOMBRE", "PRECIO", "TALLA", "STOCK", "MIN", "FECHA INGRESO");
            System.out.println("------------------------------------------------------------------");

            productos.forEach(p -> {
                System.out.printf("%-5d %-20s %-10.2f %-8s %-6d %-6d %-15s%n",
                        p.getId(),
                        p.getName(),
                        p.getUnitPrice(),
                        p.getSize() != null ? p.getSize() : "N/A",
                        p.getCurrentStock(),
                        p.getMinimumStock(),
                        p.getEntryDate());
            });
        }
    }
}