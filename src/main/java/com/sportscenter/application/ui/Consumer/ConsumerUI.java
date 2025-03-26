package com.sportscenter.application.ui.Consumer;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.menus.MenuClientes;
import com.sportscenter.application.usecase.product.ListarProducts;
import com.sportscenter.application.usecase.product.ProductUseCase;
import com.sportscenter.application.usecase.invoice.ListarInvoice;
import com.sportscenter.domain.entities.User;

import java.util.Scanner;

public class ConsumerUI {
    private final Scanner scanner;
    private final ProductUseCase productUseCase;
    private final User currentUser;
    private final ListarProducts listarProducts;
    private final ListarOrdersPorUsuario listarOrdersPorUsuario;
    private final ListarInvoice listarInvoice;
    private final ActualizarPassword actualizarPassword;

    public ConsumerUI(Scanner scanner,
                  ProductUseCase productUseCase,
                  User currentUser,
                  ListarProducts listarProducts,
                  ListarOrdersPorUsuario listarOrdersPorUsuario,
                  ListarInvoice listarInvoice,
                  ActualizarPassword actualizarPassword) {
    this.scanner = scanner;
    this.productUseCase = productUseCase;
    this.currentUser = currentUser;
    this.listarProducts = listarProducts;
    this.listarOrdersPorUsuario = listarOrdersPorUsuario;
    this.listarInvoice = listarInvoice;
    this.actualizarPassword = actualizarPassword;
}


    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            ConsoleUtils.clear();
            MenuClientes.mostrarMenu();
            opcion = obtenerOpcionValida();

            switch (opcion) {
                case 1 -> {
                    listarProducts.listar(productUseCase);
                    ConsoleUtils.pressEnterToContinue(scanner);
                }
                case 2 -> {
                    mostrarHistorialCompras();
                    ConsoleUtils.pressEnterToContinue(scanner);
                }
                case 3 -> {
                    mostrarFacturacion();
                    ConsoleUtils.pressEnterToContinue(scanner);
                }
                case 4 -> mostrarPerfil();
                case 5 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private int obtenerOpcionValida() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
    }

    private void mostrarHistorialCompras() {
        System.out.println("\n--- HISTORIAL DE COMPRAS ---");
        listarOrdersPorUsuario.mostrarPorUsuario(currentUser.getId());
    }

    private void mostrarFacturacion() {
        System.out.println("\n--- FACTURACIÓN ---");
        listarInvoice.mostrarFacturasPorUsuario(currentUser.getId());
    }

    private void mostrarPerfil() {
        int opcion;
        do {
            System.out.println("\n--- MI PERFIL ---");
            System.out.println("Usuario: " + currentUser.getUsername());
            System.out.println("Email: " + currentUser.getEmail());
            System.out.println("Rol: " + currentUser.getRole());

            System.out.println("\n1. Cambiar contraseña");
            System.out.println("2. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            opcion = obtenerOpcionValida();
            if (opcion == 1) {
                cambiarContraseña();
            }
        } while (opcion != 2);
    }

    private void cambiarContraseña() {
        String nuevaContraseña;
        do {
            System.out.print("\nIngrese nueva contraseña: ");
            nuevaContraseña = scanner.nextLine();
            if (nuevaContraseña.isBlank()) {
                System.out.println("La contraseña no puede estar vacía.");
            }
        } while (nuevaContraseña.isBlank());

        actualizarPassword.cambiar(currentUser.getId(), nuevaContraseña);
        System.out.println("✅ Contraseña actualizada exitosamente.");
    }
}
