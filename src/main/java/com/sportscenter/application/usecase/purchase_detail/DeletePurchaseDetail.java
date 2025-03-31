package com.sportscenter.application.usecase.purchase_detail;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeletePurchaseDetail {

    public static void Delete(Scanner sc, PurchaseDetailUseCase purchaseDetailUseCase) {
        System.out.println("\n=== ELIMINAR COMPRA ===");

        System.out.print("ID de la compra a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        try {
            purchaseDetailUseCase.deletePurchaseDetail(id);
            System.out.println(":D Compra eliminada exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al eliminar la compra: " + e.getMessage());
        }
    }
}
