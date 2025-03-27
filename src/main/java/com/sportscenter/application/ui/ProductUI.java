package com.sportscenter.application.ui;

import java.util.List;
import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.product.*;
import com.sportscenter.domain.entities.Product;

public class ProductUI {
    public static void manejarMenuProduct(Scanner sc, ProductUseCase productUseCase) {
    int opcion;
    ConsoleUtils.clear();
    do {
        System.out.println("\n******** MENÚ DE PRODUCTOS ********");
        System.out.println("1. Registrar producto");
        System.out.println("2. Buscar producto por ID");
        System.out.println("3. Listar todos los productos");
        System.out.println("4. Actualizar un producto");
        System.out.println("5. Eliminar un producto");
        System.out.println("6. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        ValidationInt.validate(sc);
        opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                new RegistroProduct().registro(sc, productUseCase);
                break;
            case 2:
                new BuscarProduct().buscar(sc, productUseCase);
                break;
            case 3:
                listarProductos(productUseCase);
                break;
            case 4:
                new ActualizarProduct().actualizar(sc, productUseCase);
                break;
            case 5:
                new EliminarProduct().eliminar(sc, productUseCase);
                break;
            case 6:
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("❌ Opción inválida. Por favor intente nuevamente.");
                break;
        }
    } while (opcion != 6);
}

private static void listarProductos(ProductUseCase productUseCase) {
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
}
