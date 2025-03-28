package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.employee.*;

public class EmployeeUI {
    public static void manejarMenuEmployee(Scanner sc, EmployeeUseCase employeeUseCase) {
        int opcion;
        ConsoleUtils.clear();
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
                    listarTodosEmpleados(employeeUseCase);
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
                    System.out.println("X Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 6);
    }
    private static void listarTodosEmpleados(EmployeeUseCase employeeUseCase) {
        ConsoleUtils.clear(); // Limpiar la consola antes de mostrar la lista
        System.out.println("\n--- LISTA DE EMPLEADOS ---");
    
        // Obtener la lista de empleados desde el caso de uso
        var employees = employeeUseCase.getAllEmployees();
    
        if (employees.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
    
        // Mostrar los empleados en formato tabular
        System.out.printf("%-5s %-20s %-15s %-15s %-30s %-10s%n",
                "ID", "Nombre", "Posición", "Teléfono", "Email", "Usuario ID");
    
        for (var employee : employees) {
            System.out.printf("%-5d %-20s %-15s %-15s %-30s %-10d%n",
                    employee.getId(),
                    employee.getName(),
                    employee.getPosition(),
                    employee.getPhone(),
                    employee.getUserId());
        }
    }
    public void mostrarMenu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarMenu'");
    }
}
