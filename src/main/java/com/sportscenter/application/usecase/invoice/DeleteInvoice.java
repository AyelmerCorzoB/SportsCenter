package com.sportscenter.application.usecase.invoice;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeleteInvoice {
    public void Delete(Scanner sc, InvoiceUseCase invoiceUseCase) {
        System.out.println("\n=== ELIMINAR FACTURA ===");

        System.out.print("ID de la factura a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        invoiceUseCase.deleteInvoice(id);
        System.out.println(":D Factura eliminada exitosamente.");
    }
}
