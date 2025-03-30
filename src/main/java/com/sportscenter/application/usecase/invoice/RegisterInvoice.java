package com.sportscenter.application.usecase.invoice;

import java.time.LocalDate;
import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationString;

public class RegisterInvoice {

    public void Register(Scanner sc, InvoiceUseCase invoiceUseCase) {
        System.out.println("\n=== REGISTRO DE FACTURA ===");

        System.out.print("ID de la venta: ");
        ValidationInt.validate(sc);
        int saleId = sc.nextInt();
        sc.nextLine();

        System.out.print("Número de factura: ");
        String invoiceNumber = ValidationString.validate(sc);

        System.out.print("Fecha de emisión (AAAA-MM-DD): ");
        LocalDate issueDate = LocalDate.parse(sc.nextLine());

        System.out.print("Monto total: ");
        double totalAmount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Impuestos: ");
        double taxes = sc.nextDouble();
        sc.nextLine();

        try {
            invoiceUseCase.registerInvoice(saleId, invoiceNumber, issueDate, totalAmount, taxes);
            System.out.println("🚀 Factura registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al registrar factura: " + e.getMessage());
        }
        
    }
}