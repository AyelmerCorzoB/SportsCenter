package com.sportscenter.application.usecase.employee;

import com.sportscenter.domain.entities.Employee;

public class ListEmployee {
    public void List(EmployeeUseCase employeeUseCase) {
        System.out.println("\n=== LISTADO DE EMPLEADOS ===");
        System.out.printf("%-5s %-20s %-20s %-15s %-25s %-10s%n", 
            "ID", "Nombre", "Posición", "Teléfono", "Email", "UsuarioID");
        System.out.println("------------------------------------------------------------------------------------------");

        for (Employee employee : employeeUseCase.getAllEmployees()) {
            System.out.printf("%-5d %-20s %-20s %-15s %-25s %-10d%n",
                employee.getId(),
                employee.getName(),
                employee.getPosition(),
                employee.getPhone(),
                employee.getUserId()
            );
        }
    }
}
