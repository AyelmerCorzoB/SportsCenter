package com.sportscenter.application.usecase.report;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Report;

public class ActualizarReport {
    public static void actualizar(Scanner sc, ReportUseCase reportUseCase) {
        System.out.println("\n=== ACTUALIZAR REPORTE ===");

        // Obtener ID del reporte a actualizar
        System.out.print("ID del reporte a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        // Mostrar opciones de tipo de reporte
        System.out.println("Opciones de tipo: \n1. SALES \n2. INVENTORY \n3. CUSTOMERS");
        System.out.print("Nuevo tipo (opcional, dejar vacío para no cambiar): ");
        String typeInput = sc.nextLine();
        
        String newType = null;
        if (!typeInput.isEmpty()) {
            int typeOption = Integer.parseInt(typeInput);
            switch (typeOption) {
                case 1:
                    newType = "SALES";
                    break;
                case 2:
                    newType = "INVENTORY";
                    break;
                case 3:
                    newType = "CUSTOMERS";
                    break;
                default:
                    System.out.println("❌ Opción inválida. Debe ser 1, 2 o 3.");
                    return;
            }
        }

        // Solicitar nueva ruta de archivo
        System.out.print("Nueva ruta del archivo (opcional): ");
        String newFilePath = sc.nextLine();

        // Solicitar nuevos parámetros
        System.out.print("Nuevos parámetros (opcional): ");
        String newParameters = sc.nextLine();

        try {
            // Obtener el reporte existente
            Report report = reportUseCase.getReportById(id);
            if (report == null) {
                System.out.println("❌ No se encontró un reporte con ese ID.");
                return;
            }

            // Actualizar solo los campos proporcionados
            if (newType != null) {
                report.setReportTypeId(getTypeIdFromName(newType));
            }
            if (!newFilePath.isEmpty()) {
                report.setFilePath(newFilePath);
            }
            if (!newParameters.isEmpty()) {
                report.setParameters(newParameters);
            }

            // Guardar cambios
            reportUseCase.updateReport(report);
            System.out.println("✅ Reporte actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar el reporte: " + e.getMessage());
        }
    }

    private static int getTypeIdFromName(String typeName) {
        switch (typeName) {
            case "SALES": return 1;
            case "INVENTORY": return 2;
            case "CUSTOMERS": return 3;
            default: return 0;
        }
    }
}