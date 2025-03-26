package com.sportscenter.application.usecase.purchase_detail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.purchase.PurchaseUseCase;

public class RegistrarPurchesedetail {
    public void registrar(Scanner sc, PurchaseUseCase purchaseUseCase) {
        System.out.println("\n=== REGISTRAR NUEVA COMPRA ===");

        // Fecha de la compra
        LocalDate date = null;
        while (date == null) {
            System.out.print("Fecha de compra (yyyy-MM-dd): ");
            String fechaStr = sc.nextLine();
            try {
                date = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Fecha inválida. Intenta de nuevo.");
            }
        }

        // ID del proveedor
        System.out.print("ID del proveedor: ");
        ValidationInt.validate(sc);
        int supplierId = sc.nextInt();
        sc.nextLine();

        // Estado de la compra
        System.out.println("Opciones de estado: \n1. PENDING \n2. RECEIVED \n3. CANCELED");
        System.out.print("Estado: ");
        ValidationInt.validate(sc);
        int statusId = sc.nextInt();
        sc.nextLine();

        // ID del empleado
        System.out.print("ID del empleado: ");
        ValidationInt.validate(sc);
        int employeeId = sc.nextInt();
        sc.nextLine();

        // Registro
        try {
            purchaseUseCase.registerPurchase(date, supplierId, statusId, employeeId);
            System.out.println("✅ Compra registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al registrar compra: " + e.getMessage());
        }
    }
}
