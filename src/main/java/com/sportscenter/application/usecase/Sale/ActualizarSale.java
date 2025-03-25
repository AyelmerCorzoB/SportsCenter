package com.sportscenter.application.usecase.Sale;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class ActualizarSale {

    public void actualizar(Scanner sc, SaleUseCase saleUseCase) {
        System.out.println("\n=== ACTUALIZAR VENTA ===");

        // ID de la venta a actualizar
        System.out.print("ID de la venta: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        // ID del cliente
        System.out.print("Nuevo ID del cliente: ");
        ValidationInt.validate(sc);
        int customerId = sc.nextInt();
        sc.nextLine();

        // Fecha de la venta
        LocalDate saleDate = null;
        while (saleDate == null) {
            System.out.print("Nueva fecha de venta (YYYY-MM-DD): ");
            String dateInput = sc.nextLine();
            try {
                saleDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Fecha inválida. Intenta de nuevo.");
            }
        }

        // ID del método de pago
        System.out.print("Nuevo ID del método de pago: ");
        ValidationInt.validate(sc);
        int paymentMethodId = sc.nextInt();
        sc.nextLine();

        // Total
        System.out.print("Nuevo total de la venta: ");
        while (!sc.hasNextDouble()) {
            System.out.print("Ingrese un número válido para el total: ");
            sc.next();
        }
        double total = sc.nextDouble();
        sc.nextLine();

        // Ejecutar actualización
        saleUseCase.updateSale(id, customerId, saleDate, paymentMethodId, total);
        System.out.println("✅ Venta actualizada exitosamente.");
    }
}
