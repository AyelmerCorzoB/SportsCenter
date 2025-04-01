package com.sportscenter.application.usecase.customer_type;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class RegisterCustomerType {
    public void Register(Scanner sc, CustomerTypeUseCase customerTypeUseCase) {
        System.out.println("\n=== REGISTRO DE TIPO DE CLIENTE ===");

        System.out.println("Opciones: \n1. INDIVIDUAL \n2. COMPANY");
        System.out.print("Tipo de cliente: ");
        ValidationInt.validate(sc);
        int typeNameOption = sc.nextInt();
        sc.nextLine();

        String typeName = null;
        switch (typeNameOption) {
            case 1:
                typeName = "INDIVIDUAL";
                break;
            case 2:
                typeName = "COMPANY";
                break;
            default:
                System.out.println("Opción inválida. Debe ser 1 o 2.");
                return;
        }

        System.out.print("Descripción (opcional): ");
        String description = sc.nextLine().trim();

        try {
            customerTypeUseCase.registerCustomerType(
                    typeName,
                    description.isEmpty() ? null : description);
            System.out.println(":D Tipo de cliente registrado exitosamente.");
        } catch (Exception e) {

            System.out.println("Error: No se pudo registrar el tipo de cliente. " + e.getMessage());
        }
    }
}