package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.report.*;

public class ReportUI {
    public static void manejarMenuReport(Scanner sc, ReportUseCase reportUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            System.out.println("\n******** MENÚ DE REPORTES ********");
            System.out.println("1. Registrar reporte");
            System.out.println("2. Buscar reporte por ID");
            System.out.println("3. Listar todos los reportes");
            System.out.println("4. Actualizar un reporte");
            System.out.println("5. Eliminar un reporte");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    RegistroReport.registrar(sc, reportUseCase);
                    break;
                case 2:
                    BuscarReport.buscar(sc, reportUseCase);
                    break;
                case 3:
                    reportUseCase.getAllReports(); // Método nuevo
                    break;
                case 4:
                    ActualizarReport.actualizar(sc, reportUseCase);
                    break;
                case 5:
                    EliminarReport.eliminar(sc, reportUseCase);
                    break;
                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 6);
    }
}