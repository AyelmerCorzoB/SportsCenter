package com.sportscenter.application.usecase.customer_type;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationString;

public class RegistroCustomerType {
    public void registro(Scanner sc, CustomerTypeUseCase customerTypeUseCase) {
        System.out.println("\n=== REGISTRO DE TIPO DE CLIENTE ===");
        
        System.out.println("Opciones: INDIVIDUAL, COMPANY");
        System.out.print("Tipo de cliente: ");
        ValidationString.validate(sc);
        String typeName = sc.nextLine().toUpperCase();
        
        System.out.print("Descripción (opcional): ");
        String description = sc.nextLine();
        
        customerTypeUseCase.registerCustomerType(typeName, description.isEmpty() ? null : description);
        System.out.println("✅ Tipo de cliente registrado exitosamente.");
    }
}