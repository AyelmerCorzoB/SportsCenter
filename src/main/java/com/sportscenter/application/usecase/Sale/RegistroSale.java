package com.sportscenter.application.usecase.Sale;

import java.time.LocalDate;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class RegistroSale {
    public void registro(Scanner sc, SaleUseCase saleUseCase) {
        System.out.println("\n=== REGISTRO DE VENTA ===");

        System.out.print("ID del cliente: ");
        ValidationInt.validate(sc);
        int customerId = sc.nextInt();
        sc.nextLine();

        // Fecha de la venta (por defecto, la fecha actual)
        LocalDate saleDate = LocalDate.now();
        System.out.println("Fecha de la venta (automática): " + saleDate);

        System.out.print("ID del método de pago: ");
        ValidationInt.validate(sc);
        int paymentMethodId = sc.nextInt();
        sc.nextLine();

        System.out.print("Total de la venta: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            sc.next();
        }
        double total = sc.nextDouble();
        sc.nextLine();

        System.out.print("ID del usuario: ");
        ValidationInt.validate(sc);
        int userId = sc.nextInt();
        sc.nextLine();

        saleUseCase.registerSale(customerId, saleDate, paymentMethodId, total, userId);
        System.out.println("✅ Venta registrada exitosamente.");
    }
}
