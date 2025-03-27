package com.sportscenter.application.usecase.saledetail;

import java.util.List;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.SaleDetail;

public class BuscarSaleDetail {
    public static void buscarPorVenta(Scanner sc, SaleDetailUseCase saleDetailUseCase) {
        System.out.println("\n=== BUSCAR DETALLES DE VENTA ===");

        System.out.print("ID de la venta: ");
        ValidationInt.validate(sc);
        int saleId = sc.nextInt();
        sc.nextLine();

        List<SaleDetail> detalles = saleDetailUseCase.getDetailsBySaleId(saleId);
        if (detalles.isEmpty()) {
            System.out.println("❌ No se encontraron detalles para la venta con ID: " + saleId);
            return;
        }

        System.out.println("\n📋 Detalles de la venta " + saleId + ":");
        detalles.forEach(detalle -> {
            System.out.println("\n🆔 ID Detalle: " + detalle.getId());
            System.out.println("📦 Producto ID: " + detalle.getProductId());
            System.out.println("🔢 Cantidad: " + detalle.getQuantity());
            System.out.println("💰 Precio Unitario: $" + detalle.getUnitPrice());
            System.out.println("🧮 Subtotal: $" + detalle.getSubtotal());
        });
    }
}