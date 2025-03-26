package com.sportscenter.application.usecase.customer;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class ActualizarCustomer {
    public void actualizar(Scanner sc, CustomerUseCase customerUseCase) {
        System.out.println("\n=== ACTUALIZAR CLIENTE ===");

        System.out.print("ID del cliente a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("ID del tipo de cliente: ");
        ValidationInt.validate(sc);
        int customerTypeId = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre: ");
        String name = sc.nextLine();

        System.out.print("Documento de identidad: ");
        String identityDocument = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Teléfono: ");
        String phone = sc.nextLine();

        System.out.print("Dirección: ");
        String address = sc.nextLine();

        customerUseCase.updateCustomer(id, customerTypeId, name, identityDocument, email, phone, address);
        System.out.println("✅ Cliente actualizado exitosamente.");
    }
}
