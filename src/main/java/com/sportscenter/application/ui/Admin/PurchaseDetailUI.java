package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.purchase_detail.*;

public class PurchaseDetailUI {

    public static void manejarMenuPurchasedetail(Scanner sc, PurchaseDetailUseCase purchaseDetailUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                    \n╔════════════════════════════════════╗
                    ║       MENÚ DETALLES DE COMPRA      ║
                    ╠════════════════════════════════════╣
                    ║ 1. Registrar Detalle de compra     ║
                    ║ 2. Buscar detalle de compra por ID ║
                    ║ 3. Listar todos los detalles       ║
                    ║ 4. Actualizar detalle de compra    ║
                    ║ 5. Eliminar detalle de compra      ║
                    ║ 6. Volver                          ║
                    ╚════════════════════════════════════╝
                    Seleccione una opción:""";
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    RegisterPurchasedetail.Register(sc, purchaseDetailUseCase);
                    break;
                case 2:
                    SearchPurchaseDetail.obtenerPorId(sc, purchaseDetailUseCase);
                    break;
                case 3:
                    ListTodosLosDetalles(purchaseDetailUseCase);
                    break;
                case 4:
                    UpdatePurchaseDetail.Update(sc, purchaseDetailUseCase);
                    break;
                case 5:
                    DeletePurchaseDetail.Delete(sc, purchaseDetailUseCase);
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

    private static void ListTodosLosDetalles(PurchaseDetailUseCase purchaseDetailUseCase) {
        System.out.println("\n--- LISTA DE DETALLES DE COMPRA ---");
        // Aquí deberías implementar la lógica para mostrar los detalles
        // Por ejemplo:
        // List<PurchaseDetail> detalles =
        // purchaseDetailUseCase.getAllPurchaseDetails();
        // detalles.forEach(System.out::println);
    }
}