package com.sportscenter.application.usecase.Sale;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.domain.entities.Sale;

public class ListSale {
    public void List(SaleUseCase saleUseCase) {
        ConsoleUtils.clear();
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                  LISTADO DE LAS VENTAS                            ║");
        System.out.println("╠═════╦════════════╦════════════╦════════════╦══════════╦═══════════╣");
        System.out.printf("║ %-3s ║ %-10s ║ %-10s ║ %-10s ║ %-8s ║ %-9s ║%n",
                "ID", "ClienteID", "Fecha", "PagoID", "Total", "UsuarioID");
        System.out.println("╠═════╬════════════╬════════════╬════════════╬══════════╬═══════════╣");

        for (Sale s : saleUseCase.getAllSales()) {
            System.out.printf("║ %-3d ║ %-10d ║ %-10s ║ %-10d ║ %-8.2f ║ %-9d ║%n",
                    s.getId(),
                    s.getCustomerId(),
                    s.getSaleDate(),
                    s.getPaymentMethodId(),
                    s.getTotal(),
                    s.getUserId());
        }

        System.out.println("╚═════╩════════════╩════════════╩════════════╩══════════╩═══════════╝");
    }
}