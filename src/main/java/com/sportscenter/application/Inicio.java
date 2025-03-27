package com.sportscenter.application;

import com.sportscenter.application.usecase.invoice.InvoiceUseCase;
import com.sportscenter.application.usecase.invoice.ListarInvoice;
import com.sportscenter.application.usecase.product.ListarProducts;
import com.sportscenter.application.usecase.product.ProductUseCase;
import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.application.ui.AdminUI;
import com.sportscenter.application.ui.Consumer.ActualizarPassword;
import com.sportscenter.application.ui.Consumer.ConsumerUI;
import com.sportscenter.application.ui.Consumer.ListarSalesPorUsuario;
import com.sportscenter.domain.entities.SaleDetail;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.CustomerRepository;
import com.sportscenter.domain.repository.InvoiceRepository;
import com.sportscenter.domain.repository.OrderRepository;
import com.sportscenter.domain.repository.ProductRepository;
import com.sportscenter.domain.repository.SaleDetailRepository;
import com.sportscenter.domain.repository.SaleRepository;
import com.sportscenter.domain.repository.UserRepository;
import com.sportscenter.domain.service.UserService;
import com.sportscenter.infrastructure.database.ConnectionDb;
import com.sportscenter.infrastructure.database.ConnectionFactory;
import com.sportscenter.infrastructure.persistence.CustomerRepositoryImpl;
import com.sportscenter.infrastructure.persistence.InvoiceRepositoryImpl;
import com.sportscenter.infrastructure.persistence.OrderRepositoryImpl;
import com.sportscenter.infrastructure.persistence.ProductRepositoryImpl;
import com.sportscenter.infrastructure.persistence.SaleDetailRepositoryImpl;
import com.sportscenter.infrastructure.persistence.SaleRepositoryImpl;
import com.sportscenter.infrastructure.persistence.UserRepositoryImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class Inicio {
    private final Scanner scanner;
    private final UserService userService;
    private User currentUser = null;
    private final CustomerRepository customerRepository;

    public Inicio() {
        this.scanner = new Scanner(System.in);
        ConnectionDb connection = ConnectionFactory.crearConexion();
        this.userService = new UserService(new UserRepositoryImpl(connection));
        this.customerRepository = new CustomerRepositoryImpl(connection);
    }

    public void start() {
        mostrarMenuPrincipal();
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        while (true) {
            ConsoleUtils.clear();
            System.out.println("\nBienvenido al Sports Center");
            System.out.println("1. Login");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");

            int option = obtenerOpcionValida();

            switch (option) {
                case 1 -> Login();
                case 2 -> Register();
                case 3 -> {
                    System.out.println("Saliendo del sistema...");
                    return;
                }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
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

    private void Login() {
        System.out.print("\nUsername: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser = userService.authenticate(username, password);

        if (currentUser != null) {
            ConsoleUtils.clear();
            System.out.println("\nLogin exitoso!");
            System.out.println("Bienvenido, " + currentUser.getUsername());
            System.out.println("Tu rol: " + currentUser.getRole());
            ConsoleUtils.pressEnterToContinue(scanner);
            redirigirSegunRol();
        } else {
            System.out.println("\nUsuario o contraseña inválidos");
            ConsoleUtils.pressEnterToContinue(scanner);
        }
    }

    private void redirigirSegunRol() {
        ConnectionDb connection = ConnectionFactory.crearConexion();
        ProductRepository productRepository = new ProductRepositoryImpl(connection);
        UserRepository userRepository = new UserRepositoryImpl(connection);
        SaleRepository saleRepository = new SaleRepositoryImpl(connection);
        SaleDetailRepository saleDetailRepository = new SaleDetailRepositoryImpl(connection);
        InvoiceRepository invoiceRepository = new InvoiceRepositoryImpl(connection);

        ProductUseCase productUseCase = new ProductUseCase(productRepository);
        InvoiceUseCase invoiceUseCase = new InvoiceUseCase(invoiceRepository);
        ListarProducts listarProducts = new ListarProducts();
        ListarSalesPorUsuario listarSalesPorUsuario = new ListarSalesPorUsuario(
                saleRepository,
                saleDetailRepository);

        ListarInvoice listarInvoice = new ListarInvoice();
        ActualizarPassword actualizarPassword = new ActualizarPassword(userRepository);

        switch (currentUser.getRole()) {
            case "ADMIN" ->
                new AdminUI(scanner, userService, currentUser).mostrarMenu();

            case "CASHIER" ->
                System.out.println("Panel de Cajero no implementado aún");

            case "INVENTORY" ->
                System.out.println("Panel de Inventario no implementado aún");

            case "CONSUMER" ->
                new ConsumerUI(
                        scanner,
                        productUseCase,
                        currentUser,
                        listarProducts,
                        listarSalesPorUsuario,
                        listarInvoice,
                        actualizarPassword,
                        invoiceUseCase).mostrarMenuPrincipal();
        }
    }

    private void Register() {
        System.out.println("\n--- Registro de nuevo usuario ---");
        User newUser = solicitarDatosRegistro();
        User registeredUser = userService.register(newUser, false);

        if (registeredUser != null) {
            System.out.println("\nRegistro de usuario exitoso! ID: " + registeredUser.getId());

            System.out.println("Ahora complete sus datos como cliente:");
            solicitarDatosCustomer(registeredUser);

            System.out.println("\nRegistro completo! Ahora puedes iniciar sesión como CONSUMER.");
        } else {
            System.out.println("\nRegistro fallido. El nombre de usuario ya está en uso.");
        }
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private User solicitarDatosRegistro() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        return new User(username, password, "CONSUMER");
    }

    private void solicitarDatosCustomer(User user) {
        System.out.print("\nNombre completo: ");
        String name = scanner.nextLine();

        System.out.print("Documento de identidad: ");
        String identityDocument = scanner.nextLine();

        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();

        System.out.print("Dirección: ");
        String address = scanner.nextLine();

        customerRepository.guardarCliente(
                1,
                name,
                identityDocument,
                phone,
                address,
                LocalDate.now(),
                user.getId());
    }

    private void AdminPanel() {
        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            System.out.println("\nAcceso denegado. Se requiere rol ADMIN.");
            return;
        }
        new AdminUI(scanner, userService, currentUser).mostrarMenu();
    }

    public static void main(String[] args) {
        new Inicio().start();
    }
}
