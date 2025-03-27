package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.purchase.*;

public class PurchaseUI {
    public static void manejarMenuPurchase(Scanner sc, PurchaseUseCase purchaseUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            System.out.println("\n******** MENÚ DE PURCHASE ********");
            System.out.println("1. Registrar purchase");
            System.out.println("2. Buscar purchase por ID");
            System.out.println("3. Listar todas los purchase");
            System.out.println("4. Actualizar un purchase");
            System.out.println("5. Eliminar una purchase");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegistrarPurchase().registrar(sc, purchaseUseCase);
                    break;
                case 2:
                    new ObtenerPurchasePorId().obtenerPorId(sc, purchaseUseCase);
                    break;
                case 3:
                    purchaseUseCase.getAllPurchases();
                    break;
                case 4:
                    new ActualizarPurchase().actualizar(sc, purchaseUseCase);
                    break;
                case 5:
                    new EliminarPurchase().eliminar(sc, purchaseUseCase);
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
