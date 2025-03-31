package com.sportscenter.application.ui.Admin;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
            System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║          PANEL DE ADMINISTRACIÓN           ║");
            System.out.println("╣                                            ╣");
            System.out.printf("║ Usuario actual: %-26s ║%n",
                    currentUser.getUsername() + " (" + currentUser.getRole() + ")");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║ 1. Registrar nuevo usuario administrativo  ║");
            System.out.println("║ 2. Listar todos los usuarios               ║");
            System.out.println("║ 3. Panel de control                        ║");
            System.out.println("║ 4. Cerrar sesión                           ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Elija una opción: ");

            int option = obtenerOpcionValida();

            switch (option) {
                case 1 -> RegisterAdminUser();
                case 2 -> ListUsuarios();
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
        return Optional.of(scanner)
                .filter(s -> s.hasNextInt())
                .map(s -> {
                    int opcion = s.nextInt();
                    s.nextLine();
                    return opcion;
                })
                .orElseGet(() -> {
                    System.out.println("Por favor, ingrese un número válido.");
                    scanner.next();
                    return obtenerOpcionValida();
                });
    }

    private void mostrarPanelControl() {
        ControlAdminUi adminUi = new ControlAdminUi(scanner, userService, currentUser);
        adminUi.start();
    }

    private void RegisterAdminUser() {
        ConsoleUtils.clear();
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║    Registro de usuario administrativo   ║");
        System.out.println("╠═════════════════════════════════════════╣");
        Optional.ofNullable(solicitarDatosAdmin())
                .map(adminUser -> userService.register(adminUser, true))
                .ifPresentOrElse(
                        registeredUser -> {

                            System.out.println("╔══════════════════════════════╗");
                            System.out.println("║     Registrado con éxito!    ║");
                            System.out.println("╠══════════════════════════════╣");
                            System.out.printf("║ %-15s ║ %-10s ║%n", "ID", registeredUser.getId());
                            System.out.printf("║ %-15s ║ %-10s ║%n", "Usuario", registeredUser.getUsername());
                            System.out.printf("║ %-15s ║ %-10s ║%n", "Rol", registeredUser.getRole());
                            System.out.println("╚══════════════════════════════╝");

                        },
                        () -> System.out.println("\nX Error durante el registro"));
    }

    private String solicitarInput(String mensaje, Predicate<String> validador) {
        String input;
        do {
            System.out.print(mensaje);
            input = scanner.nextLine().trim();
        } while (!validador.test(input));
        return input;
    }

    private User solicitarDatosAdmin() {
        String username = solicitarInput(
                "║ Username (mín. 3 caracteres): ",
                s -> s.length() >= 3);

        String password = solicitarInput(
                "║ Password (mín. 8 caracteres): ",
                s -> s.length() >= 8);

        String role = seleccionarRol();

        return new User(username, password, role);
    }

    private String seleccionarRol() {
        System.out.println("║ Seleccione el rol:");
        System.out.println("║ 1. ADMIN");
        System.out.println("║ 2. CASHIER");
        System.out.println("║ 3. INVENTORY");
        System.out.print("║ Opción: ");

        Map<Integer, Supplier<String>> roleSuppliers = Map.of(
                1, () -> "ADMIN",
                2, () -> "CASHIER",
                3, () -> "INVENTORY");

        return Optional.ofNullable(roleSuppliers.get(obtenerOpcionValida()))
                .map(Supplier::get)
                .orElseGet(() -> {
                    System.out.println("Opción inválida. Se asignará rol CASHIER por defecto.");
                    return "CASHIER";
                });
    }

    private void ListUsuarios() {
        ConsoleUtils.clear();
        System.out.println("");
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