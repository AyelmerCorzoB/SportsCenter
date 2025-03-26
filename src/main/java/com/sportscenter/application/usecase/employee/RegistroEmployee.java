package com.sportscenter.application.usecase.employee;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;

public class RegistroEmployee {

    public void registro(Scanner sc, EmployeeUseCase employeeUseCase) {
        System.out.println("\n=== REGISTRO DE EMPLEADO ===");

        System.out.print("Nombre: ");
        String name = sc.nextLine();

        System.out.print("Cargo/Posición: ");
        String position = sc.nextLine();

        System.out.print("Teléfono: ");
        String phone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("ID del Usuario Asociado: ");
        ValidationInt.validate(sc);
        int userId = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        employeeUseCase.registerEmployee(name, position, phone, email, userId);
        System.out.println("✅ Empleado registrado exitosamente.");
    }
}
