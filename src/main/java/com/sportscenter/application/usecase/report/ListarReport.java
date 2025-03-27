package com.sportscenter.application.usecase.report;

import com.sportscenter.domain.entities.Report;

public class ListarReport {
    public void listar(ReportUseCase reportUseCase) {
        System.out.println("\n=== LISTADO DE REPORTES ===");
        System.out.printf("%-5s %-12s %-10s %-30s %-20s%n",
                "ID", "TipoID", "UsuarioID", "Ruta del archivo", "Parámetros");
        System.out.println("--------------------------------------------------------------------------");

        for (Report r : reportUseCase.getAllReports()) {
            System.out.printf("%-5d %-12d %-10d %-30s %-20s%n",
                    r.getId(),
                    r.getReportTypeId(),
                    r.getUserId(),
                    r.getFilePath(),
                    r.getParameters() != null ? r.getParameters() : "N/A");
        }
    }
}
