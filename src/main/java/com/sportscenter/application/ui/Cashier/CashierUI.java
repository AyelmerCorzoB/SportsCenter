package com.sportscenter.application.ui.Cashier;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.menus.MenuCajero;
import com.sportscenter.application.usecase.invoice.InvoiceUseCase;
import com.sportscenter.application.ui.Admin.SaleUI;
import com.sportscenter.application.usecase.Sale.SaleUseCase;
import com.sportscenter.application.usecase.saledetail.SaleDetailUseCase;
import com.sportscenter.domain.entities.Invoice;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.SaleDetailRepository;
import com.sportscenter.domain.repository.SaleRepository;
import com.sportscenter.infrastructure.database.ConnectionFactory;
import com.sportscenter.infrastructure.persistence.SaleDetailRepositoryImpl;
import com.sportscenter.infrastructure.persistence.SaleRepositoryImpl;
import com.sportscenter.application.usecase.report.ReportUseCase;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

public class CashierUI {
    private final Scanner scanner;
    private final User currentUser;
    private final SaleUseCase saleUseCase;
    private final SaleDetailUseCase saleDetailUseCase;
    private final InvoiceUseCase invoiceUseCase;
    private final ReportUseCase reportUseCase;

    public CashierUI(Scanner scanner,
                    User currentUser,
                    SaleUseCase saleUseCase,
                    SaleDetailUseCase saleDetailUseCase,
                    InvoiceUseCase invoiceUseCase,
                    ReportUseCase reportUseCase) {
        this.scanner = scanner;
        this.currentUser = currentUser;
        this.saleUseCase = saleUseCase;
        this.saleDetailUseCase = saleDetailUseCase;
        this.invoiceUseCase = invoiceUseCase;
        this.reportUseCase = reportUseCase;
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            ConsoleUtils.clear();
            MenuCajero.mostrarMenu();
            opcion = obtenerOpcionValida();

            switch (opcion) {
                case 1 -> procesarNuevaVenta();
                case 2 -> gestionarFacturas();
                case 3 -> gestionarVentas();
                case 4 -> generarReportes();
                case 5 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
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

    private void procesarNuevaVenta() {
        System.out.println("\n--- PROCESAR NUEVA VENTA ---");
        ConsoleUtils.pressEnterToContinue(scanner);
    }
    
    private void gestionarFacturas() {
        int opcion;
        do {
            MenuCajero.mostrarSubmenuFacturas();
            opcion = obtenerOpcionValida();

            switch (opcion) {
                case 1 -> listarTodasFacturas();
                case 2 -> buscarFacturaPorId();
                case 3 -> generarNuevaFactura();
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }

    private void listarTodasFacturas() {
        System.out.println("\n--- LISTADO DE FACTURAS ---");
        List<Invoice> facturas = invoiceUseCase.getAllInvoices();
        
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
            ConsoleUtils.pressEnterToContinue(scanner);
            return;
        }
    
        System.out.printf("%-5s %-12s %-15s %-15s %-10s %-10s%n", 
            "ID", "VentaID", "N° Factura", "Fecha Emisión", "Total", "Impuestos");
        System.out.println("----------------------------------------------------------------------");
        for (Invoice invoice : facturas) {
            System.out.printf("%-5d %-12d %-15s %-15s %-10.2f %-10.2f%n",
                invoice.getId(),
                invoice.getSaleId(),
                invoice.getInvoiceNumber(),
                invoice.getIssueDate(),
                invoice.getTotalAmount(),
                invoice.getTaxes()
            );
        }
        ConsoleUtils.pressEnterToContinue(scanner);
    }
    private void buscarFacturaPorId() {
        System.out.print("\nIngrese el ID de la factura: ");
        try {
            int idFactura = Integer.parseInt(scanner.nextLine());
            Invoice factura = invoiceUseCase.getInvoiceById(idFactura);
            
            if (factura != null) {
                System.out.println("\n=== DETALLE DE FACTURA ===");
                System.out.println("ID: " + factura.getId());
                System.out.println("Número de Factura: " + factura.getInvoiceNumber());
                System.out.println("Fecha Emisión: " + factura.getIssueDate());
                System.out.println("ID Venta: " + factura.getSaleId());
                System.out.println("----------------------------------");
                System.out.println("Subtotal: $" + String.format("%.2f", factura.getTotalAmount() - factura.getTaxes()));
                System.out.println("Impuestos: $" + String.format("%.2f", factura.getTaxes()));
                System.out.println("Total: $" + String.format("%.2f", factura.getTotalAmount()));
            } else {
                System.out.println("No se encontró ninguna factura con el ID proporcionado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un número válido.");
        }
        ConsoleUtils.pressEnterToContinue(scanner);
    }

    private void generarNuevaFactura() {
    System.out.println("\n--- GENERAR NUEVA FACTURA ---");
    
    try {
        System.out.print("Ingrese el ID de la venta: ");
        int saleId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Número de factura: ");
        String invoiceNumber = scanner.nextLine();
        
        System.out.print("Fecha de emisión (AAAA-MM-DD): ");
        LocalDate issueDate = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Total: ");
        double totalAmount = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Impuestos: ");
        double taxes = Double.parseDouble(scanner.nextLine());
        
        invoiceUseCase.registerInvoice(saleId, invoiceNumber, issueDate, totalAmount, taxes);
        
        System.out.println("\nFactura registrada exitosamente!");
        
    } catch (NumberFormatException e) {
        System.out.println("Error: Debe ingresar valores numéricos válidos.");
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error inesperado al registrar factura: " + e.getMessage());
    }
    
    ConsoleUtils.pressEnterToContinue(scanner);
}

    private void gestionarVentas() {
        int opcion;
        do {
            MenuCajero.mostrarSubmenuVentas();
            opcion = obtenerOpcionValida();

            switch (opcion) {
                case 1 -> listarTodasVentas();
                case 2 -> buscarVentaPorId();
                case 3 -> verDetallesVenta();
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }

    private void listarTodasVentas() {
        System.out.println("\n--- LISTADO DE VENTAS ---");
        // Implementación pendiente
    }

    private void buscarVentaPorId() {
        System.out.print("\nIngrese el ID de la venta: ");
        String idVenta = scanner.nextLine();
        // Implementación pendiente
    }

    private void verDetallesVenta() {
        System.out.print("\nIngrese el ID de la venta para ver detalles: ");
        String idVenta = scanner.nextLine();
        // Implementación pendiente
    }

    private void generarReportes() {
        int opcion;
        do {
            MenuCajero.mostrarSubmenuReportes();
            opcion = obtenerOpcionValida();

            switch (opcion) {
                case 1 -> generarReporteVentasPeriodo();
                case 2 -> generarReporteProductosMasVendidos();
                case 3 -> generarReporteIngresos();
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }

    private void generarReporteVentasPeriodo() {
        System.out.println("\n--- REPORTE DE VENTAS POR PERÍODO ---");
        // Implementación pendiente
    }

    private void generarReporteProductosMasVendidos() {
        System.out.println("\n--- REPORTE DE PRODUCTOS MÁS VENDIDOS ---");
        // Implementación pendiente
    }

    private void generarReporteIngresos() {
        System.out.println("\n--- REPORTE DE INGRESOS ---");
        // Implementación pendiente
    }
}