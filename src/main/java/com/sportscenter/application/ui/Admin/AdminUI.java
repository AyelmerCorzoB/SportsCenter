package com.sportscenter.application.ui.Admin;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.service.UserService;

import java.util.InputMismatchException;
import java.util.List;
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
            final String RESET = "\u001B[0m";
            final String CYAN_BOLD = "\u001B[1;36m";
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║          PANEL DE ADMINISTRACIÓN           ║");
            System.out.printf(CYAN_BOLD + "║       Usuario actual: %-23s  ║%n",
                    currentUser.getUsername() + " (" + currentUser.getRole() + ")" + RESET);
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ 1. Registrar nuevo usuario administrativo  ║");
            System.out.println("║ 2. Listar todos los usuarios               ║");
            System.out.println("║ 3. Panel de control                        ║");
            System.out.println("║ 4. Cerrar sesión                           ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Elija una opción: ");

            int option = obtenerOpcionValida();

            switch (option) {
                case 1 -> registerAdminUser();
                case 2 -> listUsuarios();
                case 3 -> mostrarPanelControl();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }

            ConsoleUtils.pressEnterToContinue(scanner);
        }
    }

    private int obtenerOpcionValida(int min, int max) {
        while (true) {
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();
                
                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
                System.out.printf("Por favor, ingrese un número entre %d y %d: ", min, max);
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor ingrese un número: ");
                scanner.next();
            }
        }
    }

    private int obtenerOpcionValida() {
        return obtenerOpcionValida(1, 4);
    }

    private void mostrarPanelControl() {
        ControlAdminUi adminUi = new ControlAdminUi(scanner, userService, currentUser);
        adminUi.start();
    }

    private void registerAdminUser() {
        ConsoleUtils.clear();
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║    Registro de usuario administrativo   ║");
        System.out.println("╠═════════════════════════════════════════╣");
        
        // Solicitar username
        String username;
        do {
            System.out.print("║ Username (mín. 3 caracteres): ");
            username = scanner.nextLine().trim();
        } while (username.length() < 3);
        
        // Solicitar password
        String password;
        do {
            System.out.print("║ Password (mín. 8 caracteres): ");
            password = scanner.nextLine().trim();
        } while (password.length() < 8);
        
        // Seleccionar rol
        System.out.println("║ Seleccione el rol:");
        System.out.println("║ 1. ADMIN");
        System.out.println("║ 2. CASHIER");
        System.out.println("║ 3. INVENTORY");
        System.out.print("║ Opción: ");
        
        int opcionRol = obtenerOpcionValida(1, 3);
        String rol = switch (opcionRol) {
            case 1 -> "ADMIN";
            case 2 -> "CASHIER";
            case 3 -> "INVENTORY";
            default -> "CASHIER"; // Nunca llegará aquí por la validación anterior
        };
        
        // Crear y registrar usuario
        User nuevoUsuario = new User(username, password, rol);
        User usuarioRegistrado = userService.register(nuevoUsuario, true);
        
        // Mostrar resultados
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     Registrado con éxito!    ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.printf("║ %-15s ║ %-10s ║%n", "ID", usuarioRegistrado.getId());
        System.out.printf("║ %-15s ║ %-10s ║%n", "Usuario", usuarioRegistrado.getUsername());
        System.out.printf("║ %-15s ║ %-10s ║%n", "Rol", usuarioRegistrado.getRole());
        System.out.println("╚══════════════════════════════╝");
    }

    private void listUsuarios() {
        ConsoleUtils.clear();
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         LISTADO DE USUARIOS                            ║");
        System.out.println("╠════╦══════════════════╦════════════════════╦═════════════╦═════════════╣");
        System.out.println("║ ID ║ Username         ║ Fecha Creación     ║ Rol         ║ Activo      ║");
        System.out.println("╠════╬══════════════════╬════════════════════╬═════════════╬═════════════╣");

        try {
            List<User> users = userService.getAllUsers();

            if (users.isEmpty()) {
                System.out.println("║                       No hay usuarios registrados.                      ║");
                System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
                return;
            }

            users.forEach(user -> System.out.printf(
                    "║ %-2d ║ %-16s ║ %-18s ║ %-10s  ║ %-11s ║%n",
                    user.getId(),
                    user.getUsername(),
                    user.getCreated_at().toString().substring(0, 16),
                    user.getRole(),
                    user.isActive() ? "Sí" : "No"));
            System.out.println("╚════╩══════════════════╩════════════════════╩═════════════╩═════════════╝");
            System.out.println("Total de usuarios: " + users.size());
        } catch (Exception e) {
            System.out.println(" Error al obtener la lista de usuarios: " + e.getMessage());
        }
    }
}