package com.sportscenter.application.usecase.Sale;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeleteSale {
    public static void Delete(Scanner sc, SaleUseCase saleUseCase) {
        System.out.println("\n=== ELIMINAR VENTA ===");

        System.out.print("ID de la venta a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        saleUseCase.deleteSale(id);
        System.out.println("🚀 Venta eliminada exitosamente.");
    }
}
