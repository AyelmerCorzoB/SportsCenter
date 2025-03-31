package com.sportscenter.application.usecase.customer_type;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class RegisterCustomerType {
    public void Register(Scanner sc, CustomerTypeUseCase customerTypeUseCase) {
        System.out.println("\n=== REGISTRO DE TIPO DE CLIENTE ===");

        System.out.println("Opciones: \n1. INDIVIDUAL \n2. COMPANY");
        System.out.print("Tipo de cliente: ");
        ValidationInt.validate(sc);
        String typeName = null;
        int TypeNameOption = sc.nextInt();
        switch (TypeNameOption) {
            case 1:
                typeName = "INDIVIDUAL";
                break;
            case 2:
                typeName = "COMPANY";
                break;
            default:
                System.out.println("Opcion invalido debe ser 1,2 o 3");
                break;
        }
        System.out.print("Descripción (opcional): ");
        String description = sc.nextLine();

        customerTypeUseCase.registerCustomerType(typeName, description.isEmpty() ? null : description);
        System.out.println(":D Tipo de cliente registrado exitosamente.");
    }
}