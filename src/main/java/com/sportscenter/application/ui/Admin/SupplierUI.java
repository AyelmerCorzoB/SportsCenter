package com.sportscenter.application.ui.Admin;

import java.util.List;
import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.supplier.*;
import com.sportscenter.domain.entities.Supplier;

public class SupplierUI {
    public static void mostrarMenu(Scanner sc, SupplierUseCase supplierUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                        \n╔═════════════════════════════╗
                        ║       MENÚ PROVEEDORES      ║
                        ╠═════════════════════════════╣
                        ║ 1. Registrar Proveedor      ║
                        ║ 2. Buscar Proveedor por ID  ║
                        ║ 3. Listar todos             ║
                        ║ 4. Actualizar Proveedor     ║
                        ║ 5. Eliminar Proveedor       ║
                        ║ 6. Salir                    ║
                        ╚═════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);
            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    RegisterSupplier.Register(sc, supplierUseCase);
                    break;
                case 2:
                    SearchSupplier.Search(sc, supplierUseCase);
                    break;
                case 3:
                    ListProveedores(supplierUseCase);
                    break;
                case 4:
                    UpdateSupplier.Update(sc, supplierUseCase);
                    break;
                case 5:
                    DeleteSupplier.Delete(sc, supplierUseCase);
                    break;
                default:
                    System.out.println("X Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 7);
    }

    private static void ListProveedores(SupplierUseCase supplierUseCase) {
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
}
