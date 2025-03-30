package com.sportscenter.application.usecase.employee;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;

public class DeleteEmployee {
    public void Delete(Scanner sc, EmployeeUseCase employeeUseCase) {
        System.out.println("\n=== ELIMINAR EMPLEADO ===");

        System.out.print("ID del empleado a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        employeeUseCase.deleteEmployee(id);
        System.out.println("🚀 Empleado eliminado exitosamente.");
    }
}
