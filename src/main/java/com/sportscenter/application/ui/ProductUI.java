package com.sportscenter.application.ui;

import java.util.List;
import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.product.*;
import com.sportscenter.domain.entities.Product;

public class ProductUI {
    private final ProductUseCase productUseCase;
    private final Scanner scanner;

    public ProductUI(Scanner scanner, ProductUseCase productUseCase) {
        this.scanner = scanner;
        this.productUseCase = productUseCase;
    }

    public void agregarProducto() {
        new RegistroProduct().registro(scanner, productUseCase);
    }

    public void buscarProductoPorId() {
        new BuscarProduct().buscar(scanner, productUseCase);
    }

    public void actualizarProducto() {
        new ActualizarProduct().actualizar(scanner, productUseCase);
    }

    public void eliminarProducto() {
        new EliminarProduct().eliminar(scanner, productUseCase);
    }

    public void listarProductos() {
        List<Product> productos = productUseCase.getAllProducts();
        
        if (productos.isEmpty()) {
            System.out.println("\nNo hay productos registrados.");
            return;
        }
        
        System.out.println("\nLISTADO DE PRODUCTOS");
        System.out.println("+----------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-8s | %-15s | %-15s | %-10s |\n", 
                         "ID", "Nombre", "Descripción", "Precio", "Stock", "Categoría", "Proveedor", "Color");
        System.out.println("+----------------------------------------------------------------------------------------------------------------+");
        
        for (Product p : productos) {
            System.out.printf("| %-5d | %-20s | %-30s | $%-9.2f | %-8d | %-15s | %-15s | %-10s |\n",
                            p.getId(),
                            p.getName(),
                            p.getDescription() != null ? 
                                (p.getDescription().length() > 30 ? 
                                 p.getDescription().substring(0, 27) + "..." : 
                                 p.getDescription()) : "",
                            p.getUnitPrice(),
                            p.getCurrentStock(),
                            p.getCategoryName(),
                            p.getSupplierName(),
                            p.getColorName());
        }
        System.out.println("+----------------------------------------------------------------------------------------------------------------+");
        System.out.println("Total de productos: " + productos.size());
    }

    public void mostrarMenu() {
        boolean volver = false;
        while (!volver) {
            ConsoleUtils.clear();
            System.out.println("\n=== MENÚ DE PRODUCTOS ===");
            System.out.println("1. Listar productos");
            System.out.println("2. Agregar producto");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Volver al menú anterior");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1 -> listarProductos();
                case 2 -> agregarProducto();
                case 3 -> buscarProductoPorId();
                case 4 -> actualizarProducto();
                case 5 -> eliminarProducto();
                case 6 -> volver = true;
                default -> System.out.println("Opción inválida");
            }
            
            if (opcion != 6) {
                ConsoleUtils.pressEnterToContinue(scanner);
            }
        }
    }
}