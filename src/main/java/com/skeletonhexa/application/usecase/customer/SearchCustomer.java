package com.skeletonhexa.application.usecase.customer;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationInt;

public class SearchCustomer {
    public void search(Scanner sc, CustomerUseCase customerUseCase) {
        System.out.print("Ingresa el ID de el Cliente a buscar: ");
        ValidationInt.validate(sc);
        int idSearch = sc.nextInt();
        sc.nextLine();
        customerUseCase.getCustomer(idSearch);
    }
}
