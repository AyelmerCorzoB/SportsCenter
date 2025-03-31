package com.sportscenter.application.usecase.invoice;

import java.time.LocalDate;
import java.util.Scanner;

public class RegisterInvoice {
    public void register(Scanner sc, InvoiceUseCase invoiceUseCase) {
        System.out.println("\n=== REGISTRO DE FACTURA ===");

        int saleId;
        while (true) {
            System.out.print("ID de la venta: ");
            if (sc.hasNextInt()) {
                saleId = sc.nextInt();
                sc.nextLine();
                break;
            } else {
                System.out.println("Error: Debe ingresar un número válido para el ID de venta.");
                sc.next();
            }
        }

        String invoiceNumber;
        while (true) {
            System.out.print("Número de factura: ");
            invoiceNumber = sc.nextLine().trim();
            if (!invoiceNumber.isEmpty()) {
                break;
            }
            System.out.println("Error: El número de factura no puede estar vacío.");
        }

        LocalDate entryDate;
        System.out.print("¿Desea ingresar una fecha personalizada? (S/N): ");
        String opcionFecha = sc.nextLine().trim().toUpperCase();

        if (opcionFecha.equals("S")) {
            while (true) {
                try {
                    System.out.print("Ingrese fecha (YYYY-MM-DD): ");
                    String entryDateStr = sc.nextLine();
                    entryDate = LocalDate.parse(entryDateStr);

                    if (entryDate.isAfter(LocalDate.now())) {
                        System.out.println("Error: La fecha no puede ser futura.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Error: Formato de fecha inválido. Use YYYY-MM-DD");
                }
            }
        } else {
            entryDate = LocalDate.now();
            System.out.println("Se asignará la fecha actual: " + entryDate);
        }

        double totalAmount;
        while (true) {
            System.out.print("Monto total: ");
            if (sc.hasNextDouble()) {
                totalAmount = sc.nextDouble();
                sc.nextLine();
                if (totalAmount > 0) {
                    break;
                }
                System.out.println("Error: El monto debe ser mayor a cero.");
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                sc.next();
            }
        }

        double taxes;
        while (true) {
            System.out.print("Impuestos (Ej: 0.19): ");
            if (sc.hasNextDouble()) {
                taxes = sc.nextDouble();
                sc.nextLine();
                if (taxes >= 0 && taxes <= 1) {
                    break;
                }
                System.out.println("Error: Los impuestos deben estar entre 0 y 1 (0% a 100%).");
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                sc.next();
            }
        }

        try {
            invoiceUseCase.registerInvoice(saleId, invoiceNumber, entryDate, totalAmount, taxes);
            System.out.println("\n:D Factura registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("\nX Error al registrar factura: " + e.getMessage());
        }
    }
}
