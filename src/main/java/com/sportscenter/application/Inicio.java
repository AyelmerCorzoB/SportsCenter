package com.sportscenter.application;

import com.sportscenter.application.usecase.Sale.SaleUseCase;
import com.sportscenter.application.usecase.invoice.InvoiceUseCase;
import com.sportscenter.application.usecase.invoice.ListInvoice;
import com.sportscenter.application.usecase.product.ListarProducts;
import com.sportscenter.application.usecase.product.ProductUseCase;
import com.sportscenter.application.usecase.report.ReportUseCase;
import com.sportscenter.application.usecase.saledetail.SaleDetailUseCase;
import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.application.ui.Admin.AdminUI;
import com.sportscenter.application.ui.Cashier.CashierUI;
import com.sportscenter.application.ui.Consumer.ActualizarPassword;
import com.sportscenter.application.ui.Consumer.ConsumerUI;
import com.sportscenter.application.ui.Consumer.ListarSalesPorUsuario;
import com.sportscenter.application.ui.Inventory.InventoryUi;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.CustomerRepository;
import com.sportscenter.domain.repository.InvoiceRepository;
import com.sportscenter.domain.repository.ProductRepository;
import com.sportscenter.domain.repository.ReportRepository;
import com.sportscenter.domain.repository.SaleDetailRepository;
import com.sportscenter.domain.repository.SaleRepository;
import com.sportscenter.domain.repository.UserRepository;
import com.sportscenter.domain.service.UserService;
import com.sportscenter.infrastructure.database.ConnectionDb;
import com.sportscenter.infrastructure.database.ConnectionFactory;
import com.sportscenter.infrastructure.persistence.CustomerRepositoryImpl;
import com.sportscenter.infrastructure.persistence.InvoiceRepositoryImpl;
import com.sportscenter.infrastructure.persistence.ProductRepositoryImpl;
import com.sportscenter.infrastructure.persistence.ReportRepositoryImpl;
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

            int option = obtenerOpcionValida(1, 3);

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

    private int obtenerOpcionValida(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("Error: No se permiten entradas vacías. Intente nuevamente: ");
                    continue;
                }
                int opcion = Integer.parseInt(input);
                if (opcion < min || opcion > max) {
                    System.out.printf("Error: Opción debe estar entre %d y %d. Intente nuevamente: ", min, max);
                    continue;
                }

                return opcion;
            } catch (NumberFormatException e) {
                System.out.print("Error: Debe ingresar un número válido. Intente nuevamente: ");
            }
        }
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
        ReportRepository reportRepository = new ReportRepositoryImpl(connection);

        SaleUseCase saleUseCase = new SaleUseCase(saleRepository);
        SaleDetailUseCase saleDetailUseCase = new SaleDetailUseCase(saleDetailRepository);
        ProductUseCase productUseCase = new ProductUseCase(productRepository);
        InvoiceUseCase invoiceUseCase = new InvoiceUseCase(invoiceRepository);
        ListarProducts listarProducts = new ListarProducts(productRepository);
        ReportUseCase reportUseCase = new ReportUseCase(reportRepository);
        ListarSalesPorUsuario listarSalesPorUsuario = new ListarSalesPorUsuario(
                saleRepository,
                saleDetailRepository);

        ListInvoice ListInvoice = new ListInvoice();
        ActualizarPassword actualizarPassword = new ActualizarPassword(userRepository);

        switch (currentUser.getRole()) {
            case "ADMIN" -> new AdminUI(scanner, userService, currentUser).mostrarMenu();
            case "CASHIER" ->
                new CashierUI(scanner, currentUser, saleUseCase, saleDetailUseCase, invoiceUseCase, reportUseCase)
                        .mostrarMenuPrincipal();
            case "INVENTORY" ->
                new InventoryUi(scanner, productUseCase, listarProducts, currentUser, productRepository, userService)
                        .mostrarMenu();
            case "CONSUMER" -> new ConsumerUI(
                    scanner,
                    productUseCase,
                    currentUser,
                    listarProducts,
                    listarSalesPorUsuario,
                    ListInvoice,
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

    public static void main(String[] args) {
        new Inicio().start();
    }
}
