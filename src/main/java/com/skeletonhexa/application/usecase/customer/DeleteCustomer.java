package com.skeletonhexa.application.usecase.customer;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationInt;

public class DeleteCustomer {
    public void delete(Scanner sc, CustomerUseCase customerUseCase) {
        System.out.print("Ingresa el ID de el Cliente que quieres eliminar: ");
        ValidationInt.validate(sc);
        int idDelete = sc.nextInt();
        customerUseCase.deleteCustomer(idDelete);
    }
}
