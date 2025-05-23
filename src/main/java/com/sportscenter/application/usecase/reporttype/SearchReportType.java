package com.sportscenter.application.usecase.reporttype;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.ReportType;

public class SearchReportType {
    public void Search(Scanner sc, ReportTypeUseCase reportTypeUseCase) {
        System.out.println("\n=== BUSCAR TIPO DE REPORTE ===");
        
        System.out.print("ID del tipo: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        ReportType type = reportTypeUseCase.getReportTypeById(id);
        if(type != null) {
            System.out.println("\nInformación del tipo:");
            System.out.println("ID: " + type.getId());
            System.out.println("Tipo: " + type.getTypeName());
            System.out.println("Descripción: " + (type.getDescription() != null ? type.getDescription() : "N/A"));
        } else {
            System.out.println("X No se encontró el tipo con ID: " + id);
        }
    }
}