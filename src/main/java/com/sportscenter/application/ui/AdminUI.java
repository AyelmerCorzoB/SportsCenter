package com.sportscenter.application.ui;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.service.UserService;

import java.util.NoSuchElementException;
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
            ConsoleUtils.clear();
            System.out.println("\n--- Panel de Administración ---");
            System.out.println("1. Registrar nuevo usuario administrativo");
            System.out.println("2. Listar todos los usuarios");
            System.out.println("3. Panel de control");
            System.out.println("4. Volver al menú principal");
            System.out.print("Elija una opción: ");

            int option = obtenerOpcionValida();

            switch (option) {
                case 1 -> registrarAdminUser();
                case 2 -> listarUsuarios();
                case 3 -> mostrarPanelControl();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private int obtenerOpcionValida() {
        try {
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
            int opcion = scanner.nextInt();
            scanner.nextLine();
            return opcion;
        } catch (NoSuchElementException e) {
            System.out.println("Error al leer la entrada. Volviendo al menú principal.");
            return 4;
        }
    }

    private void mostrarPanelControl() {
        ControlAdminUi controlAdmin = new ControlAdminUi();
        controlAdmin.start();
    }

    private void registrarAdminUser() {
        System.out.println("\n--- Registro de usuario administrativo ---");
        User adminUser = solicitarDatosAdmin();
        
        try {
            User registeredUser = userService.register(adminUser, true);
        
            if (registeredUser != null) {
                System.out.println("\nUsuario administrativo registrado con éxito!");
                System.out.println("Nombre de usuario: " + registeredUser.getUsername());
                System.out.println("Rol asignado: " + registeredUser.getRole());
                System.out.println("ID de usuario: " + registeredUser.getId());
            } else {
                System.out.println("\nError al registrar usuario administrativo.");
                System.out.println("Posibles causas:");
                System.out.println("- El nombre de usuario ya existe");
                System.out.println("- Error en la base de datos");
            }
        } catch (Exception e) {
            System.out.println("\nError durante el registro: " + e.getMessage());
            e.printStackTrace();
        }
        
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private User solicitarDatosAdmin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        String role = seleccionarRol();
        
        return new User(username, password, role);
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