package com.skeletonhexa.application.usecase.product;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationInt;

public class SearchCustomer {
    public void search(Scanner sc, ProductUseCase custProductUseCase) {
        System.out.print("Ingresa el ID de el producto a buscar: ");
        ValidationInt.validate(sc);
        int idSearch = sc.nextInt();
        sc.nextLine();
        custProductUseCase.getProduct(idSearch);
    }
}
