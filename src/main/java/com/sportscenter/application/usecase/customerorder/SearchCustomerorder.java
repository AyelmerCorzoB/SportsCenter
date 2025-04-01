package com.sportscenter.application.usecase.customerorder;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.CustomerOrder;

public class SearchCustomerorder {
    public void Search(Scanner sc, CustomerOrderUseCase customerOrderUseCase) {
        System.out.println("\n=== BUSCAR ORDEN DE CLIENTE ===");

        System.out.print("ID de la orden: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        CustomerOrder order = customerOrderUseCase.getCustomerOrderById(id);
        if (order != null) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║      INFORMACIÓN DE LA ORDEN ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println(String.format("║ ID: %-24s ║", order.getId()));
            System.out.println(String.format("║ Cliente: %-19s ║", order.getCustomerId()));
            System.out.println(
                    String.format("║ Fecha: %-21s ║", order.getOrderDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
            System.out.println(String.format("║ Estado: %-20s ║", order.getStatusId()));
            System.out.println(String.format("║ Total: $%-20s ║", order.getTotal()));
            System.out.println(String.format("║ Usuario: %-19s ║", order.getUserId()));
            System.out.println("╚══════════════════════════════╝");
        } else {
            System.out.println("X No se encontró la orden con ID: " + id);
        }
    }
}
