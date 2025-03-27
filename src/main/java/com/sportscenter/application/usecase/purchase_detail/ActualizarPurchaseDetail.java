package com.sportscenter.application.usecase.purchase_detail;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationDouble;

public class ActualizarPurchaseDetail {

    public static void actualizar(Scanner sc, PurchaseDetailUseCase purchaseDetailUseCase) {
        System.out.println("\n=== ACTUALIZAR DETALLE DE COMPRA ===");

        // Obtener ID del detalle a actualizar
        System.out.print("ID del detalle de compra a actualizar: ");
        ValidationInt.validate(sc);
        int id =sc.nextInt();
        sc.nextLine();

        // Obtener nuevos valores
        System.out.print("Nueva cantidad: ");
        ValidationInt.validate(sc);
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo precio unitario: ");
        ValidationDouble.validate(sc);
        double unitPrice = sc.nextDouble();
        sc.nextLine();

        try {
            purchaseDetailUseCase.updatePurchaseDetail(id, quantity, unitPrice);
            System.out.println("✅ Detalle de compra actualizado exitosamente.");
            System.out.println("Nuevo subtotal calculado: " + (quantity * unitPrice));
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar el detalle de compra: " + e.getMessage());
        }
    }
}