package com.sportscenter.application.ui.Admin;

import java.util.List;
import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.invoice.*;
import com.sportscenter.domain.entities.Invoice;

public class InvoiceUI {
    public static void mostrarMenu(Scanner sc, InvoiceUseCase invoiceUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            System.out.println("\n******** MENÚ DE FACTURAS ********");
            System.out.println("1. Registrar factura");
            System.out.println("2. Search factura por ID");
            System.out.println("3. Listar todas las facturas");
            System.out.println("4. Actualizar una factura");
            System.out.println("5. Eliminar una factura");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterInvoice().Register(sc, invoiceUseCase);
                    break;
                case 2:
                    new SearchInvoice().Search(sc, invoiceUseCase);
                    break;
                case 3:
                    ListFacturas(invoiceUseCase);
                    break;
                case 4:
                    new UpdateInvoice().Update(sc, invoiceUseCase);
                    break;
                case 5:
                    new DeleteInvoice().Delete(sc, invoiceUseCase);
                    break;
                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("X Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 6);
    }

    private static void ListFacturas(InvoiceUseCase invoiceUseCase) {
        List<Invoice> facturas = invoiceUseCase.getAllInvoices();

        if (facturas.isEmpty()) {
            System.out.println("\nNo hay facturas registradas.");
            return;
        }

        System.out.println("\nLISTADO DE FACTURAS");
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-15s | %-20s | %-15s | %-15s | %-10s | %-8s |\n",
                "ID", "N° Factura", "Cliente", "Documento", "Método Pago", "Total", "Items");
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");

        for (Invoice factura : facturas) {
            System.out.printf("| %-10d | %-15s | %-20s | %-15s | %-15s | $%-9.2f | %-8d |\n",
                    factura.getId(),
                    factura.getInvoiceNumber(),
                    factura.getCustomerName(),
                    factura.getCustomerDocument(),
                    factura.getPaymentMethod(),
                    factura.getTotalAmount(),
                    factura.getItemsCount());
        }
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");
        System.out.println("Total de facturas: " + facturas.size());
    }
}
