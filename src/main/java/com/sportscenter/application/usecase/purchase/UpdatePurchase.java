package com.sportscenter.application.usecase.purchase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;

public class UpdatePurchase {

    public void Update(Scanner sc, PurchaseUseCase purchaseUseCase) {
        System.out.println("\n=== ACTUALIZAR COMPRA ===");

        System.out.print("ID de la compra a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nueva fecha (yyyy-MM-dd): ");
        String fechaStr = sc.nextLine();
        LocalDate date = LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Nuevo ID de proveedor: ");
        ValidationInt.validate(sc);
        int supplierId = sc.nextInt();
        sc.nextLine();

        System.out.println("Opciones de estado: \n1. PENDING \n2. RECEIVED \n3. CANCELED");
        System.out.print("Nuevo estado: ");
        ValidationInt.validate(sc);
        int statusId = sc.nextInt();
        sc.nextLine();

        try {
            purchaseUseCase.updatePurchase(id, date, supplierId, statusId);
            System.out.println(":D Compra actualizada exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al actualizar compra: " + e.getMessage());
        }
    }
}