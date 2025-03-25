package com.sportscenter.application.usecase.report;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.reporttype.ReportTypeUseCase;
import com.sportscenter.domain.entities.ReportType;

public class BuscarReport {
    public void buscar(Scanner sc, ReportTypeUseCase reportTypeUseCase) {
        System.out.println("\n=== BUSCAR TIPO DE REPORTE ===");
        
        System.out.print("ID del tipo: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        ReportType type = reportTypeUseCase.getReportTypeById(id);
        if (type != null) {
            System.out.println("\nInformación del tipo:");
            System.out.println("ID: " + type.getId());
            System.out.println("Tipo: " + type.getTypeName());
            System.out.println("Descripción: " + (type.getDescription() != null ? type.getDescription() : "N/A"));
        } else {
            System.out.println("❌ No se encontró el tipo con ID: " + id);
        }
    }
}
