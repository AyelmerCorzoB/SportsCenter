package com.sportscenter.application.usecase.customerorder;

import java.time.LocalDate;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class RegistroCustomerorder {
    public void registro(Scanner sc, CustomerOrderUseCase customerOrderUseCase) {
        System.out.println("\n=== REGISTRO DE ORDEN DE CLIENTE ===");

        System.out.print("ID del Cliente: ");
        ValidationInt.validate(sc);
        int customerId = sc.nextInt();
        sc.nextLine();

        System.out.print("Fecha de la orden (AAAA-MM-DD): ");
        LocalDate orderDate = LocalDate.parse(sc.nextLine());

        System.out.println("Opciones de estado: \n1. IN PROCESS \n2. DELIVERED \n3. CANCELED");
        System.out.print("Seleccione el ID del estado: ");
        ValidationInt.validate(sc);
        int statusId = sc.nextInt();
        sc.nextLine();

        System.out.print("Total: ");
        double total = sc.nextDouble();
        sc.nextLine();

        System.out.print("ID del Usuario que registra: ");
        ValidationInt.validate(sc);
        int userId = sc.nextInt();
        sc.nextLine();

        customerOrderUseCase.registerCustomerOrder(customerId, orderDate, statusId, total, userId);
        System.out.println("✅ Orden de cliente registrada exitosamente.");
    }
}
