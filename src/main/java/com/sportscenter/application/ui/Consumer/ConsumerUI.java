package com.sportscenter.application.ui.Consumer;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.menus.MenuClientes;
import com.sportscenter.application.usecase.product.ListProducts;
import com.sportscenter.application.usecase.product.ProductUseCase;
import com.sportscenter.application.usecase.invoice.ListInvoice;
import com.sportscenter.application.usecase.invoice.InvoiceUseCase;
import com.sportscenter.domain.entities.User;

import java.util.Scanner;

public class ConsumerUI {
    private final Scanner scanner;
    private final ProductUseCase productUseCase;
    private final User currentUser;
    private final ListProducts ListProducts;
    private final ListSalesPorUsuario ListSalesPorUsuario;
    private final ListInvoice ListInvoice;
    private final InvoiceUseCase invoiceUseCase;
    private final UpdatePassword UpdatePassword;

    public ConsumerUI(Scanner scanner,
            ProductUseCase productUseCase,
            User currentUser,
            ListProducts ListProducts,
            ListSalesPorUsuario ListSalesPorUsuario,
            ListInvoice ListInvoice,
            UpdatePassword UpdatePassword,
            InvoiceUseCase invoiceUseCase) {
        this.scanner = scanner;
        this.productUseCase = productUseCase;
        this.currentUser = currentUser;
        this.ListProducts = ListProducts;
        this.ListSalesPorUsuario = ListSalesPorUsuario;
        this.ListInvoice = ListInvoice;
        this.UpdatePassword = UpdatePassword;
        this.invoiceUseCase = invoiceUseCase;
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            ConsoleUtils.clear();
            MenuClientes.mostrarMenu();
            opcion = obtenerOpcionValida();

            switch (opcion) {
                case 1 -> {
                    ListProducts.List(productUseCase);
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
        scanner.nextLine();
        return opcion;
    }

    private void mostrarHistorialCompras() {
        System.out.println("\n--- HISTORIAL DE COMPRAS ---");
        ListSalesPorUsuario.mostrarPorUsuario(currentUser.getId());
    }

    private void mostrarFacturacion() {
        System.out.println("\n--- HISTORIAL DE FACTURAS ---");
        ListInvoice.mostrarFacturasPorUsuario(currentUser.getId(), invoiceUseCase);
    }

    private void mostrarPerfil() {
        int opcion;
        do {
            System.out.println("\n--- MI PERFIL ---");
            System.out.println("Usuario: " + currentUser.getUsername());
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

        UpdatePassword.cambiar(currentUser.getId(), nuevaContraseña);
        System.out.println(":D Contraseña actualizada exitosamente.");
    }
}