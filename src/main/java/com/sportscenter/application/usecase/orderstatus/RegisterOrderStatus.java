package com.sportscenter.application.usecase.orderstatus;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class RegisterOrderStatus {
    public void Register(Scanner sc, OrderStatusUseCase orderStatusUseCase) {
        System.out.println("\n=== REGISTRO DE ESTADO DE PEDIDO ===");
        
        
        System.out.println("Opciones: \n1. IN PROCESS \n2. DELIVERED \n3. CANCELED");
        System.out.print("Estado : ");
        ValidationInt.validate(sc);
        String statusName = null;
        int statusNameOption = sc.nextInt();
        switch (statusNameOption) {
            case 1:
                statusName = "IN PROCESS";
                break;
            case 2:
                statusName = "DELIVERED";
                break;
            case 3:
                statusName = "CANCELED";
                break;
            default:
                System.out.println("Opcion invalido debe ser 1,2 o 3");
                break;
        }
        sc.nextLine();

        System.out.print("Descripción (opcional): ");
        String description = sc.nextLine();
        
        orderStatusUseCase.registerOrderStatus(statusName, description.isEmpty() ? null : description);
        System.out.println("🚀 Estado de pedido registrado exitosamente.");
    }
}