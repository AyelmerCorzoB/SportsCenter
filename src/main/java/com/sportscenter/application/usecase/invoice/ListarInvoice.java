package com.sportscenter.application.usecase.invoice;

import com.sportscenter.domain.entities.Invoice;

public class ListarInvoice {
    public void listar(InvoiceUseCase invoiceUseCase) {
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

    public void mostrarFacturasPorUsuario(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarFacturasPorUsuario'");
    }
}
