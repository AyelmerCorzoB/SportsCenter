package com.sportscenter.application.usecase.Sale;

import com.sportscenter.domain.entities.Sale;

public class ListSale {
    public void List(SaleUseCase saleUseCase) {
        System.out.println("\n=== LISTADO DE LAS VENTAS ===");
        System.out.printf("%-5s %-12s %-12s %-12s %-10s %-10s%n",
                "ID", "ClienteID", "Fecha", "PagoID", "Total", "UsuarioID");
        System.out.println("---------------------------------------------------------------");

        for (Sale s : saleUseCase.getAllSales()) {
            System.out.printf("%-5d %-12d %-12s %-12d %-10.2f %-10d%n",
                    s.getId(),
                    s.getCustomerId(),
                    s.getSaleDate(),
                    s.getPaymentMethodId(),
                    s.getTotal(),
                    s.getUserId());
        }
    }
}
