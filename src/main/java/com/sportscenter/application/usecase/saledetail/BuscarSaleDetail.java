package com.sportscenter.application.usecase.saledetail;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.Sale.SaleUseCase;
import com.sportscenter.domain.entities.Sale;

public class BuscarSaleDetail {
    public void buscar(Scanner sc, SaleUseCase saleUseCase) {
        System.out.println("\n=== BUSCAR VENTA POR ID ===");

        System.out.print("ID de la venta: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        Sale sale = saleUseCase.getSaleById(id);
        if (sale != null) {
            System.out.println("\n📄 Información de la venta:");
            System.out.println("🆔 ID: " + sale.getId());
            System.out.println("👤 ID Cliente: " + sale.getCustomerId());
            System.out.println("📅 Fecha de venta: " + sale.getSaleDate());
            System.out.println("💳 Método de pago ID: " + sale.getPaymentMethodId());
            System.out.println("💰 Total: $" + sale.getTotal());
            System.out.println("👨‍💼 ID Usuario: " + sale.getUserId());
        } else {
            System.out.println("❌ No se encontró ninguna venta con ID: " + id);
        }
    }
}
