package com.sportscenter.application.usecase.customerorder;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.CustomerOrder;

public class BuscarCustomerorder {
    public void buscar(Scanner sc, CustomerOrderUseCase customerOrderUseCase) {
        System.out.println("\n=== BUSCAR ORDEN DE CLIENTE ===");

        System.out.print("ID de la orden: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine(); 

        CustomerOrder order = customerOrderUseCase.getCustomerOrderById(id);
        if (order != null) {
            System.out.println("\nInformación de la orden:");
            System.out.println("ID: " + order.getId());
            System.out.println("ID Cliente: " + order.getCustomerId());
            System.out.println("Fecha de Orden: " + order.getOrderDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
            System.out.println("ID Estado: " + order.getStatusId());
            System.out.println("Total: $" + order.getTotal());
            System.out.println("ID Usuario: " + order.getUserId());
        } else {
            System.out.println("X No se encontró la orden con ID: " + id);
        }
    }
}
