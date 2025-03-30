package com.sportscenter.application.usecase.saledetail;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.SaleDetail;

public class UpdateSaleDetail {
    public static void Update(Scanner sc, SaleDetailUseCase saleDetailUseCase) {
        System.out.println("\n=== ACTUALIZAR DETALLE DE VENTA ===");

        System.out.print("ID del detalle a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        SaleDetail detalle = saleDetailUseCase.getDetailById(id);
        if (detalle == null) {
            System.out.println("X No se encontró el detalle con ID: " + id);
            return;
        }

        System.out.println("\nDetalle actual:");
        mostrarDetalle(detalle);

        System.out.print("\nNueva cantidad (" + detalle.getQuantity() + "): ");
        ValidationInt.validate(sc);
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo precio unitario (" + detalle.getUnitPrice() + "): ");
        double unitPrice = sc.nextDouble();
        sc.nextLine();

        saleDetailUseCase.updateSaleDetail(id, quantity, unitPrice);
        System.out.println("🚀 Detalle actualizado exitosamente.");
    }

    private static void mostrarDetalle(SaleDetail detalle) {
        System.out.println("Venta ID: " + detalle.getSaleId());
        System.out.println("Producto ID: " + detalle.getProductId());
        System.out.println("Cantidad: " + detalle.getQuantity());
        System.out.println("Precio Unitario: " + detalle.getUnitPrice());
        System.out.println("Subtotal: " + detalle.getSubtotal());
    }
}