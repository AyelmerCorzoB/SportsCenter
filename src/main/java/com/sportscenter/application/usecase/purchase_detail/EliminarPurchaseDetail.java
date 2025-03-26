package com.sportscenter.application.usecase.purchase_detail;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.purchase.PurchaseUseCase;

public class EliminarPurchaseDetail {

    public void eliminar(Scanner sc, PurchaseUseCase purchaseUseCase) {
        System.out.println("\n=== ELIMINAR COMPRA ===");

        System.out.print("ID de la compra a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        try {
            purchaseUseCase.deletePurchase(id);
            System.out.println("✅ Compra eliminada exitosamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar la compra: " + e.getMessage());
        }
    }
}
