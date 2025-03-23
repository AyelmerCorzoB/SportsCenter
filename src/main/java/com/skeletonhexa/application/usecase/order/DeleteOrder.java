package com.skeletonhexa.application.usecase.order;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationInt;

public class DeleteOrder {
    public void delete(Scanner sc, OrderUseCase OrderUseCase) {
        System.out.print("Ingresa el ID de el Pedido que quieres eliminar: ");
        ValidationInt.validate(sc);
        int idDelete = sc.nextInt();
        OrderUseCase.deleteOrder(idDelete);
    }
}
