package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.purchasestatus.*;

public class PurchaseStatusUI {
    public static void manejarMenuPurchaseStatus(Scanner sc, PurchaseStatusUseCase purchaseStatusUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                        \n╔═════════════════════════════════╗
                        ║       MENÚ PURCHASESTATUS       ║
                        ╠═════════════════════════════════╣
                        ║ 1. Registrar PURCHASESTATUS     ║
                        ║ 2. Buscar PURCHASESTATUS por ID ║
                        ║ 3. Listar todos                 ║
                        ║ 4. Actualizar PURCHASESTATUS    ║
                        ║ 5. Eliminar PURCHASESTATUS      ║
                        ║ 6. Volver                       ║
                        ╚═════════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterPurchaseStatus().Register(sc, purchaseStatusUseCase);
                    break;
                case 2:
                    new SearchPurchaseStatus().Search(sc, purchaseStatusUseCase);
                    break;
                case 3:
                    purchaseStatusUseCase.getAllPurchaseStatuses();
                    break;
                case 4:
                    new UpdatePurchaseStatus().Update(sc, purchaseStatusUseCase);
                    break;
                case 5:
                    new DeletePurchaseStatus().Delete(sc, purchaseStatusUseCase);
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
