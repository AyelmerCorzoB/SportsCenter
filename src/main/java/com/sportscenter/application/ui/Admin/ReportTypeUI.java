package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.reporttype.*;

public class ReportTypeUI {
    public static void manejarMenuReporttype(Scanner sc, ReportTypeUseCase reportTypeUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                        \n╔═════════════════════════════════════╗
                        ║        MENÚ TIPOS DE REPORTES       ║
                        ╠═════════════════════════════════════╣
                        ║ 1. Registrar Tipo de reporte        ║
                        ║ 2. Buscar tipo de reporte por ID    ║
                        ║ 3. Listar todos los tipo de reporte ║
                        ║ 4. Actualizar tipo de reporte       ║
                        ║ 5. Eliminar tipo de reporte         ║
                        ║ 6. Volver                           ║
                        ╚═════════════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterReportType().Register(sc, reportTypeUseCase);
                    break;
                case 2:
                    new SearchReportType().Search(sc, reportTypeUseCase);
                    break;
                case 3:
                    reportTypeUseCase.getAllReportTypes();
                    break;
                case 4:
                    new UpdateReportType().Update(sc, reportTypeUseCase);
                    break;
                case 5:
                    new DeleteReportType().Delete(sc, reportTypeUseCase);
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
