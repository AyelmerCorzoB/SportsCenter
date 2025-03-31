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
        sc.nextLine(); // limpiar buffer

        Customer customer = customerUseCase.getCustomerById(id);
        if (customer != null) {
            System.out.println("\nInformación del cliente:");
            System.out.println("ID: " + customer.getId());
            System.out.println("Tipo de Cliente ID: " + customer.getCustomerTypeId());
            System.out.println("Nombre: " + customer.getName());
            System.out.println("Documento de Identidad: " + customer.getIdentityDocument());
            System.out.println("Teléfono: " + customer.getPhone());
            System.out.println("Dirección: " + customer.getAddress());
            System.out.println("Fecha de Registro: " + customer.getRegistrationDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        } else {
            System.out.println("X No se encontró el cliente con ID: " + id);
        }
    }
}
