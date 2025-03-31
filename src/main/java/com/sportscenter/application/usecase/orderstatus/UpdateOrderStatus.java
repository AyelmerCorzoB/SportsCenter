package com.sportscenter.application.usecase.orderstatus;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class UpdateOrderStatus {
    public void Update(Scanner sc, OrderStatusUseCase orderStatusUseCase) {
        System.out.println("\n=== ACTUALIZAR ESTADO DE PEDIDO ===");
        
        System.out.print("ID del estado a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        
        System.out.println("Opciones: \n1. IN PROCESS \n2. DELIVERED \n3. CANCELED");
        System.out.print("Estado : ");
        ValidationInt.validate(sc);
        String newStatusName = null;
        int newStatusNameOption = sc.nextInt();
        switch (newStatusNameOption) {
            case 1:
                newStatusName = "IN PROCESS";
                break;
            case 2:
                newStatusName = "DELIVERED";
                break;
            case 3:
                newStatusName = "CANCELED";
                break;
            default:
                System.out.println("Opcion invalido debe ser 1,2 o 3");
                break;
        }
        sc.nextLine();
        
        System.out.print("Nueva descripción (opcional): ");
        String newDescription = sc.nextLine();
        
        orderStatusUseCase.updateOrderStatus(id, newStatusName, newDescription.isEmpty() ? null : newDescription);
        System.out.println(":D Estado de pedido actualizado exitosamente.");
    }
}