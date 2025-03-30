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
            System.out.println("\n******** MENÚ DE CLIENTES ********");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Search cliente por ID");
            System.out.println("3. Listar todas las órdenes");
            System.out.println("4. Actualizar una cliente");
            System.out.println("5. Eliminar una cliente");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

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