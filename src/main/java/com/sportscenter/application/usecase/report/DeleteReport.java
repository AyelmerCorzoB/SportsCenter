package com.sportscenter.application.usecase.report;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeleteReport {
    public static void Delete(Scanner sc, ReportUseCase reportUseCase) {
        System.out.println("\n=== ELIMINAR REPORTE ===");

        System.out.print("ID del reporte a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        reportUseCase.deleteReport(id);
        System.out.println(":D Reporte eliminado exitosamente.");
    }
}
