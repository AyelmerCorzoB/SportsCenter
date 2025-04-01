package com.sportscenter.application.usecase.customer;

import java.time.LocalDate;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class UpdateCustomer {
    public void Update(Scanner sc, CustomerUseCase customerUseCase) {
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

        System.out.print("Teléfono: ");
        String phone = sc.nextLine();

        System.out.print("Dirección: ");
        String address = sc.nextLine();
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
        customerUseCase.updateCustomer(id, customerTypeId, name, identityDocument, phone, address,orderDate);
        System.out.println(":D Cliente actualizado exitosamente.");
    }
}
