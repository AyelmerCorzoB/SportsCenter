package com.sportscenter.application.ui;

import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.service.UserService;
import java.util.Scanner;

public class AdminUI {
    private final Scanner scanner;
    private final UserService userService;
    private final User currentUser;

    public AdminUI(Scanner scanner, UserService userService, User currentUser) {
        this.scanner = scanner;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n--- Panel de Administración ---");
            System.out.println("1. Registrar nuevo usuario administrativo");
            System.out.println("2. Listar todos los usuarios");
            System.out.println("3. Volver al menú principal");
            System.out.print("Elija una opción: ");

            int option = obtenerOpcionValida();

            switch (option) {
                case 1 -> registrarAdminUser();
                case 2 -> listarUsuarios();
                case 3 -> { return; }
                default -> System.out.println("Opción inválida.");
            }
        }
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

    private void registrarAdminUser() {
        System.out.println("\n--- Registro de usuario administrativo ---");
        User adminUser = solicitarDatosAdmin();
        boolean registered = userService.register(adminUser, true);

        System.out.println(registered ? 
            "\nUsuario administrativo registrado con éxito!" :
            "\nError al registrar usuario administrativo.");
    }

    private User solicitarDatosAdmin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        String role = seleccionarRol();

        return new User(username, email, password, role);
    }

    private String seleccionarRol() {
        System.out.println("Seleccione el rol:");
        System.out.println("1. ADMIN");
        System.out.println("2. CASHIER");
        System.out.println("3. INVENTORY");
        System.out.print("Opción: ");

        int roleOption = obtenerOpcionValida();

        return switch (roleOption) {
            case 1 -> "ADMIN";
            case 2 -> "CASHIER";
            case 3 -> "INVENTORY";
            default -> {
                System.out.println("Opción inválida. Se asignará rol CASHIER por defecto.");
                yield "CASHIER";
            }
        };
    }

    private void listarUsuarios() {
        System.out.println("\n--- Listado de Usuarios ---");
        System.out.println("Funcionalidad de listado pendiente de implementar.");
    }
}