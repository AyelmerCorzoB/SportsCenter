package com.sportscenter.application.usecase.saledetail;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;

public class RegistroSaledetail {
    public static void registro(Scanner sc, SaleDetailUseCase saleDetailUseCase) {
        System.out.println("\n=== REGISTRO DE DETALLE DE VENTA ===");

        System.out.print("ID de la venta (saleId): ");
        ValidationInt.validate(sc);
        int saleId = sc.nextInt();
        sc.nextLine();

        System.out.print("ID del producto: ");
        ValidationInt.validate(sc);
        int productId = sc.nextInt();
        sc.nextLine();

        System.out.print("Cantidad: ");
        ValidationInt.validate(sc);
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.print("Precio unitario: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Por favor, ingrese un número válido.");
            sc.next();
        }
        double unitPrice = sc.nextDouble();
        sc.nextLine();

        try {
            saleDetailUseCase.registerSaleDetail(saleId, productId, quantity, unitPrice);
            System.out.println("✅ Detalle de venta registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al registrar detalle de venta: " + e.getMessage());
        }
    }
}
