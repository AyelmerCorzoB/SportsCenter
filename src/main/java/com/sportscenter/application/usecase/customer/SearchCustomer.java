package com.sportscenter.application.usecase.customer;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Customer;

public class SearchCustomer {
    public void Search(Scanner sc, CustomerUseCase customerUseCase) {
        System.out.println("\n=== BUSCAR CLIENTE ===");

        System.out.print("ID del cliente: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        Customer customer = customerUseCase.getCustomerById(id);
        if (customer != null) {
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║          INFORMACIÓN DEL CLIENTE         ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println(String.format("║ %-15s: %-23s ║", "ID", customer.getId()));
            System.out.println(String.format("║ %-15s: %-23s ║", "Tipo",
                    customer.getCustomerTypeId() == 1 ? "INDIVIDUAL"
                            : customer.getCustomerTypeId() == 2 ? "COMPANY" : "DESCONOCIDO"));
            System.out.println(String.format("║ %-15s: %-23s ║", "Nombre", truncate(customer.getName(), 23)));
            System.out.println(
                    String.format("║ %-15s: %-23s ║", "Documento", truncate(customer.getIdentityDocument(), 23)));
            System.out.println(String.format("║ %-15s: %-23s ║", "Teléfono", customer.getPhone()));
            System.out.println(String.format("║ %-15s: %-23s ║", "Dirección", truncate(customer.getAddress(), 23)));
            System.out.println(String.format("║ %-15s: %-23s ║", "Fecha Registro",
                    customer.getRegistrationDate() != null
                            ? customer.getRegistrationDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
                            : "N/A"));
            System.out.println("╚══════════════════════════════════════════╝");
        } else {
            System.out.println("X No se encontró el cliente con ID: " + id);
        }
    }
    private static String truncate(String value, int length) {
        if (value == null)
            return "N/A";
        return value.length() > length ? value.substring(0, length - 3) + "..." : value;
    }
}
