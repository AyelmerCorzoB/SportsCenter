package com.sportscenter.application.usecase.purchase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;

public class RegisterPurchase {
    
    public void Register(Scanner sc, PurchaseUseCase purchaseUseCase) {
        System.out.println("\n=== REGISTRAR NUEVA COMPRA ===");
        
        System.out.print("Fecha de compra (yyyy-MM-dd): ");
        String fechaStr = sc.nextLine();
        LocalDate date = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
        
        System.out.print("ID del proveedor: ");
        ValidationInt.validate(sc);
        int supplierId = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Opciones de estado: \n1. PENDING \n2. RECEIVED \n3. CANCELED");
        System.out.print("Estado: ");
        ValidationInt.validate(sc);
        int statusId = sc.nextInt();
        sc.nextLine();
        
        System.out.print("ID del empleado: ");
        ValidationInt.validate(sc);
        int employeeId = sc.nextInt();
        sc.nextLine();
        
        try {
            purchaseUseCase.registerPurchase(date, supplierId, statusId, employeeId);
            System.out.println(":D Compra registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al registrar compra: " + e.getMessage());
        }
    }
}