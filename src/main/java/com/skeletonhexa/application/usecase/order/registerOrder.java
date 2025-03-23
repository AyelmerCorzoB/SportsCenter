package com.skeletonhexa.application.usecase.order;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;

public class registerOrder {
    public void register(Scanner sc, OrderUseCase OrderUseCase) {
        try {

            System.out.print("Ingrese el ID del cliente: ");
            int customerId = Integer.parseInt(sc.nextLine());

            System.out.print("Ingrese la fecha del pedido (YYYY-MM-DD): ");
            String orderDateStr = sc.nextLine();
            Date orderDate = Date.valueOf(orderDateStr);

            System.out.print("Ingrese el estado del pedido: ");
            String status = sc.nextLine();

            System.out.print("Ingrese el total del pedido: ");
            BigDecimal total = new BigDecimal(sc.nextLine());

            System.out.print("Ingrese el ID del usuario: ");
            int userId = Integer.parseInt(sc.nextLine());

            OrderUseCase.registerOrder(customerId, orderDate, status, total, userId);
            System.out.println("✅ Pedido registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
}
