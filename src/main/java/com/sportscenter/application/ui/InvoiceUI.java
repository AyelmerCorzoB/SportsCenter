package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.invoice.ActualizarInvoice;
import com.sportscenter.application.usecase.invoice.BuscarInvoice;
import com.sportscenter.application.usecase.invoice.EliminarInvoice;
import com.sportscenter.application.usecase.invoice.InvoiceUseCase;
import com.sportscenter.application.usecase.invoice.RegistroInvoice;

public class InvoiceUI {
    public static void manejarMenuInvoice(Scanner sc, InvoiceUseCase invoiceUseCase) {
        int opcion;
        do {
            System.out.println("\n******** MENÚ DE EMPLEADOS ********");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Buscar empleado por ID");
            System.out.println("3. Listar todas los empleados");
            System.out.println("4. Actualizar un empleado");
            System.out.println("5. Eliminar una empleado");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegistroInvoice().registro(sc, invoiceUseCase);
                    break;
                case 2:
                    new BuscarInvoice().buscar(sc, invoiceUseCase);
                    break;
                case 3:
                    invoiceUseCase.getAllInvoices();
                    break;
                case 4:
                    new ActualizarInvoice().actualizar(sc, invoiceUseCase);
                    break;
                case 5:
                    new EliminarInvoice().eliminar(sc, invoiceUseCase);
                    break;
                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 6);
    }
}
