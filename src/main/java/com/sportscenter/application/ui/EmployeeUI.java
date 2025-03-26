package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.employee.ActualizarEmployee;
import com.sportscenter.application.usecase.employee.BuscarEmployee;
import com.sportscenter.application.usecase.employee.EmployeeUseCase;
import com.sportscenter.application.usecase.employee.EliminarEmployee;
import com.sportscenter.application.usecase.employee.RegistroEmployee;

public class EmployeeUI {
    public static void manejarMenuEmployee(Scanner sc, EmployeeUseCase employeeUseCase) {
        int opcion;
        do {
            System.out.println("\n******** MENÚ DE EMPLEADOS ********");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Buscar empleado por ID");
            System.out.println("3. Listar todas los empleados");
            System.out.println("4. Actualizar un empleado");
            System.out.println("5. Eliminar una empleado");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegistroEmployee().registro(sc, employeeUseCase);
                    break;
                case 2:
                    new BuscarEmployee().buscar(sc, employeeUseCase);
                    break;
                case 3:
                    employeeUseCase.getAllEmployees();
                    break;
                case 4:
                    new ActualizarEmployee().actualizar(sc, employeeUseCase);
                    break;
                case 5:
                    new EliminarEmployee().eliminar(sc, employeeUseCase);
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
