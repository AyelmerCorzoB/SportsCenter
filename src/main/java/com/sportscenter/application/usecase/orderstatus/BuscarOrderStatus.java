package com.sportscenter.application.usecase.orderstatus;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.OrderStatus;

public class BuscarOrderStatus {
    public void buscar(Scanner sc, OrderStatusUseCase orderStatusUseCase) {
        System.out.println("\n=== BUSCAR ESTADO DE PEDIDO ===");
        
        System.out.print("ID del estado: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        OrderStatus status = orderStatusUseCase.getOrderStatusById(id);
        if(status != null) {
            System.out.println("\nInformación del estado:");
            System.out.println("ID: " + status.getId());
            System.out.println("Estado: " + status.getStatusName());
            System.out.println("Descripción: " + (status.getDescription() != null ? status.getDescription() : "N/A"));
        } else {
            System.out.println("X No se encontró el estado con ID: " + id);
        }
    }
}