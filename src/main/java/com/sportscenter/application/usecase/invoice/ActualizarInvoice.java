package com.sportscenter.application.usecase.invoice;

import java.time.LocalDate;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class ActualizarInvoice {
    public void actualizar(Scanner sc, InvoiceUseCase invoiceUseCase) {
        System.out.println("\n=== ACTUALIZAR FACTURA ===");

        System.out.print("ID de la factura a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("ID de la venta: ");
        ValidationInt.validate(sc);
        int saleId = sc.nextInt();
        sc.nextLine();

        System.out.print("Número de factura: ");
        String invoiceNumber = sc.nextLine();

        System.out.print("Fecha de emisión (AAAA-MM-DD): ");
        LocalDate issueDate = LocalDate.parse(sc.nextLine());

        System.out.print("Total: ");
        double total = sc.nextDouble();

        System.out.print("Impuestos: ");
        double taxes = sc.nextDouble();
        sc.nextLine();

        invoiceUseCase.updateInvoice(id, saleId, invoiceNumber, issueDate, total, taxes);
        System.out.println("✅ Factura actualizada exitosamente.");
    }
}
