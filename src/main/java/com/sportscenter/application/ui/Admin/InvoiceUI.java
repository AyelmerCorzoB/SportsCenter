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
        
        do {
            String menu = """
                    \n╔══════════════════════════════╗
                    ║        MENÚ FACTURAS         ║
                    ╠══════════════════════════════╣
                    ║ 1. Registrar Factura         ║
                    ║ 2. Buscar Factura por ID     ║
                    ║ 3. Listar todas las Facturas ║
                    ║ 4. Actualizar Factura        ║
                    ║ 5. Eliminar Factura          ║
                    ║ 6. Volver                    ║
                    ╚══════════════════════════════╝
                    Seleccione una opción:""";
            ConsoleUtils.clear();
            System.out.print(menu);
            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterInvoice().register(sc, invoiceUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 2:
                    new SearchInvoice().Search(sc, invoiceUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 3:
                    ConsoleUtils.clear();
                    ListFacturas(invoiceUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 4:
                    new UpdateInvoice().Update(sc, invoiceUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 5:
                    new DeleteInvoice().Delete(sc, invoiceUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
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
        ConsoleUtils.clear();
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                            LISTADO DE FACTURAS                                          ║");
        System.out.println("╠══════╦═════════════════╦══════════════════════╦════════════════╦════════════════╦═════════════╦═════════╣");
        System.out.printf("║ %-4s ║ %-15s ║ %-20s ║ %-14s ║ %-14s ║ %-11s ║ %-5s   ║%n",
                "ID", "N° Factura", "Cliente", "Documento", "Método Pago", "Total", "Items");
        System.out.println("╠══════╬═════════════════╬══════════════════════╬════════════════╬════════════════╬═════════════╬═════════╣");
    
        List<Invoice> facturas = invoiceUseCase.getAllInvoices();
    
        if (facturas.isEmpty()) {
            System.out.println("║                                  No hay facturas registradas                                      ║");
        } else {
            for (Invoice factura : facturas) {
                System.out.printf("║ %-4d ║ %-15s ║ %-20s ║ %-14s ║ %-14s ║ $%-10.2f ║ %-5d   ║%n",
                        factura.getId(),
                        factura.getInvoiceNumber(),
                        truncate(factura.getCustomerName(), 20),
                        truncate(factura.getCustomerDocument(), 14),
                        truncate(factura.getPaymentMethod(), 14),
                        factura.getTotalAmount(),
                        factura.getItemsCount());
            }
        }
        System.out.println("╚══════╩═════════════════╩══════════════════════╩════════════════╩════════════════╩═════════════╩═════════╝");
        System.out.println("Total de facturas: " + facturas.size());
    }
    
    // Método auxiliar para truncar strings largos
    private static String truncate(String value, int length) {
        if (value == null) return "";
        return value.length() > length ? value.substring(0, length - 3) + "..." : value;
    }
}
