package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.orderstatus.*;

public class OrderStatusUI {
    public static void manejarMenuOrderStatus(Scanner sc, OrderStatusUseCase orderStatusUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            System.out.println("\n******** MENÚ DE ORDERSTATUS ********");
            System.out.println("1. Registrar orderstatus");
            System.out.println("2. Buscar orderstatus por ID");
            System.out.println("3. Listar todas los orderstatus");
            System.out.println("4. Actualizar un orderstatus");
            System.out.println("5. Eliminar una orderstatus");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegistroOrderStatus().registro(sc, orderStatusUseCase);
                    break;
                case 2:
                    new BuscarOrderStatus().buscar(sc, orderStatusUseCase);
                    break;
                case 3:
                    orderStatusUseCase.getAllOrderStatuses();
                    break;
                case 4:
                    new ActualizarOrderStatus().actualizar(sc, orderStatusUseCase);
                    break;
                case 5:
                    new EliminarOrderStatus().eliminar(sc, orderStatusUseCase);
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
