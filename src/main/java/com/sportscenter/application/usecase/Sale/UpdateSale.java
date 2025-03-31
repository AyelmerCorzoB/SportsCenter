package com.sportscenter.application.usecase.Sale;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class UpdateSale {

    public static void Update(Scanner sc, SaleUseCase saleUseCase) {
        System.out.println("\n=== ACTUALIZAR VENTA ===");

        System.out.print("ID de la venta: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo ID del cliente: ");
        ValidationInt.validate(sc);
        int customerId = sc.nextInt();
        sc.nextLine();

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

        System.out.print("Nuevo ID del método de pago: ");
        ValidationInt.validate(sc);
        int paymentMethodId = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo total de la venta: ");
        while (!sc.hasNextDouble()) {
            System.out.print("Ingrese un número válido para el total: ");
            sc.next();
        }
        double total = sc.nextDouble();
        sc.nextLine();

        saleUseCase.updateSale(id, customerId, saleDate, paymentMethodId, total);
        System.out.println(":D Venta actualizada exitosamente.");
    }
}
