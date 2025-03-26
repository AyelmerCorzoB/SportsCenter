package com.sportscenter.application.usecase.employee;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;

public class ActualizarEmployee {
    public void actualizar(Scanner sc, EmployeeUseCase employeeUseCase) {
        System.out.println("\n=== ACTUALIZAR EMPLEADO ===");

        System.out.print("ID del empleado a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre: ");
        String name = sc.nextLine();

        System.out.print("Posición: ");
        String position = sc.nextLine();

        System.out.print("Teléfono: ");
        String phone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("ID de usuario asociado: ");
        ValidationInt.validate(sc);
        int userId = sc.nextInt();
        sc.nextLine();

        employeeUseCase.updateEmployee(id, name, position, phone, email, userId);
        System.out.println("✅ Empleado actualizado exitosamente.");
    }
}
