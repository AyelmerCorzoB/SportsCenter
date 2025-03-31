package com.sportscenter.application.usecase.invoice;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Invoice;
import java.time.LocalDate;
import java.util.Scanner;

public class UpdateInvoice {
    public void Update(Scanner sc, InvoiceUseCase invoiceUseCase) {
        System.out.println("\n=== ACTUALIZAR FACTURA ===");

        System.out.print("ID de la factura a Update: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        Invoice invoice = invoiceUseCase.getInvoiceById(id);
        if (invoice == null) {
            System.out.println(" No se encontró una factura con el ID proporcionado");
            return;
        }

        System.out.println("\nDatos actuales de la factura:");
        System.out.println("Número de factura: " + invoice.getInvoiceNumber());
        System.out.println("Fecha de emisión: " + invoice.getIssueDate());
        System.out.println("Total: " + invoice.getTotalAmount());
        System.out.println("Impuestos: " + invoice.getTaxes());

        System.out.println("\nIngrese los nuevos datos (deje en blanco para mantener el valor actual):");

        System.out.print("Nuevo número de factura [" + invoice.getInvoiceNumber() + "]: ");
        String newInvoiceNumber = sc.nextLine();
        if (!newInvoiceNumber.isBlank()) {
            invoice.setInvoiceNumber(newInvoiceNumber);
        }

        System.out.print("Nueva fecha de emisión (AAAA-MM-DD) [" + invoice.getIssueDate() + "]: ");
        String dateInput = sc.nextLine();
        if (!dateInput.isBlank()) {
            invoice.setIssueDate(LocalDate.parse(dateInput));
        }

        System.out.print("Nuevo total [" + invoice.getTotalAmount() + "]: ");
        String totalInput = sc.nextLine();
        if (!totalInput.isBlank()) {
            invoice.setTotalAmount(Double.parseDouble(totalInput));
        }

        System.out.print("Nuevos impuestos [" + invoice.getTaxes() + "]: ");
        String taxesInput = sc.nextLine();
        if (!taxesInput.isBlank()) {
            invoice.setTaxes(Double.parseDouble(taxesInput));
        }

        try {
            invoiceUseCase.updateInvoice(invoice);
            System.out.println(":D Factura actualizada exitosamente.");
        } catch (Exception e) {
            System.out.println(" Error al actualizar la factura: " + e.getMessage());
        }
    }
}