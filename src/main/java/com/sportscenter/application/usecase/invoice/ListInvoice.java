package com.sportscenter.application.usecase.invoice;

import java.util.List;

import com.sportscenter.domain.entities.Invoice;

public class ListInvoice {
    public void List(InvoiceUseCase invoiceUseCase) {
        System.out.println("\n=== LISTADO DE FACTURAS ===");
        System.out.printf("%-5s %-12s %-15s %-15s %-10s %-10s%n", 
            "ID", "VentaID", "N° Factura", "Fecha Emisión", "Total", "Impuestos");
        System.out.println("----------------------------------------------------------------------");

        for (Invoice invoice : invoiceUseCase.getAllInvoices()) {
            System.out.printf("%-5d %-12d %-15s %-15s %-10.2f %-10.2f%n",
                invoice.getId(),
                invoice.getSaleId(),
                invoice.getInvoiceNumber(),
                invoice.getIssueDate(),
                invoice.getTotalAmount(),
                invoice.getTaxes()
            );
        }
    }

    public void mostrarFacturasPorUsuario(int userId, InvoiceUseCase invoiceUseCase) {
        List<Invoice> facturas = invoiceUseCase.getInvoicesByUserId(userId);
        
        if (facturas.isEmpty()) {
            System.out.println("\nNo se encontraron facturas para este usuario.");
            return;
        }

        System.out.println("\n=== FACTURAS DEL USUARIO ===");
        System.out.printf("%-10s %-15s %-15s %-12s %-10s %-15s %-15s%n",
            "FACTURA", "FECHA EMISIÓN", "FECHA VENTA", "TOTAL", "ITEMS", "CLIENTE", "MÉTODO PAGO");
        System.out.println("------------------------------------------------------------------------------------------");

        for (Invoice factura : facturas) {
            System.out.printf("%-10s %-15s %-15s %-12.2f %-10d %-15s %-15s%n",
                factura.getInvoiceNumber(),
                factura.getIssueDate(),
                factura.getSaleDate(),
                factura.getTotalAmount(),
                factura.getItemsCount(),
                factura.getCustomerName(),
                factura.getPaymentMethod()
            );
        }
        
        // Mostrar resumen
        double totalFacturado = facturas.stream().mapToDouble(Invoice::getTotalAmount).sum();
        System.out.println("\nRESUMEN:");
        System.out.println("Total facturado: $" + String.format("%.2f", totalFacturado));
        System.out.println("Cantidad de facturas: " + facturas.size());
    }


    
}