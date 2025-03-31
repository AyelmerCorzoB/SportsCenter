package com.sportscenter.application.usecase.purchase;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;

public class DeletePurchase {
    
    public void Delete(Scanner sc, PurchaseUseCase purchaseUseCase) {
        System.out.println("\n=== ELIMINAR COMPRA ===");
        
        System.out.print("ID de la compra a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        try {
            purchaseUseCase.deletePurchase(id);
            System.out.println(":D Compra eliminada exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al eliminar compra: " + e.getMessage());
        }
    }
}