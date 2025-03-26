package com.sportscenter.application.usecase.customer;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class EliminarCustomer {
    public void eliminar(Scanner sc, CustomerUseCase customerUseCase) {
        System.out.println("\n=== ELIMINAR CLIENTE ===");

        System.out.print("ID del cliente a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        customerUseCase.deleteCustomer(id);
        System.out.println("✅ Cliente eliminado exitosamente.");
    }
}
