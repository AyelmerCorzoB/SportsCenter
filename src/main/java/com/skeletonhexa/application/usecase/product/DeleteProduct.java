package com.skeletonhexa.application.usecase.product;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationInt;

public class DeleteProduct {
    public void delete(Scanner sc, ProductUseCase ProductUseCase) {
        System.out.print("Ingresa el ID de el Producto que quieres eliminar: ");
        ValidationInt.validate(sc);
        int idDelete = sc.nextInt();
        ProductUseCase.deleteProduct(idDelete);
    }
}
