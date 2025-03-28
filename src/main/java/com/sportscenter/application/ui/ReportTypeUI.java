package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.reporttype.*;

public class ReportTypeUI {
    public static void manejarMenuReporttype(Scanner sc, ReportTypeUseCase reportTypeUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            System.out.println("\n******** MENÚ DE TIPOS DE REPORTES ********");
            System.out.println("1. Registrar tipo de reporte");
            System.out.println("2. Buscar tipo de reporte por ID");
            System.out.println("3. Listar todas los tipo de reporte");
            System.out.println("4. Actualizar un tipo de reporte");
            System.out.println("5. Eliminar una tipo de reporte");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegistroReportType().registro(sc, reportTypeUseCase);
                    break;
                case 2:
                    new BuscarReportType().buscar(sc, reportTypeUseCase);
                    break;
                case 3:
                    reportTypeUseCase.getAllReportTypes();
                    break;
                case 4:
                    new ActualizarReportType().actualizar(sc, reportTypeUseCase);
                    break;
                case 5:
                    new EliminarReportType().eliminar(sc, reportTypeUseCase);
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
