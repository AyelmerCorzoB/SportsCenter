package com.sportscenter.application.usecase.reporttype;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeleteReportType {
    public void Delete(Scanner sc, ReportTypeUseCase reportTypeUseCase) {
        System.out.println("\n=== ELIMINAR TIPO DE REPORTE ===");
        
        System.out.print("ID del tipo a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        reportTypeUseCase.deleteReportType(id);
        System.out.println("🚀 Tipo de reporte eliminado exitosamente.");
    }
}