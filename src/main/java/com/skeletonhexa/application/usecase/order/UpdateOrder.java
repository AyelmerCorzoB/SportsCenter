package com.skeletonhexa.application.usecase.order;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;

public class UpdateOrder {
    public void update(Scanner sc, OrderUseCase orderUseCase) {
        try {

            System.out.print("Ingrese el ID del pedido a actualizar: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Ingrese el ID del cliente: ");
            int customerId = Integer.parseInt(sc.nextLine());

            System.out.print("Ingrese la fecha del pedido (YYYY-MM-DD): ");
            String orderDateStr = sc.nextLine();
            Date orderDate = Date.valueOf(orderDateStr);

            System.out.println("Seleccione el estado del pedido:");
            System.out.println("1. IN PROCESS");
            System.out.println("2. DELIVERED");
            System.out.println("3. CANCELED");
            System.out.print("Ingrese una opción (1, 2 o 3): ");
            int statusOption = Integer.parseInt(sc.nextLine());

            String status;
            switch (statusOption) {
                case 1:
                    status = "IN_PROCESS";
                    break;
                case 2:
                    status = "DELIVERED";
                    break;
                case 3:
                    status = "CANCELED";
                    break;
                default:
                    throw new IllegalArgumentException("Opción no válida. Debe ser 1, 2 o 3.");
            }

            System.out.print("Ingrese el total del pedido: ");
            BigDecimal total = new BigDecimal(sc.nextLine());

            System.out.print("Ingrese el ID del usuario: ");
            int userId = Integer.parseInt(sc.nextLine());

            orderUseCase.updateOrder(id, customerId, orderDate, status, total, userId);
            System.out.println("✅ Pedido actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
}