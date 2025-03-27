package com.sportscenter.application;

import com.sportscenter.application.usecase.invoice.ListarInvoice;
import com.sportscenter.application.usecase.product.ListarProducts;
import com.sportscenter.application.usecase.product.ProductUseCase;
import com.sportscenter.application.ui.AdminUI;
import com.sportscenter.application.ui.Consumer.ActualizarPassword;
import com.sportscenter.application.ui.Consumer.ConsumerUI;
import com.sportscenter.application.ui.Consumer.ListarOrdersPorUsuario;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.OrderRepository;
import com.sportscenter.domain.repository.ProductRepository;
import com.sportscenter.domain.repository.UserRepository;
import com.sportscenter.domain.service.UserService;
import com.sportscenter.infrastructure.database.ConnectionDb;
import com.sportscenter.infrastructure.database.ConnectionFactory;
import com.sportscenter.infrastructure.persistence.OrderRepositoryImpl;
import com.sportscenter.infrastructure.persistence.ProductRepositoryImpl;
import com.sportscenter.infrastructure.persistence.UserRepositoryImpl;

import java.util.Scanner;

public class LoginPrimeraPropuesta {
    private final Scanner scanner;
    private final UserService userService;
    private User currentUser = null;

    public LoginPrimeraPropuesta() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService(new UserRepositoryImpl(ConnectionFactory.crearConexion()));
    }

    public void start() {
        mostrarMenuPrincipal();
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\nBienvenido al Sports Center");
            System.out.println("1. Login");
            System.out.println("2. Registrarse");
            System.out.println("3. Panel de administración");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");

            int option = obtenerOpcionValida();

            switch (option) {
                case 1 -> handleLogin();
                case 2 -> handleRegister();
                case 3 -> handleAdminPanel();
                case 4 -> {
                    System.out.println("Saliendo del sistema...");
                    return;
                }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    //Usamos este método para obtener una opción válida del menú ya qye la validacion de la carpeta adapter validations
    //me causaba problemas al momento de validar el ingreso de los enteros
    private int obtenerOpcionValida() {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    private void handleLogin() {
        System.out.print("\nUsername: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser = userService.authenticate(username, password);

        if (currentUser != null) {
            System.out.println("\nLogin exitoso!");
            System.out.println("Bienvenido, " + currentUser.getUsername());
            System.out.println("Tu rol: " + currentUser.getRole());

            redirigirSegunRol();
        } else {
            System.out.println("\nUsuario o contraseña inválidos");
        }
    }

    private void redirigirSegunRol() {
        ConnectionDb connection = ConnectionFactory.crearConexion();
        ProductRepository productRepository = new ProductRepositoryImpl(connection);
        UserRepository userRepository = new UserRepositoryImpl(connection);
        OrderRepository orderRepository = new OrderRepositoryImpl(connection);

        ProductUseCase productUseCase = new ProductUseCase(productRepository);
        ListarProducts listarProducts = new ListarProducts();
        ListarOrdersPorUsuario listarOrdersPorUsuario = new ListarOrdersPorUsuario(orderRepository); // Ahora con repositorio
        ListarInvoice listarInvoice = new ListarInvoice(/* Repositorio necesario */); // Completa con el repositorio que necesites
        ActualizarPassword actualizarPassword = new ActualizarPassword(userRepository); // Ahora con repositorio

        switch (currentUser.getRole()) {
            case "ADMIN" -> new AdminUI(scanner, userService, currentUser).mostrarMenu();
            case "CASHIER" -> System.out.println("Panel de Cajero no implementado aún");
            case "INVENTORY" -> System.out.println("Panel de Inventario no implementado aún");
            case "CONSUMER" -> new ConsumerUI(scanner, productUseCase, currentUser,
                                              listarProducts, listarOrdersPorUsuario,
                                              listarInvoice, actualizarPassword).mostrarMenuPrincipal();
        }
    }

    private void handleRegister() {
        System.out.println("\n--- Registro de nuevo usuario ---");
        User newUser = solicitarDatosRegistro();
        boolean registered = userService.register(newUser, false);

        System.out.println(registered ? "\nRegistro exitoso! Ahora puedes iniciar sesión como CONSUMER."
                : "\nRegistro fallido. El nombre de usuario o email ya están en uso.");
    }

    private User solicitarDatosRegistro() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        return new User(username, email, password, "CONSUMER");
    }

    private void handleAdminPanel() {
        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            System.out.println("\nAcceso denegado. Se requiere rol ADMIN.");
            return;
        }
        new AdminUI(scanner, userService, currentUser).mostrarMenu();
    }

    public static void main(String[] args) {
        new LoginPrimeraPropuesta().start();
    }
}
