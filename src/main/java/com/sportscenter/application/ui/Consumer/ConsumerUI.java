package com.sportscenter.application.ui.Consumer;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.menus.MenuClientes;
import com.sportscenter.application.usecase.product.ListarProducts;
import com.sportscenter.application.usecase.product.ProductUseCase;
import com.sportscenter.domain.entities.User;

import java.util.Scanner;

public class ConsumerUI {
    private final Scanner scanner;
    private final ProductUseCase productUseCase;
    private final User currentUser;
    private final ListarProducts listarProducts;

    public ConsumerUI(Scanner scanner, ProductUseCase productUseCase, User currentUser) {
        this.scanner = scanner;
        this.productUseCase = productUseCase;
        this.currentUser = currentUser;
        this.listarProducts = new ListarProducts();
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            ConsoleUtils.clear();
            MenuClientes.mostrarMenu();
            opcion = obtenerOpcionValida();
            
            switch (opcion) {
                case 1:
                    listarProducts.listar(productUseCase);
                    ConsoleUtils.pressEnterToContinue(scanner);
                    break;
                case 2:
                    mostrarHistorialCompras();
                    break;
                case 3:
                    mostrarFacturacion();
                    break;
                case 4:
                    mostrarPerfil();
                    break;
                case 5:
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private int obtenerOpcionValida() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        return opcion;
    }

    private void mostrarHistorialCompras() {
        System.out.println("\n--- HISTORIAL DE COMPRAS ---");
        // Implementar lógica para mostrar compras del usuario
        System.out.println("Función en desarrollo...");
    }

    private void mostrarFacturacion() {
        System.out.println("\n--- FACTURACIÓN ---");
        // Implementar lógica para mostrar facturas
        System.out.println("Función en desarrollo...");
    }

    private void mostrarPerfil() {
        System.out.println("\n--- MI PERFIL ---");
        System.out.println("Usuario: " + currentUser.getUsername());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Rol: " + currentUser.getRole());
        
        System.out.println("\n1. Cambiar contraseña");
        System.out.println("2. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        
        int opcion = obtenerOpcionValida();
        
        if (opcion == 1) {
            cambiarContraseña();
        }
    }

    private void cambiarContraseña() {
        System.out.print("\nIngrese nueva contraseña: ");
        String nuevaContraseña = scanner.nextLine();
        // Lógica para cambiar contraseña
        System.out.println("Contraseña actualizada exitosamente (simulado)");
    }
}