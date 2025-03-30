package com.sportscenter.application.usecase.purchase_detail;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.PurchaseDetail;

public class SearchPurchaseDetail {
    public static void obtenerPorId(Scanner sc, PurchaseDetailUseCase purchaseDetailUseCase) {
        System.out.println("\n=== CONSULTAR DETALLE DE COMPRA POR ID ===");

        System.out.print("ID del detalle de compra: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        try {
            PurchaseDetail purchaseDetail = purchaseDetailUseCase.getPurchaseDetailById(id);
            if (purchaseDetail != null) {
                System.out.println("\n📋 Detalles del item de compra:");
                System.out.println("ID Detalle: " + purchaseDetail.getId());
                System.out.println("ID Compra: " + purchaseDetail.getPurchaseId());
                System.out.println("ID Producto: " + purchaseDetail.getProductId());
                System.out.println("Cantidad: " + purchaseDetail.getQuantity());
                System.out.println("Precio Unitario: " + purchaseDetail.getUnitPrice());
                System.out.println("Subtotal: " + purchaseDetail.getSubtotal());
                System.out.println("Fecha creación: " + purchaseDetail.getCreatedAt());
            } else {
                System.out.println("⚠️ No se encontró un detalle de compra con ese ID.");
            }
        } catch (Exception e) {
            System.out.println("X Error al consultar detalle de compra: " + e.getMessage());
        }
    }
}
