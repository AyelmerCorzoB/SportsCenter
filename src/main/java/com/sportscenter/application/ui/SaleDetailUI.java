package com.sportscenter.application.ui;

import java.util.List;
import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.saledetail.*;
import com.sportscenter.domain.entities.SaleDetail;

public class SaleDetailUI {
    public static void manejarMenuSaleDetail(Scanner sc, SaleDetailUseCase saleDetailUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            System.out.println("\n******** MENÚ DE DETALLES DE VENTA ********");
            System.out.println("1. Registrar detalle de venta");
            System.out.println("2. Buscar detalles por ID de venta");
            System.out.println("3. Listar todos los detalles de venta");
            System.out.println("4. Actualizar un detalle de venta");
            System.out.println("5. Eliminar detalles de venta");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    RegistroSaledetail.registro(sc, saleDetailUseCase);
                    break;
                case 2:
                    BuscarSaleDetail.buscarPorVenta(sc, saleDetailUseCase);
                    break;
                case 3:
                    listarTodosDetalles(saleDetailUseCase);
                    break;
                case 4:
                    ActualizarSaleDetail.actualizar(sc, saleDetailUseCase);
                    break;
                case 5:
                    EliminarSaledetail.eliminar(sc, saleDetailUseCase);
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

    private static void listarTodosDetalles(SaleDetailUseCase saleDetailUseCase) {
        System.out.println("\n--- LISTA DE DETALLES DE VENTA ---");
        List<SaleDetail> detalles = saleDetailUseCase.getAllDetails();

        if (detalles.isEmpty()) {
            System.out.println("No hay detalles de venta registrados");
            return;
        }

        System.out.printf("%-5s %-10s %-10s %-8s %-10s %-10s%n",
                "ID", "Venta ID", "Producto", "Cantidad", "P. Unitario", "Subtotal");

        for (SaleDetail detalle : detalles) {
            System.out.printf("%-5d %-10d %-10d %-8d %-10.2f %-10.2f%n",
                    detalle.getId(),
                    detalle.getSaleId(),
                    detalle.getProductId(),
                    detalle.getQuantity(),
                    detalle.getUnitPrice(),
                    detalle.getSubtotal());
        }
    }
}