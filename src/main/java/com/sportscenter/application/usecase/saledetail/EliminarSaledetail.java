package com.sportscenter.application.usecase.saledetail;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class EliminarSaledetail {
    public void eliminar(Scanner sc, SaleDetailUseCase saleDetailUseCase) {
        System.out.println("\n=== ELIMINAR DETALLES DE UNA VENTA ===");

        System.out.print("ID de la venta cuyos detalles deseas eliminar: ");
        ValidationInt.validate(sc);
        int saleId = sc.nextInt();
        sc.nextLine();

        saleDetailUseCase.deleteSaleDetails(saleId);
        System.out.println("✅ Detalles de la venta eliminados exitosamente.");
    }
}
