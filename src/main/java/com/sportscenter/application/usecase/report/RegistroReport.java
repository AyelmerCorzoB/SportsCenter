package com.sportscenter.application.usecase.report;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;

public class RegistroReport {
    public static void registrar(Scanner sc, ReportUseCase reportUseCase) {
        System.out.println("\n=== REGISTRO DE REPORTE ===");

        // Selección del tipo de reporte
        System.out.println("Tipos disponibles: \n1. SALES \n2. INVENTORY \n3. CUSTOMERS");
        System.out.print("Seleccione el tipo: ");
        ValidationInt.validate(sc);
        int tipo = sc.nextInt();
        sc.nextLine();

        if (tipo < 1 || tipo > 3) {
            System.out.println("❌ Tipo de reporte inválido");
            return;
        }

        // ID del usuario que genera el reporte
        System.out.print("ID del usuario que genera el reporte: ");
        ValidationInt.validate(sc);
        int userId = sc.nextInt();
        sc.nextLine();

        // Ruta donde se guardará el reporte
        System.out.print("Ruta del archivo (ej: /reportes/ventas_2023.pdf): ");
        String filePath = sc.nextLine();

        // Parámetros adicionales
        System.out.print("Parámetros adicionales (opcional): ");
        String parameters = sc.nextLine();

        try {
            reportUseCase.generateReport(tipo, userId, filePath,
                    parameters.isEmpty() ? null : parameters);
            System.out.println("✅ Reporte registrado exitosamente.");
            System.out.println("Ubicación: " + filePath);
        } catch (Exception e) {
            System.out.println("❌ Error al registrar el reporte: " + e.getMessage());
        }
    }
}