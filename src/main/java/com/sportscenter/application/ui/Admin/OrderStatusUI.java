package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.orderstatus.*;

public class OrderStatusUI {
    public static void manejarMenuOrderStatus(Scanner sc, OrderStatusUseCase orderStatusUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                        \n╔══════════════════════════════════╗
                        ║          MENÚ ORDERSTATUS        ║
                        ╠══════════════════════════════════╣
                        ║ 1. Registrar Estado de orden     ║
                        ║ 2. Buscar Estado de orden por ID ║
                        ║ 3. Listar todos los estados      ║
                        ║ 4. Actualizar Estado de orden    ║
                        ║ 5. Eliminar Estado de orden      ║
                        ║ 6. Salir                         ║
                        ╚══════════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterOrderStatus().Register(sc, orderStatusUseCase);
                    break;
                case 2:
                    new SearchOrderStatus().Search(sc, orderStatusUseCase);
                    break;
                case 3:
                    orderStatusUseCase.getAllOrderStatuses();
                    break;
                case 4:
                    new UpdateOrderStatus().Update(sc, orderStatusUseCase);
                    break;
                case 5:
                    new DeleteOrderStatus().Delete(sc, orderStatusUseCase);
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
