package com.skeletonhexa.application.usecase.customer;

import java.sql.Date;
import java.util.Scanner;


public class UpdateCustomer {
    public void update(Scanner sc, CustomerUseCase customerUseCase) {

        try {
            System.out.print("Ingrese el nuevo nombre del cliente: ");
            String newName = sc.nextLine();

            System.out.println("Seleccione el nuevo tipo de cliente:");
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

            System.out.print("Ingrese el nuevo documento de identidad: ");
            String identityDocument = sc.nextLine();

            System.out.print("Ingrese el nuevo email: ");
            String email = sc.nextLine();

            System.out.print("Ingrese el nuevo teléfono: ");
            String phone = sc.nextLine();

            System.out.print("Ingrese la nueva dirección: ");
            String address = sc.nextLine();

            System.out.print("Ingrese la fecha de actualizacion (YYYY-MM-DD): ");
            String registrationDateStr = sc.nextLine();
            Date registrationDate = Date.valueOf(registrationDateStr);

            customerUseCase.registerCustomer(customerType, newName, identityDocument, email, phone, address, registrationDate);
            System.out.println("✅ Cliento actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
}