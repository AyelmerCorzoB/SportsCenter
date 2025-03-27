package com.sportscenter.application.usecase.employee;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;

public class ActualizarEmployee {
    public void actualizar(Scanner sc, EmployeeUseCase employeeUseCase) {
        ConsoleUtils.clear();
        System.out.println("\n=== ACTUALIZAR EMPLEADO ===");

        System.out.print("ID del empleado a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nombre: ");
        String name = sc.nextLine();

        System.out.println("\nSeleccione la posición del empleado:");
        System.out.println("1. ADMINISTRATOR");
        System.out.println("2. CASHIER");
        System.out.println("3. INVENTORY");

        int opcion;
        String position;
        do {
            System.out.print("Opción: ");
            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion < 1 || opcion > 3) {
                System.out.println("❌ Opción inválida. Por favor, seleccione una opción entre 1 y 3.");
            }
        } while (opcion < 1 || opcion > 3);

        position = switch (opcion) {
            case 1 -> "ADMINISTRATOR";
            case 2 -> "CASHIER";
            case 3 -> "INVENTORY";
            default -> throw new IllegalArgumentException("Opción no válida");
        };

        System.out.print("Teléfono: ");
        String phone = sc.nextLine();

        System.out.print("ID de usuario asociado: ");
        ValidationInt.validate(sc);
        int userId = sc.nextInt();
        sc.nextLine();

        employeeUseCase.updateEmployee(id, name, position, phone, userId);
        System.out.println("✅ Empleado actualizado exitosamente.");
    }
}
