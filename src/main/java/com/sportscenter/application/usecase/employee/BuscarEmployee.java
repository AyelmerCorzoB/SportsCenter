package com.sportscenter.application.usecase.employee;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Employee;

public class BuscarEmployee {
    public void buscar(Scanner sc, EmployeeUseCase employeeUseCase) {
        System.out.println("\n=== BUSCAR EMPLEADO ===");

        System.out.print("ID del empleado: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        Employee employee = employeeUseCase.getEmployeeById(id);
        if (employee != null) {
            System.out.println("\nInformación del empleado:");
            System.out.println("ID: " + employee.getId());
            System.out.println("Nombre: " + employee.getName());
            System.out.println("Posición: " + employee.getPosition());
            System.out.println("Teléfono: " + employee.getPhone());
            System.out.println("ID de Usuario: " + employee.getUserId());
        } else {
            System.out.println("X No se encontró el empleado con ID: " + id);
        }
    }
}
