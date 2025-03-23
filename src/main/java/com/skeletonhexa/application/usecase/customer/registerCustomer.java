package com.skeletonhexa.application.usecase.customer;

import java.sql.Date;
import java.util.Scanner;


public class registerCustomer {
    public void register(Scanner sc, CustomerUseCase customerUseCase) {
        try {
            System.out.print("Ingrese el nombre del cliente: ");
            String name = sc.nextLine();

            System.out.println("Seleccione el tipo de cliente:");
            System.out.println("1. INDIVIDUAL");
            System.out.println("2. COMPANY");
            System.out.print("Ingrese una opción (1 o 2): ");
            int customerTypeOption = sc.nextInt();
            sc.nextLine();

            String customerType;
            switch (customerTypeOption) {
                case 1:
                    customerType = "INDIVIDUAL";
                    break;
                case 2:
                    customerType = "COMPANY";
                    break;
                default:
                    throw new IllegalArgumentException("Opción no válida. Debe ser 1 o 2.");
            }

            System.out.print("Ingrese el documento de identidad: ");
            String identityDocument = sc.nextLine();

            System.out.print("Ingrese el email: ");
            String email = sc.nextLine();

            System.out.print("Ingrese el teléfono: ");
            String phone = sc.nextLine();

            System.out.print("Ingrese la dirección: ");
            String address = sc.nextLine();

            System.out.print("Ingrese la fecha de registro (YYYY-MM-DD): ");
            String registrationDateStr = sc.nextLine();
            Date registrationDate = Date.valueOf(registrationDateStr);

            customerUseCase.registerCustomer(customerType, name, identityDocument, email, phone, address, registrationDate);
            System.out.println("✅ Cliente registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
}

