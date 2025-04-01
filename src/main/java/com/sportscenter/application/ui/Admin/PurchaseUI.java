package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.purchase.*;

public class PurchaseUI {
    public static void manejarMenuPurchase(Scanner sc, PurchaseUseCase purchaseUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                        \n╔══════════════════════════════╗
                        ║        MENÚ Purchase         ║
                        ╠══════════════════════════════╣
                        ║ 1. Registrar Purchase        ║
                        ║ 2. Buscar Purchase por ID    ║
                        ║ 3. Listar todos              ║
                        ║ 4. Actualizar Purchase       ║
                        ║ 5. Eliminar Purchase         ║
                        ║ 6. Volver                    ║
                        ╚══════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterPurchase().Register(sc, purchaseUseCase);
                    break;
                case 2:
                    new ObtenerPurchasePorId().obtenerPorId(sc, purchaseUseCase);
                    break;
                case 3:
                    purchaseUseCase.getAllPurchases();
                    break;
                case 4:
                    new UpdatePurchase().Update(sc, purchaseUseCase);
                    break;
                case 5:
                    new DeletePurchase().Delete(sc, purchaseUseCase);
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
