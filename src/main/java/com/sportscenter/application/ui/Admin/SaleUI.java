package com.sportscenter.application.ui.Admin;

import java.util.List;
import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.Sale.*;
import com.sportscenter.application.usecase.saledetail.SaleDetailUseCase;
import com.sportscenter.domain.entities.Sale;

public class SaleUI {
    public static void mostrarMenu(Scanner sc, SaleUseCase saleUseCase, SaleDetailUseCase saleDetailUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                    \n╔═══════════════════════════╗
                    ║        MENÚ VENTAS        ║
                    ╠═══════════════════════════╣
                    ║ 1. Registrar Ventas       ║
                    ║ 2. Buscar venta por ID    ║
                    ║ 3. Listar todos           ║
                    ║ 4. Actualizar venta       ║
                    ║ 5. Eliminar venta         ║
                    ║ 6. Menu de detalles       ║
                    ║ 7. Salir                  ║
                    ╚═══════════════════════════╝
                    Seleccione una opción:""";
            System.out.print(menu);
            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    RegisterSale.Register(sc, saleUseCase);
                    break;
                case 2:
                    SearchSale.Search(sc, saleUseCase);
                    break;
                case 3:
                    ListTodasVentas(saleUseCase);
                    break;
                case 4:
                    UpdateSale.Update(sc, saleUseCase);
                    break;
                case 5:
                    DeleteSale.Delete(sc, saleUseCase);
                    break;
                case 6:

                    SaleDetailUI.manejarMenuSaleDetail(sc, saleDetailUseCase);
                    break;
                case 7:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("X Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 7);
    }

    private static void ListTodasVentas(SaleUseCase saleUseCase) {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                               LISTA DE VENTAS                                      ║");
        System.out.println("╠═════╦════════════╦════════════╦════════════════╦════════════╦═══════════════╣");
        System.out.printf("║ %-3s ║ %-10s ║ %-10s ║ %-14s ║ %-10s ║ %-13s ║%n",
                "ID", "Cliente", "Fecha", "Método Pago", "Total", "Usuario");
        System.out.println("╠═════╬════════════╬════════════╬════════════════╬════════════╬═══════════════╣");
        
        List<Sale> ventas = saleUseCase.getAllSales();
        
        if (ventas.isEmpty()) {
            System.out.println("║                          No hay ventas registradas                                ║");
        } else {
            for (Sale venta : ventas) {
                System.out.printf("║ %-3d ║ %-10d ║ %-10s ║ %-14d ║ %-10.2f ║ %-13d ║%n",
                        venta.getId(),
                        venta.getCustomerId(),
                        venta.getSaleDate(),
                        venta.getPaymentMethodId(),
                        venta.getTotal(),
                        venta.getUserId());
            }
        }
        System.out.println("╚═════╩════════════╩════════════╩════════════════╩════════════╩═══════════════╝\n");
    }
    
    public static void procesarNuevaVenta(Scanner sc, SaleUseCase saleUseCase) {
        RegisterSale.Register(sc, saleUseCase);
    }
}