package com.sportscenter.application.usecase.report;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.reporttype.ReportTypeUseCase;

public class RegistroReport {
    public void registro(Scanner sc, ReportTypeUseCase reportTypeUseCase) {
        System.out.println("\n=== REGISTRO DE TIPO DE REPORTE ===");
        
        System.out.println("Opciones: \n1. SALES \n2. INVENTORY \n3. CUSTOMERS");
        System.out.print("Seleccione el tipo: ");
        ValidationInt.validate(sc);
        int option = sc.nextInt();
        sc.nextLine(); // Limpia el buffer
        
        String typeName = null;
        switch (option) {
            case 1 -> typeName = "SALES";
            case 2 -> typeName = "INVENTORY";
            case 3 -> typeName = "CUSTOMERS";
            default -> {
                System.out.println("❌ Opción inválida. Debe ser 1, 2 o 3.");
                return; // Salir sin registrar si la opción es incorrecta
            }
        }

        System.out.print("Descripción (opcional): ");
        String description = sc.nextLine();

        reportTypeUseCase.registerReportType(typeName, description.isEmpty() ? null : description);
        System.out.println("✅ Tipo de reporte registrado exitosamente.");
    }
}
