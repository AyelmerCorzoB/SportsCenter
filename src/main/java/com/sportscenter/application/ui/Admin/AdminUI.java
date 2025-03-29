package com.sportscenter.application.ui.Admin;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.service.UserService;

import java.util.List;
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
            System.out.println("Usuario actual: " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
            System.out.println("1. Registrar nuevo usuario administrativo");
            System.out.println("2. Listar todos los usuarios");
            System.out.println("3. Panel de control");
            System.out.println("4. Cerrar sesion");
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
            
            ConsoleUtils.pressEnterToContinue(scanner);
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
        controlAdmin.start();}

    private void registrarAdminUser() {
        ConsoleUtils.clear();
        System.out.println("\n--- Registro de usuario administrativo ---");
        
        try {
            User adminUser = solicitarDatosAdmin();
            User registeredUser = userService.register(adminUser, true);
            
            if (registeredUser != null) {
                System.out.println("\n✅ Usuario administrativo registrado con éxito!");
                System.out.println("|------------------------------|");
                System.out.printf("| %-15s: %-10s |\n", "ID", registeredUser.getId());
                System.out.printf("| %-15s: %-10s |\n", "Usuario", registeredUser.getUsername());
                System.out.printf("| %-15s: %-10s |\n", "Rol", registeredUser.getRole());
                System.out.println("|------------------------------|");
            }
        } catch (Exception e) {
            System.out.println("\nX Error durante el registro: " + e.getMessage());
        }
    }

    private User solicitarDatosAdmin() {
        String username;
        do {
            System.out.print("Username (mín. 3 caracteres): ");
            username = scanner.nextLine().trim();
        } while (username.length() < 3);
        
        String password;
        do {
            System.out.print("Password (mín. 8 caracteres): ");
            password = scanner.nextLine().trim();
        } while (password.length() < 8);
        
        String role = seleccionarRol();
        
        return new User(username, password, role);
    }
    
    private String seleccionarRol() {
        System.out.println("\nSeleccione el rol:");
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
        ConsoleUtils.clear();
        System.out.println("\n--- Listado de Usuarios ---");
        
        try {
            List<User> users = userService.getAllUsers();
            
            if (users.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
                return;
            }
            
            System.out.println("|------------------------------------------------------------------------|");
            System.out.println("| ID  | Username           | Fecha Creación     | Rol      | Activo      |");
            System.out.println("|------------------------------------------------------------------------|");
            
            for (User user : users) {
                System.out.printf("| %-3d | %-18s | %-18s | %-8s | %-12s |\n",
                    user.getId(),
                    user.getUsername(),
                    user.getCreated_at().toString().substring(0, 16),
                    user.getRole(),
                    user.isActive() ? "Sí" : "No");
            }
            
            System.out.println("|-------------------------------------------------------------------------|");
            System.out.println("\nTotal de usuarios: " + users.size());
        } catch (Exception e) {
            System.out.println("X Error al obtener la lista de usuarios: " + e.getMessage());
        }
    }
}