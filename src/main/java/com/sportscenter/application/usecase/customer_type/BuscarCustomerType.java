package com.sportscenter.application.usecase.customer_type;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.CustomerType;

public class BuscarCustomerType {
    public void buscar(Scanner sc, CustomerTypeUseCase customerTypeUseCase) {
        System.out.println("\n=== BUSCAR TIPO DE CLIENTE ===");
        
        System.out.print("ID del tipo de cliente: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        CustomerType type = customerTypeUseCase.getCustomerTypeById(id);
        if(type != null) {
            System.out.println("\nInformación del tipo:");
            System.out.println("ID: " + type.getId());
            System.out.println("Tipo: " + type.getTypeName());
            System.out.println("Descripción: " + (type.getDescription() != null ? type.getDescription() : "N/A"));
        } else {
            System.out.println("X No se encontró el tipo con ID: " + id);
        }
    }
}