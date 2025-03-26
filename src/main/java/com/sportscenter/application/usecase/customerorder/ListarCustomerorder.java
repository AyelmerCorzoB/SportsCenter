package com.sportscenter.application.usecase.customerorder;

import com.sportscenter.domain.entities.CustomerOrder;

public class ListarCustomerorder {
    public void listar(CustomerOrderUseCase customerOrderUseCase) {
        System.out.println("\n=== LISTADO DE ÓRDENES DE CLIENTE ===");
        System.out.printf("%-5s %-12s %-15s %-10s %-10s%n", 
            "ID", "ClienteID", "Fecha Orden", "EstadoID", "Total");
        System.out.println("-------------------------------------------------------------");

        for (CustomerOrder order : customerOrderUseCase.getAllCustomerOrders()) {
            System.out.printf("%-5d %-12d %-15s %-10d %-10.2f%n",
                order.getId(),
                order.getCustomerId(),
                order.getOrderDate(),
                order.getStatusId(),
                order.getTotal()
            );
        }
    }
}
