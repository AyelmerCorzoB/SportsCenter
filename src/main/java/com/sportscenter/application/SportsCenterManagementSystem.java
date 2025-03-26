package com.sportscenter.application;

import com.sportscenter.application.ui.*;
import com.sportscenter.application.usecase.*;
import com.sportscenter.application.usecase.category.CategoryUseCase;
import com.sportscenter.application.usecase.color.ColorUseCase;
import com.sportscenter.application.usecase.customer.CustomerUseCase;
import com.sportscenter.application.usecase.customer_type.CustomerTypeUseCase;
import com.sportscenter.application.usecase.customerorder.CustomerOrderUseCase;
import com.sportscenter.application.usecase.invoice.InvoiceUseCase;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.*;
import com.sportscenter.domain.service.UserService;
import com.sportscenter.infrastructure.database.ConnectionFactory;
import com.sportscenter.infrastructure.persistence.*;

import java.util.Scanner;

public class SportsCenterManagementSystem {
    private final Scanner scanner;
    private final UserService userService;
    private User currentUser;

    public SportsCenterManagementSystem() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService(new UserRepositoryImpl(ConnectionFactory.crearConexion()));
    }

    public void start() {
        showWelcomeMessage();
        showMainMenu();
        scanner.close();
    }

    private void showWelcomeMessage() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║    SISTEMA DE GESTIÓN DEPORTIVA      ║");
        System.out.println("║            BIENVENIDO               ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    
    private void showMainMenu() {
        int selectedOption;
        
        do {
            printMainMenuOptions();
            selectedOption = getValidOption(1, 7);
            
            if (selectedOption != 7) {
                handleMenuOption(selectedOption);
            }
        } while (selectedOption != 7);
        
        System.out.println("\nGracias por usar el sistema. ¡Hasta pronto!");
    }

    private void printMainMenuOptions() {
        System.out.println("\nMENÚ PRINCIPAL:");
        System.out.println("1. Gestión de Categorías");
        System.out.println("2. Gestión de Colores");
        System.out.println("3. Gestión de Pedidos");
        System.out.println("4. Gestión de Tipos de Cliente");
        System.out.println("5. Gestión de Clientes");
        System.out.println("6. Gestión de Facturas");
        System.out.println("7. Salir del sistema");
        System.out.print("\nSeleccione una opción: ");
    }

    private void handleMenuOption(int option) {
        try {
            switch (option) {
                case 1 -> manageCategories();
                case 2 -> manageColors();
                case 3 -> manageCustomerOrders();
                case 4 -> manageCustomerTypes();
                case 5 -> manageCustomers();
                case 6 -> manageInvoices();
                case 7 -> System.out.println("Saliendo...");
                default -> System.out.println("❌ Opción no válida");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void manageCategories() {
        CategoryRepository repository = new CategoryRepositoryImpl(ConnectionFactory.crearConexion());
        CategoryUseCase useCase = new CategoryUseCase(repository);
        CategoryUI.manejarMenuCategory(scanner, useCase);
    }

    private void manageColors() {
        ColorRepository repository = new ColorRepositoryImpl(ConnectionFactory.crearConexion());
        ColorUseCase useCase = new ColorUseCase(repository);
        ColorUI.manejarMenuColor(scanner, useCase);
    }

    private void manageCustomerOrders() {
        CustomerOrderRepository repository = new CustomerOrderRepositoryImpl(ConnectionFactory.crearConexion());
        CustomerOrderUseCase useCase = new CustomerOrderUseCase(repository);
        CustomerOrderUI.manejarMenuCustomerOrder(scanner, useCase);
    }

    private void manageCustomerTypes() {
        CustomerTypeRepository repository = new CustomerTypeRepositoryImpl(ConnectionFactory.crearConexion());
        CustomerTypeUseCase useCase = new CustomerTypeUseCase(repository);
        CustomerTypeUI.manejarMenuCustomerType(scanner, useCase);
    }

    private void manageCustomers() {
        CustomerRepository repository = new CustomerRepositoryImpl(ConnectionFactory.crearConexion());
        CustomerUseCase useCase = new CustomerUseCase(repository);
        CustomerUI.manejarMenuCustomer(scanner, useCase);
    }

    private void manageInvoices() {
        InvoiceRepository repository = new InvoiceRepositoryImpl(ConnectionFactory.crearConexion());
        InvoiceUseCase useCase = new InvoiceUseCase(repository);
        InvoiceUI.manejarMenuInvoice(scanner, useCase);
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

    public static void main(String[] args) {
        new SportsCenterManagementSystem().start();
    }
}