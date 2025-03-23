package com.skeletonhexa.application.usecase.Purchase;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationInt;

public class DeletePurchase {
    public void delete(Scanner sc, PurchaseUseCase PurchaseUseCase) {
        System.out.print("Ingresa el ID de la compra que quieres eliminar: ");
        ValidationInt.validate(sc);
        int idDelete = sc.nextInt();
        PurchaseUseCase.deletePurchase(idDelete);
    }
}
