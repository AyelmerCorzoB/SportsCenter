package com.skeletonhexa.application.usecase.order;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationInt;

public class SearchCustomer {
    public void search(Scanner sc, OrderUseCase custOrderUseCase) {
        System.out.print("Ingresa el ID de el Pedido a buscar: ");
        ValidationInt.validate(sc);
        int idSearch = sc.nextInt();
        sc.nextLine();
        custOrderUseCase.getOrder(idSearch);
    }
}
