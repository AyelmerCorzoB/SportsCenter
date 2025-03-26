package com.sportscenter.application.usecase.report;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.reporttype.ReportTypeUseCase;

public class ActualizarReport {
    public void actualizar(Scanner sc, ReportTypeUseCase reportTypeUseCase) {
        System.out.println("\n=== ACTUALIZAR TIPO DE REPORTE ===");

        System.out.print("ID del tipo a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Opciones: \n1. SALES \n2. INVENTORY \n3. CUSTOMERS");
        System.out.print("Nuevo tipo: ");
        ValidationInt.validate(sc);
        String newTypeName = null;
        int newTypeNameOption = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer después de nextInt()

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
                System.out.println("❌ Opción inválida. Debe ser 1, 2 o 3.");
                return;
        }

        System.out.print("Nueva descripción (opcional): ");
        String newDescription = sc.nextLine();

        reportTypeUseCase.updateReportType(id, newTypeName, newDescription.isEmpty() ? null : newDescription);
        System.out.println("✅ Tipo de reporte actualizado exitosamente.");
    }
}
