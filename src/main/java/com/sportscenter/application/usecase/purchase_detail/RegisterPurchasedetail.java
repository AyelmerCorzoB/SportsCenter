package com.sportscenter.application.usecase.purchase_detail;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationDouble;

public class RegisterPurchasedetail {
    public static void Register(Scanner sc, PurchaseDetailUseCase purchaseDetailUseCase) {
        System.out.println("\n=== REGISTRAR DETALLE DE COMPRA ===");

        System.out.print("ID de la compra: ");
        ValidationInt.validate(sc);
        int purchaseId = sc.nextInt();
        sc.nextLine();

        System.out.print("ID del producto: ");
        ValidationInt.validate(sc);
        int productId = sc.nextInt();
        sc.nextLine();

        System.out.print("Cantidad: ");
        ValidationInt.validate(sc);
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("Precio unitario: ");
        ValidationDouble.validate(sc);
        double unitPrice = sc.nextDouble();
        sc.nextLine();

        try {
            purchaseDetailUseCase.registerPurchaseDetail(purchaseId, productId, quantity, unitPrice);
            System.out.println(":D Detalle de compra registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al registrar detalle de compra: " + e.getMessage());
        }
    }
}