package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.purchasestatus.*;

public class PurchaseStatusUI {
    public static void manejarMenuPurchaseStatus(Scanner sc, PurchaseStatusUseCase purchaseStatusUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            System.out.println("\n******** MENÚ DE PURCHASESTATUS ********");
            System.out.println("1. Registrar purchaseStatus");
            System.out.println("2. Buscar purchaseStatus por ID");
            System.out.println("3. Listar todas los purchaseStatus");
            System.out.println("4. Actualizar un purchaseStatus");
            System.out.println("5. Eliminar una purchaseStatus");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegistroPurchaseStatus().registro(sc, purchaseStatusUseCase);
                    break;
                case 2:
                    new BuscarPurchaseStatus().buscar(sc, purchaseStatusUseCase);
                    break;
                case 3:
                    purchaseStatusUseCase.getAllPurchaseStatuses();
                    break;
                case 4:
                    new ActualizarPurchaseStatus().actualizar(sc, purchaseStatusUseCase);
                    break;
                case 5:
                    new EliminarPurchaseStatus().eliminar(sc, purchaseStatusUseCase);
                    break;
                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 6);
    }
}
