package com.sportscenter.application.usecase.customer;

import java.time.LocalDate;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationString;

public class RegisterCustomer {
    public void Register(Scanner sc, CustomerUseCase customerUseCase) {
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

        System.out.print("Teléfono: ");
        String phone = ValidationString.validate(sc);

        System.out.print("Dirección: ");
        String address = ValidationString.validate(sc);

        LocalDate orderDate;
        System.out.print("¿Desea ingresar una fecha personalizada? (S/N): ");
        String opcionFecha = sc.nextLine().trim().toUpperCase();

        if (opcionFecha.equals("S")) {
            while (true) {
                try {
                    System.out.print("Ingrese fecha (YYYY-MM-DD): ");
                    String orderDateStr = sc.nextLine();
                    orderDate = LocalDate.parse(orderDateStr);
                    if (orderDate.isAfter(LocalDate.now())) {
                        System.out.println("La fecha no puede ser futura");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Formato de fecha inválido. Use YYYY-MM-DD");
                }
            }
        } else {
            orderDate = LocalDate.now();
            System.out.println("Se asignará la fecha actual: " + orderDate);
        }
        try {
            customerUseCase.registerCustomer(typeId, name, identityDoc, phone, address, orderDate);
            System.out.println(":D Cliente registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al registrar cliente: " + e.getMessage());
        }
    }
}