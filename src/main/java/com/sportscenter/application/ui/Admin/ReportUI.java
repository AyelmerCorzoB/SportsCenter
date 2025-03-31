package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.report.*;

public class ReportUI {
    public static void manejarMenuReport(Scanner sc, ReportUseCase reportUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                        \n╔══════════════════════════════╗
                        ║        MENÚ REPORTES         ║
                        ╠══════════════════════════════╣
                        ║ 1. Registrar REPORTE         ║
                        ║ 2. Buscar reporte por ID     ║
                        ║ 3. Listar todos los repottes ║
                        ║ 4. Actualizar reporte        ║
                        ║ 5. Eliminar reporte          ║
                        ║ 6. Salir                     ║
                        ╚══════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    RegisterReport.Register(sc, reportUseCase);
                    break;
                case 2:
                    SearchReport.Search(sc, reportUseCase);
                    break;
                case 3:
                    reportUseCase.getAllReports(); // Método nuevo
                    break;
                case 4:
                    UpdateReport.Update(sc, reportUseCase);
                    break;
                case 5:
                    DeleteReport.Delete(sc, reportUseCase);
                    break;
                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("X Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 6);
    }
}