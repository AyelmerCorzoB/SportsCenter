package com.sportscenter.application.usecase.orderstatus;

public class ListOrderStatuses {
    public void List(OrderStatusUseCase orderStatusUseCase) {
        System.out.println("\n=== LISTADO DE ESTADOS DE PEDIDO ===");
        System.out.printf("%-5s %-15s %-30s%n", "ID", "ESTADO", "DESCRIPCIÓN");
        System.out.println("--------------------------------------------------");

        orderStatusUseCase.getAllOrderStatuses().forEach(s -> System.out.printf("%-5d %-15s %-30s%n",
                s.getId(),
                s.getStatusName(),
                s.getDescription() != null ? s.getDescription() : "N/A"));
    }
}