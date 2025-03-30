package com.sportscenter.application.usecase.saledetail;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeleteSaledetail {
    public static void Delete(Scanner sc, SaleDetailUseCase saleDetailUseCase) {
        System.out.println("\n=== ELIMINAR DETALLE DE VENTA ===");
        System.out.println("1. Eliminar por ID de detalle");
        System.out.println("2. Eliminar todos los detalles de una venta");
        System.out.print("Seleccione opción: ");
        ValidationInt.validate(sc);
        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                DeletePorDetalleId(sc, saleDetailUseCase);
                break;
            case 2:
                DeletePorVentaId(sc, saleDetailUseCase);
                break;
            default:
                System.out.println("X Opción inválida");
        }
    }

    private static void DeletePorDetalleId(Scanner sc, SaleDetailUseCase saleDetailUseCase) {
        System.out.print("ID del detalle a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        if (saleDetailUseCase.getDetailById(id) == null) {
            System.out.println("X No existe detalle con ese ID");
            return;
        }

        saleDetailUseCase.deleteSaleDetail(id);
        System.out.println("🚀 Detalle eliminado exitosamente");
    }

    private static void DeletePorVentaId(Scanner sc, SaleDetailUseCase saleDetailUseCase) {
        System.out.print("ID de la venta cuyos detalles desea Delete: ");
        ValidationInt.validate(sc);
        int saleId =sc.nextInt();
        sc.nextLine();

        saleDetailUseCase.deleteDetailsBySaleId(saleId);
        System.out.println("🚀 Detalles de venta eliminados exitosamente");
    }
}