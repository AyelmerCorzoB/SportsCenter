package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.purchase_detail.*;

public class PurchaseDetailUI {

    public static void manejarMenuPurchasedetail(Scanner sc, PurchaseDetailUseCase purchaseDetailUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            System.out.println("\n******** MENÚ DE DETALLES DE COMPRA ********");
            System.out.println("1. Registrar detalle de compra");
            System.out.println("2. Buscar detalle de compra por ID");
            System.out.println("3. Listar todos los detalles de compra");
            System.out.println("4. Actualizar un detalle de compra");
            System.out.println("5. Eliminar un detalle de compra");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    RegistrarPurchasedetail.registrar(sc, purchaseDetailUseCase);
                    break;
                case 2:
                    BuscarPurchaseDetail.obtenerPorId(sc, purchaseDetailUseCase);
                    break;
                case 3:
                    listarTodosLosDetalles(purchaseDetailUseCase);
                    break;
                case 4:
                    ActualizarPurchaseDetail.actualizar(sc, purchaseDetailUseCase);
                    break;
                case 5:
                    EliminarPurchaseDetail.eliminar(sc, purchaseDetailUseCase);
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

    private static void listarTodosLosDetalles(PurchaseDetailUseCase purchaseDetailUseCase) {
        System.out.println("\n--- LISTA DE DETALLES DE COMPRA ---");
        // Aquí deberías implementar la lógica para mostrar los detalles
        // Por ejemplo:
        // List<PurchaseDetail> detalles = purchaseDetailUseCase.getAllPurchaseDetails();
        // detalles.forEach(System.out::println);
    }
}