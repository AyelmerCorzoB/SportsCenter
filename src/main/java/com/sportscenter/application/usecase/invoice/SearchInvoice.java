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
            System.out.println("\nInformación de la factura:");
            System.out.println("ID: " + invoice.getId());
            System.out.println("ID Venta: " + invoice.getSaleId());
            System.out.println("Número de Factura: " + invoice.getInvoiceNumber());
            System.out.println("Fecha de Emisión: " + invoice.getIssueDate());
            System.out.println("Total: $" + invoice.getTotalAmount());
            System.out.println("Impuestos: $" + invoice.getTaxes());
        } else {
            System.out.println("X No se encontró la factura con ID: " + id);
        }
    }
}
