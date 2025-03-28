package com.sportscenter.application.usecase.saledetail;

import java.util.List;
import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.SaleDetail;

public class ListarSaledetail {
    public void listar(Scanner sc, SaleDetailUseCase saleDetailUseCase) {
        System.out.println("\n=== DETALLES DE UNA VENTA ===");

        System.out.print("Ingrese el ID de la venta: ");
        ValidationInt.validate(sc);
        int saleId = sc.nextInt();
        sc.nextLine();

        List<SaleDetail> details = saleDetailUseCase.getDetailsBySaleId(saleId);

        if (details.isEmpty()) {
            System.out.println("X No se encontraron detalles para la venta con ID: " + saleId);
            return;
        }

        System.out.printf("%-5s %-10s %-10s %-10s%n", "ID", "ProductoID", "Cantidad", "PrecioUnitario");
        System.out.println("------------------------------------------------");

        for (SaleDetail detail : details) {
            System.out.printf("%-5d %-10d %-10d %-10.2f%n",
                    detail.getId(),
                    detail.getProductId(),
                    detail.getQuantity(),
                    detail.getUnitPrice());
        }
    }
}
