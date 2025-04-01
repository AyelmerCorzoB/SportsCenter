package com.sportscenter.application.usecase.customerorder;

import java.time.LocalDate;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class RegisterCustomerorder {
    public void Register(Scanner sc, CustomerOrderUseCase customerOrderUseCase) {
        System.out.println("\n=== REGISTRO DE ORDEN DE CLIENTE ===");

        System.out.print("ID del Cliente: ");
        ValidationInt.validate(sc);
        int customerId = sc.nextInt();
        sc.nextLine();

        LocalDate orderDate;
        System.out.print("¿Desea ingresar una fecha personalizada? (S/N): ");
        String opcionFecha = sc.nextLine().trim().toUpperCase();

        if (opcionFecha.equals("S")) {
            while (true) {
                try {
                    System.out.print("Ingrese fecha (YYYY-MM-DD): ");
                    String orderDateStr = sc.nextLine();
                    orderDate = LocalDate.parse(orderDateStr);
                    if (orderDate.isAfter(LocalDate.now())) {
                        System.out.println("La fecha no puede ser futura");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Formato de fecha inválido. Use YYYY-MM-DD");
                }
            }
        } else {
            orderDate = LocalDate.now();
            System.out.println("Se asignará la fecha actual: " + orderDate);
        }

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
        System.out.println(":D Orden de cliente registrada exitosamente.");
    }
}
