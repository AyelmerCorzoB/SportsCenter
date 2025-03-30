package com.sportscenter.application.usecase.customer_type;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;


public class UpdateCustomerType {
    public void Update(Scanner sc, CustomerTypeUseCase customerTypeUseCase) {
        System.out.println("\n=== ACTUALIZAR TIPO DE CLIENTE ===");
        
        System.out.print("ID del tipo a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Opciones: \n1. INDIVIDUAL\n 2.COMPANY");
        System.out.print("Nuevo tipo: ");
        ValidationInt.validate(sc);
        int customerTypeOption = sc.nextInt();
        String newTypeName = null;
        switch (customerTypeOption) {
            case 1:
                newTypeName = "INDIVIDUAL";
                break;
            case 2:
                newTypeName = "COMPANY";
                break;
            default:
                break;
        }
        
        System.out.print("Nueva descripción (opcional): ");
        String newDescription = sc.nextLine();
        
        customerTypeUseCase.updateCustomerType(id, newTypeName, newDescription.isEmpty() ? null : newDescription);
        System.out.println("🚀 Tipo de cliente actualizado exitosamente.");
    }
}