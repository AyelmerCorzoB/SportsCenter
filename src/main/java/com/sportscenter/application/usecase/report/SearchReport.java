package com.sportscenter.application.usecase.report;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Report;

public class SearchReport {
    public static void Search(Scanner sc, ReportUseCase reportUseCase) {
        System.out.println("\n=== BUSCAR REPORTE ===");

        System.out.print("ID del reporte: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        Report reporte = reportUseCase.getReportById(id);
        if (reporte != null) {
            System.out.println("\n📋 Información del Reporte:");
            System.out.println("ID: " + reporte.getId());
            System.out.println("Tipo de Reporte ID: " + reporte.getReportTypeId());
            System.out.println("Fecha de Generación: " +
                    (reporte.getGenerationDate() != null ? reporte.getGenerationDate() : "N/A"));
            System.out.println("Usuario ID: " + reporte.getUserId());
            System.out.println("Ruta del Archivo: " + reporte.getFilePath());
            System.out.println("Parámetros: " +
                    (reporte.getParameters() != null ? reporte.getParameters() : "Ninguno"));
            System.out.println("Fecha de Creación: " + reporte.getCreatedAt());
        } else {
            System.out.println("X No se encontró un reporte con ID: " + id);
        }
    }
}
