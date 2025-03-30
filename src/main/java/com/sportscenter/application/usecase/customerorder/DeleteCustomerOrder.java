package com.sportscenter.application.usecase.customerorder;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeleteCustomerOrder {
    public void Delete(Scanner sc, CustomerOrderUseCase customerOrderUseCase) {
        System.out.println("\n=== ELIMINAR ORDEN DE CLIENTE ===");

        System.out.print("ID de la orden a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        customerOrderUseCase.deleteCustomerOrder(id);
        System.out.println("🚀 Orden de cliente eliminada exitosamente.");
    }
}
