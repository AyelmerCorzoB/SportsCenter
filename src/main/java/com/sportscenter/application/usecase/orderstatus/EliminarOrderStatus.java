package com.sportscenter.application.usecase.orderstatus;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;


public class EliminarOrderStatus {
    public void eliminar(Scanner sc, OrderStatusUseCase orderStatusUseCase) {
        System.out.println("\n=== ELIMINAR ESTADO DE PEDIDO ===");
        
        System.out.print("ID del estado a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        orderStatusUseCase.deleteOrderStatus(id);
        System.out.println("✅ Estado de pedido eliminado exitosamente.");
    }
}