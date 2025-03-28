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
            System.out.println("\n******** MENÚ DE VENTAS ********");
            System.out.println("1. Registrar venta");
            System.out.println("2. Buscar venta por ID");
            System.out.println("3. Listar todas las ventas");
            System.out.println("4. Actualizar una venta");
            System.out.println("5. Eliminar una venta");
            System.out.println("6. Menú de detalles de venta");
            System.out.println("7. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    RegistroSale.registro(sc, saleUseCase);
                    break;
                case 2:
                    BuscarSale.buscar(sc, saleUseCase);
                    break;
                case 3:
                    listarTodasVentas(saleUseCase);
                    break;
                case 4:
                    ActualizarSale.actualizar(sc, saleUseCase);
                    break;
                case 5:
                    EliminarSale.eliminar(sc, saleUseCase);
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

    private static void listarTodasVentas(SaleUseCase saleUseCase) {
        System.out.println("\n--- LISTA DE VENTAS ---");
        List<Sale> ventas = saleUseCase.getAllSales();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas");
            return;
        }

        System.out.printf("%-5s %-10s %-12s %-15s %-10s %-10s%n",
                "ID", "Cliente", "Fecha", "Método Pago", "Total", "Usuario");

        for (Sale venta : ventas) {
            System.out.printf("%-5d %-10d %-12s %-15d %-10.2f %-10d%n",
                    venta.getId(),
                    venta.getCustomerId(),
                    venta.getSaleDate(),
                    venta.getPaymentMethodId(),
                    venta.getTotal(),
                    venta.getUserId());
        }
    }
}