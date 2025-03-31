package com.sportscenter.application.usecase.reporttype;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class UpdateReportType {
    public void Update(Scanner sc, ReportTypeUseCase reportTypeUseCase) {
        System.out.println("\n=== ACTUALIZAR TIPO DE REPORTE ===");
        
        System.out.print("ID del tipo a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.println("Opciones: \n1. SALES \n2. INVENTORY \n3. CUSTOMERS");
        System.out.print("Tipo: ");
        ValidationInt.validate(sc);
        String newTypeName = null;
        int newTypeNameOption = sc.nextInt();
        switch (newTypeNameOption) {
            case 1:
                newTypeName = "SALES";
                break;
            case 2:
                newTypeName = "INVENTORY";
                break;
            case 3:
                newTypeName = "CUSTOMERS";
                break;
            default:
                System.out.println("Opcion invalido debe ser 1,2 o 3");
                break;
        }

        System.out.print("Nueva descripción (opcional): ");
        String newDescription = sc.nextLine();
        
        reportTypeUseCase.updateReportType(id, newTypeName, newDescription.isEmpty() ? null : newDescription);
        System.out.println(":D Tipo de reporte actualizado exitosamente.");
    }
}