package com.sportscenter.application.usecase.invoice;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Invoice;

public class SearchInvoice {
    public void Search(Scanner sc, InvoiceUseCase invoiceUseCase) {
        System.out.println("\n=== BUSCAR FACTURA ===");

        System.out.print("ID de la factura: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        Invoice invoice = invoiceUseCase.getInvoiceById(id);
        if (invoice != null) {
            System.out.println("╔═════════════════════════════════════════════╗");
            System.out.println("║           INFORMACIÓN DE FACTURA            ║");
            System.out.println("╠═════════════════════════════════════════════╣");
            System.out.println(String.format("║   %-16s: %-21s   ║", "ID", invoice.getId()));
            System.out.println(String.format("║   %-16s: %-21s   ║", "ID Venta", invoice.getSaleId()));
            System.out.println(String.format("║   %-16s: %-21s   ║", "N° Factura", invoice.getInvoiceNumber()));
            System.out.println(String.format("║   %-16s: %-21s   ║", "Fecha Emisión", invoice.getIssueDate()));
            System.out.println(String.format("║   %-16s: $%-20.2f   ║", "Total", invoice.getTotalAmount()));
            System.out.println(String.format("║   %-16s: $%-20.2f   ║", "Impuestos", invoice.getTaxes()));
            System.out.println("╚═════════════════════════════════════════════╝");
        } else {
            System.out.println("X No se encontró la factura con ID: " + id);
        }
    }
}
