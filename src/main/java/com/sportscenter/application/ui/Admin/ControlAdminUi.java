package com.sportscenter.application.ui.Admin;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.application.usecase.Sale.SaleUseCase;
import com.sportscenter.application.usecase.category.CategoryUseCase;
import com.sportscenter.application.usecase.color.ColorUseCase;
import com.sportscenter.application.usecase.customer.CustomerUseCase;
import com.sportscenter.application.usecase.customer_type.CustomerTypeUseCase;
import com.sportscenter.application.usecase.customerorder.CustomerOrderUseCase;
import com.sportscenter.application.usecase.employee.EmployeeUseCase;
import com.sportscenter.application.usecase.invoice.InvoiceUseCase;
import com.sportscenter.application.usecase.saledetail.SaleDetailUseCase;
import com.sportscenter.application.usecase.supplier.SupplierUseCase;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.*;
import com.sportscenter.domain.service.UserService;
import com.sportscenter.infrastructure.database.ConnectionFactory;
import com.sportscenter.infrastructure.persistence.*;

import java.util.Scanner;

public class ControlAdminUi {
    private final Scanner scanner;
    private final UserService userService;
    private User currentUser;
    private int currentPage = 1;
    private static final int MAX_OPTIONS_PER_PAGE = 6;
    private static final int EXIT_OPTION = 7;

    public ControlAdminUi() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService(new UserRepositoryImpl(ConnectionFactory.crearConexion()));
    }

    public void start() {
        showWelcomeMessage();
        showMainMenu();
    }

    private void showWelcomeMessage() {
        System.out.println("======= Bienvenido al sistema de gestión del Sports Center =======");
    }

    private void showMainMenu() {
        int selectedOption;
        boolean exitProgram = false;

        do {
            ConsoleUtils.clear();
            printMainMenuOptions();

            selectedOption = getValidOption(1, EXIT_OPTION);

            if (selectedOption == MAX_OPTIONS_PER_PAGE) {
                currentPage = (currentPage == 1) ? 2 : 1;
            } else if (selectedOption == EXIT_OPTION) {
                exitProgram = true;
            } else {
                handleMenuOption(selectedOption);
            }
        } while (!exitProgram);
    }

    private void printMainMenuOptions() {
        System.out.println("\n=== PANEL DE CONTROL ADMINISTRATIVO ===");
        System.out.println("Página " + currentPage + " de 2");
        System.out.println("--------------------------------------");
        
        if (currentPage == 1) {
            printPage1Options();
        } else {
            printPage2Options();
        }
        System.out.print("\nSeleccione una opción: ");
    }

    private void printPage1Options() {
        System.out.println("1. Gestión de Categorías");
        System.out.println("2. Gestión de Colores");
        System.out.println("3. Gestión de Pedidos");
        System.out.println("4. Gestión de Tipos de Cliente");
        System.out.println("5. Gestión de Clientes");
        System.out.println("\n6. Siguiente página");
        System.out.println("7. Volver al menú principal");
    }

    private void printPage2Options() {
        System.out.println("1. Gestión de Facturas");
        System.out.println("2. Gestión de Productos");
        System.out.println("3. Gestión de Proveedores");
        System.out.println("4. Gestión de Usuarios");
        System.out.println("5. Gestión de Ventas");
        System.out.println("\n6. Página anterior");
        System.out.println("7. Volver al menú principal");
    }

    private void handleMenuOption(int option) {
        try {
            if (currentPage == 1) {
                handlePage1Options(option);
            } else {
                handlePage2Options(option);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
            ConsoleUtils.pressEnterToContinue(scanner);
        }
    }

    private void handlePage1Options(int option) {
        switch (option) {
            case 1 -> manageCategories();
            case 2 -> manageColors();
            case 3 -> manageCustomerOrders();
            case 4 -> manageCustomerTypes();
            case 5 -> manageCustomers();
            default -> System.out.println("Opción no válida");
        }
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void handlePage2Options(int option) {
        switch (option) {
            case 1 -> manageInvoices();
            //case 2 -> manageProducts();
            case 3 -> manageSuppliers();
            case 4 -> manageEmployees();
            case 5 -> manageSales();
            default -> System.out.println("Opción no válida");
        }
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void manageCategories() {
        CategoryRepository repository = new CategoryRepositoryImpl(ConnectionFactory.crearConexion());
        CategoryUseCase useCase = new CategoryUseCase(repository);
        CategoryUI.mostrarMenu(scanner,useCase);
    }

    private void manageColors() {
        ColorRepository repository = new ColorRepositoryImpl(ConnectionFactory.crearConexion());
        ColorUseCase useCase = new ColorUseCase(repository);
        ColorUI.mostrarMenu(scanner, useCase);;
    }

    private void manageCustomerOrders() {
        CustomerOrderRepository repository = new CustomerOrderRepositoryImpl(ConnectionFactory.crearConexion());
        CustomerOrderUseCase useCase = new CustomerOrderUseCase(repository);
        CustomerOrderUI.mostrarMenu(scanner, useCase);
    }

    private void manageCustomerTypes() {
        CustomerTypeRepository repository = new CustomerTypeRepositoryImpl(ConnectionFactory.crearConexion());
        CustomerTypeUseCase useCase = new CustomerTypeUseCase(repository);
        CustomerTypeUI.mostrarMenu(scanner, useCase);
    }

    private void manageCustomers() {
        CustomerRepository repository = new CustomerRepositoryImpl(ConnectionFactory.crearConexion());
        CustomerUseCase useCase = new CustomerUseCase(repository);
        CustomerUI.mostrarMenu(scanner, useCase);
    }

    private void manageInvoices() {
        InvoiceRepository repository = new InvoiceRepositoryImpl(ConnectionFactory.crearConexion());
        InvoiceUseCase useCase = new InvoiceUseCase(repository);
        InvoiceUI.mostrarMenu(scanner, useCase);
    }

    // private void manageProducts() {
    //     ProductRepository repository = new ProductRepositoryImpl(ConnectionFactory.crearConexion());
    //     ProductUseCase useCase = new ProductUseCase(repository);
    //     ProductUI.mostrarMenu();
    // }

    private void manageSuppliers() {
        SupplierRepository repository = new SupplierRepositoryImpl(ConnectionFactory.crearConexion());
        SupplierUseCase useCase = new SupplierUseCase(repository);
        SupplierUI.mostrarMenu(scanner, useCase);
    }

    private void manageSales() {
        SaleRepository repository = new SaleRepositoryImpl(ConnectionFactory.crearConexion());
        SaleDetailRepository saleDetailRepository = new SaleDetailRepositoryImpl(ConnectionFactory.crearConexion());
        SaleUseCase useCase = new SaleUseCase(repository);
        SaleDetailUseCase saleDetailUseCase = new SaleDetailUseCase(saleDetailRepository);
        SaleUI saleUI = new SaleUI();
        SaleUI.mostrarMenu(scanner, useCase,saleDetailUseCase);
    }

    private void manageEmployees() {
        EmployeeRepository repository = new EmployeeRepositoryImpl(ConnectionFactory.crearConexion());
        EmployeeUseCase useCase = new EmployeeUseCase(repository);
        EmployeeUI.mostrarMenu(scanner, useCase);
    }

    private int getValidOption(int min, int max) {
        while (true) {
            try {
                int option = Integer.parseInt(scanner.nextLine());
                if (option >= min && option <= max) {
                    return option;
                }
                System.out.printf("Opción debe estar entre %d y %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número: ");
            }
        }
    }
}