package com.sportscenter.application.usecase.customerorder;

import java.time.LocalDate;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class ActualizarCustomerorder {
    public void actualizar(Scanner sc, CustomerOrderUseCase customerOrderUseCase) {
        System.out.println("\n=== ACTUALIZAR ORDEN DE CLIENTE ===");

        System.out.print("ID de la orden a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("ID del cliente: ");
        ValidationInt.validate(sc);
        int customerId = sc.nextInt();
        sc.nextLine();

        System.out.print("Fecha de la orden (AAAA-MM-DD): ");
        LocalDate orderDate = LocalDate.parse(sc.nextLine());

        System.out.print("ID del estado: ");
        ValidationInt.validate(sc);
        int statusId = sc.nextInt();
        sc.nextLine();

        System.out.print("Total: ");
        double total = sc.nextDouble();
        sc.nextLine(); // limpiar buffer

        customerOrderUseCase.updateCustomerOrder(id, customerId, orderDate, statusId, total);
        System.out.println("✅ Orden de cliente actualizada exitosamente.");
    }
}
