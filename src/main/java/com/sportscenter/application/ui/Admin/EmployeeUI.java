package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.employee.*;

public class EmployeeUI {
    public static void mostrarMenu(Scanner sc, EmployeeUseCase employeeUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                        \n╔═══════════════════════════════╗
                        ║        MENÚ EMPLEADOS         ║
                        ╠═══════════════════════════════╣
                        ║ 1. Registrar Empleado         ║
                        ║ 2. Buscar Empleado por ID     ║
                        ║ 3. Listar todos los empleados ║
                        ║ 4. Actualizar Empleado        ║
                        ║ 5. Eliminar Empleado          ║
                        ║ 6. Salir                      ║
                        ╚═══════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterEmployee().Register(sc, employeeUseCase);
                    break;
                case 2:
                    new SearchEmployee().Search(sc, employeeUseCase);
                    break;
                case 3:
                    ListAllEmployees(employeeUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 4:
                    new UpdateEmployee().Update(sc, employeeUseCase);
                    break;
                case 5:
                    new DeleteEmployee().Delete(sc, employeeUseCase);
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
    private static void ListAllEmployees(EmployeeUseCase employeeUseCase) {
        ConsoleUtils.clear();
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                          LISTA DE EMPLEADOS                                ║");
        System.out.println("╠═════╦══════════════════════╦════════════════╦══════════════╦═══════════════╣");
        System.out.printf("║ %-3s ║ %-20s ║ %-14s ║ %-12s ║ %-13s ║%n",
                "ID", "Nombre", "Posición", "Teléfono", "Usuario ID");
        System.out.println("╠═════╬══════════════════════╬════════════════╬══════════════╬═══════════════╣");
    
        var employees = employeeUseCase.getAllEmployees();
    
        if (employees.isEmpty()) {
            System.out.println("║                          No hay empleados registrados.                        ║");
        } else {
            for (var employee : employees) {
                System.out.printf("║ %-3d ║ %-20s ║ %-14s ║ %-12s ║ %-13d ║%n",
                        employee.getId(),
                        employee.getName(),
                        employee.getPosition(),
                        employee.getPhone(),
                        employee.getUserId());
            }
        }
    
        System.out.println("╚═════╩══════════════════════╩════════════════╩══════════════╩═══════════════╝");
    }
    
}
