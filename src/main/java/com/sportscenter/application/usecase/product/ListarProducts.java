package com.sportscenter.application.usecase.product;

public class ListarProducts {
    public void listar(ProductUseCase productUseCase) {
        System.out.println("\n=== LISTADO DE PRODUCTOS ===");
        System.out.printf("%-5s %-20s %-10s %-8s %-6s %-6s %-15s%n", 
                         "ID", "NOMBRE", "PRECIO", "TALLA", "STOCK", "MIN", "FECHA INGRESO");
        System.out.println("------------------------------------------------------------------");
        
        productUseCase.getAllProducts().forEach(p -> 
            System.out.printf("%-5d %-20s %-10.2f %-8s %-6d %-6d %-15s%n", 
                p.getId(), 
                p.getName(), 
                p.getUnitPrice(), 
                p.getSize(), 
                p.getCurrentStock(), 
                p.getMinimumStock(), 
                p.getEntryDate())
        );
    }
}