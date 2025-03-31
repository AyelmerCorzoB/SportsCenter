package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.customer.*;

public class CustomerUI {
    public static void mostrarMenu(Scanner sc, CustomerUseCase customerUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                    \n╔══════════════════════════════╗
                    ║          MENÚ CLIENTES       ║
                    ╠══════════════════════════════╣
                    ║ 1. Registrar CLIENTE         ║
                    ║ 2. Buscar Cliente por ID     ║
                    ║ 3. Listar todos los Clientes ║
                    ║ 4. Actualizar Cliente        ║
                    ║ 5. Eliminar Cliente          ║
                    ║ 6. Salir                     ║
                    ╚══════════════════════════════╝
                    Seleccione una opción:""";
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterCustomer().Register(sc, customerUseCase);
                    break;
                case 2:
                    new SearchCustomer().Search(sc, customerUseCase);
                    break;
                case 3:
                    customerUseCase.getAllCustomers();
                    break;
                case 4:
                    new UpdateCustomer().Update(sc, customerUseCase);
                    break;
                case 5:
                    new DeleteCustomer().Delete(sc, customerUseCase);
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
}