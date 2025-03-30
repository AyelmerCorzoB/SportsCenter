package com.sportscenter.application.usecase.reporttype;

public class ListReportTypes {
    public void List(ReportTypeUseCase reportTypeUseCase) {
        System.out.println("\n=== LISTADO DE TIPOS DE REPORTE ===");
        System.out.printf("%-5s %-15s %-30s%n", "ID", "TIPO", "DESCRIPCIÓN");
        System.out.println("--------------------------------------------------");

        reportTypeUseCase.getAllReportTypes().forEach(t -> System.out.printf("%-5d %-15s %-30s%n",
                t.getId(),
                t.getTypeName(),
                t.getDescription() != null ? t.getDescription() : "N/A"));
    }
}