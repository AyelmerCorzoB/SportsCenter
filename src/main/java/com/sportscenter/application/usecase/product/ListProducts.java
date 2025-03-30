package com.sportscenter.application.usecase.product;

import java.util.List;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.domain.entities.Product;
import com.sportscenter.domain.repository.ProductRepository;

public class ListProducts {
    public ListProducts(ProductRepository productRepository) {
        //TODO Auto-generated constructor stub
    }

    public void List(ProductUseCase productUseCase) {
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

    public void ejecutar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ejecutar'");
    }
}