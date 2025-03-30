package com.sportscenter.application.ui.Cashier;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.menus.MenuCajero;
import com.sportscenter.application.usecase.invoice.InvoiceUseCase;
import com.sportscenter.application.usecase.invoice.RegisterInvoice;
import com.sportscenter.application.ui.Admin.SaleUI;
import com.sportscenter.application.usecase.Sale.ListSale;
import com.sportscenter.application.usecase.Sale.SaleUseCase;
import com.sportscenter.application.usecase.Sale.SearchSale;
import com.sportscenter.application.usecase.saledetail.SaleDetailUseCase;
import com.sportscenter.application.usecase.saledetail.SearchSaleDetail;
import com.sportscenter.domain.entities.Invoice;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.ReportRepository;
import com.sportscenter.domain.repository.SaleDetailRepository;
import com.sportscenter.domain.repository.SaleRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;
import com.sportscenter.infrastructure.database.ConnectionFactory;
import com.sportscenter.infrastructure.persistence.ReportRepositoryImpl;
import com.sportscenter.infrastructure.persistence.SaleDetailRepositoryImpl;
import com.sportscenter.infrastructure.persistence.SaleRepositoryImpl;
import com.sportscenter.application.usecase.report.ReportService;
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
                case 1 -> ListTodasFacturas();
                case 2 -> SearchFacturaPorId();
                case 3 -> new RegisterInvoice().Register(scanner, invoiceUseCase);
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }

    private void ListTodasFacturas() {
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
    private void SearchFacturaPorId() {
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

    

    private void gestionarVentas() {
        int opcion;
        do {
            MenuCajero.mostrarSubmenuVentas();
            opcion = obtenerOpcionValida();

            switch (opcion) {
                case 1 -> new ListSale().List(saleUseCase);
                case 2 -> new SearchSale().Search(scanner, saleUseCase);
                case 3 -> new SearchSaleDetail().SearchPorVenta(scanner, saleDetailUseCase);
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }

    private void generarReportes() {
        ConnectionDb connection = ConnectionFactory.crearConexion();
        ReportRepository repository = new ReportRepositoryImpl(connection);
        ReportService reportService = new ReportService(repository);
        
        int opcion;
        do {
            MenuCajero.mostrarSubmenuReportes();
            opcion = obtenerOpcionValida();
            String filePath;
            switch (opcion) {
                case 1 -> {
                    filePath = "Reporte_Ventas.pdf";
                    reportService.generarReporteVentas(filePath);
                }
                case 2 -> {
                    filePath = "Reporte_Productos_Mas_Vendidos.pdf";
                    reportService.generarReporteProductosMasVendidos(filePath);
                }
                case 3 -> {
                    filePath = "Reporte_Ingresos.pdf";
                    reportService.generarReporteIngresos(filePath);
                }
                case 4 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }
    

    
}