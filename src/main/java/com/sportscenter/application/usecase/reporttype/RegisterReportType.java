package com.sportscenter.application.usecase.reporttype;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class RegisterReportType {
    public void Register(Scanner sc, ReportTypeUseCase reportTypeUseCase) {
        System.out.println("\n=== REGISTRO DE TIPO DE REPORTE ===");
        
        System.out.println("Opciones: \n1. SALES \n2. INVENTORY \n3. CUSTOMERS");
        System.out.print("Tipo: ");
        ValidationInt.validate(sc);
        String typeName = null;
        int TypeNameOption = sc.nextInt();
        switch (TypeNameOption) {
            case 1:
                typeName = "SALES";
                break;
            case 2:
                typeName = "INVENTORY";
                break;
            case 3:
                typeName = "CUSTOMERS";
                break;
            default:
                System.out.println("Opcion invalido debe ser 1,2 o 3");
                break;
        }

        System.out.print("Descripción (opcional): ");
        String description = sc.nextLine();
        
        reportTypeUseCase.registerReportType(typeName, description.isEmpty() ? null : description);
        System.out.println("🚀 Tipo de reporte registrado exitosamente.");
    }
}