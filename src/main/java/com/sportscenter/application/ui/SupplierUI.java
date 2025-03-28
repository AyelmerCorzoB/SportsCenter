package com.sportscenter.application.ui;

import java.util.List;
import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.supplier.*;
import com.sportscenter.domain.entities.Supplier;

public class SupplierUI {
    public static void manejarMenuSupplier(Scanner sc, SupplierUseCase supplierUseCase) {
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
            System.out.print("Seleccione una opción: ");
            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    RegistroSupplier.registro(sc, supplierUseCase);
                    break;
                case 2:
                    BuscarSupplier.buscar(sc, supplierUseCase);
                    break;
                case 3:
                    listarProveedores(supplierUseCase);
                    break;
                case 4:
                    ActualizarSupplier.actualizar(sc, supplierUseCase);
                    break;
                case 5:
                    EliminarSupplier.eliminar(sc, supplierUseCase);
                    break;
                default:
                    System.out.println("X Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 7);
    }

    private static void listarProveedores(SupplierUseCase supplierUseCase) {
        System.out.println("\n--- LISTA DE PROVEEDORES ---");
        List<Supplier> proveedores = supplierUseCase.getAllSuppliers();

        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-25s %-30s %-15s%n",
                "ID", "Nombre", "Teléfono", "Email", "Dirección", "RUC/NIT");

        for (Supplier prov : proveedores) {
            System.out.printf("%-5d %-20s %-15s %-25s %-30s %-15s%n",
                    prov.getId(),
                    prov.getName(),
                    prov.getPhone(),
                    prov.getEmail(),
                    prov.getAddress(),
                    prov.getTaxId());
        }
    }

    public void mostrarMenu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarMenu'");
    }
}
