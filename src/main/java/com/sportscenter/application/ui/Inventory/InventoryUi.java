package com.sportscenter.application.ui.Inventory;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.application.usecase.product.ListProducts;
import com.sportscenter.application.usecase.product.ProductUseCase;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.ProductRepository;
import com.sportscenter.domain.service.UserService;

import java.util.Scanner;

public class InventoryUi {
    private final Scanner scanner;
    private final ProductUseCase productUseCase;
    private final User currentUser;
    private final ProductUI productUi;
    private final UserService userService;

    public InventoryUi(Scanner scanner,
            ProductUseCase productUseCase,
            ListProducts ListProducts,
            User currentUser,
            ProductRepository productRepository,
            UserService userService) {
        this.scanner = scanner;
        this.productUseCase = productUseCase;
        this.currentUser = currentUser;
        this.userService = userService;
        this.productUi = new ProductUI(scanner, productUseCase,userService);
    }

    public void mostrarMenu() {
        while (true) {
            mostrarEncabezado();
            mostrarOpcionesMenu();

            int opcion = obtenerOpcionUsuario();

            if (!procesarOpcion(opcion)) {
                return;
            }
        }
    }

    private void mostrarEncabezado() {
        ConsoleUtils.clear();
        System.out.println("\n=== GESTIÓN DE INVENTARIO ===");
        System.out.println("Usuario: " + currentUser.getUsername());
        System.out.println("--------------------------------");
    }

    private void mostrarOpcionesMenu() {
        System.out.println("1. Listar todos los productos");
        System.out.println("2. Agregar nuevo producto");
        System.out.println("3. Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Buscar producto por ID");
        System.out.println("6. Volver al menú principal");
        System.out.print("\nSeleccione una opción: ");
    }

    private int obtenerOpcionUsuario() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Ingrese un número: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
    }

    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> {
                productUi.ListProductos();
                ConsoleUtils.pressEnterToContinue(scanner);
            }
            case 2 -> {
                productUi.agregarProducto();
                ConsoleUtils.pressEnterToContinue(scanner);
            }
            case 3 -> {
                productUi.UpdateProducto();
                ConsoleUtils.pressEnterToContinue(scanner);
            }
            case 4 -> {
                productUi.DeleteProducto();
                ConsoleUtils.pressEnterToContinue(scanner);
            }
            case 5 -> {
                productUi.SearchProductoPorId();
                ConsoleUtils.pressEnterToContinue(scanner);
            }
            case 6 -> {
                return false;
            }
            default -> {
                System.out.println("Opción inválida. Intente nuevamente.");
                ConsoleUtils.pressEnterToContinue(scanner);
            }
        }
        return true;
    }
    
}