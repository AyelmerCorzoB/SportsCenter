package com.sportscenter.application.ui.Admin;

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
            String menu = """
                        \n╔═══════════════════════════════════╗
                        ║        MENÚ DETALLES DE VENTA     ║
                        ╠═══════════════════════════════════╣
                        ║ 1. Registrar DETALLES DE VENTA    ║
                        ║ 2. Buscar detalle de venta por ID ║
                        ║ 3. Listar todos                   ║
                        ║ 4. Actualizar detalle de venta    ║
                        ║ 5. Eliminar detalle de venta      ║
                        ║ 6. Volver                         ║
                        ╚═══════════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    RegisterSaledetail.Register(sc, saleDetailUseCase);
                    break;
                case 2:
                    SearchSaleDetail.SearchPorVenta(sc, saleDetailUseCase);
                    break;
                case 3:
                    ListTodosDetalles(saleDetailUseCase);
                    break;
                case 4:
                    UpdateSaleDetail.Update(sc, saleDetailUseCase);
                    break;
                case 5:
                    DeleteSaledetail.Delete(sc, saleDetailUseCase);
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

    private static void ListTodosDetalles(SaleDetailUseCase saleDetailUseCase) {
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