package com.sportscenter.application.usecase.customer;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationString;

public class RegistroCustomer {
    public void registro(Scanner sc, CustomerUseCase customerUseCase) {
        System.out.println("\n=== REGISTRO DE CLIENTE ===");

        int typeId;
        do {
            System.out.println("Tipo de cliente \nOpciones: \n1. INDIVIDUAL \n2. COMPANY");
            System.out.print("Tipo: ");
            ValidationInt.validate(sc);
            typeId = sc.nextInt();
            if (typeId != 1 && typeId != 2) {
                System.out.println("Opción inválida, debe ser 1 o 2");
            }
        } while (typeId != 1 && typeId != 2);
        sc.nextLine(); 
        System.out.print("Nombre: ");
        String name = ValidationString.validate(sc);

        System.out.print("Documento de identidad: ");
        String identityDoc = ValidationString.validate(sc);

        System.out.print("Email: ");
        String email = ValidationString.validate(sc);

        System.out.print("Teléfono: ");
        String phone = ValidationString.validate(sc);

        System.out.print("Dirección: ");
        String address = ValidationString.validate(sc);

        LocalDate regDate = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print("Fecha de registro (YYYY-MM-DD): ");
                String dateStr = sc.nextLine();
                regDate = LocalDate.parse(dateStr);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use YYYY-MM-DD");
            }
        }
        try {
            customerUseCase.registerCustomer(typeId, name, identityDoc, email, phone, address, regDate);
            System.out.println("✅ Cliente registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al registrar cliente: " + e.getMessage());
        }
    }
}