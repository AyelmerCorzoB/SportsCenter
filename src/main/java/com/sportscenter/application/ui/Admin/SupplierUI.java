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
        
        do {
            ConsoleUtils.clear();
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
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 2:
                    SearchSupplier.Search(sc, supplierUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 3:
                    ConsoleUtils.clear();
                    ListProveedores(supplierUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 4:
                    UpdateSupplier.update(sc, supplierUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 5:
                    DeleteSupplier.Delete(sc, supplierUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 6: 
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("X Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 6);
    }

    private static void ListProveedores(SupplierUseCase supplierUseCase) {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         LISTADO DE PROVEEDORES                                               ║");
        System.out.println("╠════╦════════════════════════════╦════════════════════╦════════════════════╦══════════════════╣");
        System.out.println("║ ID ║ Nombre                     ║ Teléfono           ║ Dirección          ║ RFC              ║");
        System.out.println("╠════╬════════════════════════════╬════════════════════╬════════════════════╬══════════════════╣");
    
        try {
            List<Supplier> proveedores = supplierUseCase.getAllSuppliers();
    
            if (proveedores.isEmpty()) {
                System.out.println("║                       No hay proveedores registrados.                        ║");
                System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
                return;
            }
            for (Supplier prov : proveedores) {
                System.out.printf(
                    "║ %-4d ║ %-26s ║ %-18s ║ %-18s ║ %-16s ║%n",
                    prov.getId(),
                    prov.getName(),
                    prov.getPhone(),
                    prov.getAddress(),
                    prov.getTaxId()
                );
            }
    
            System.out.println("╚════╩════════════════════════════╩════════════════════╩════════════════════╩══════════════════╝");
            System.out.println("Total de proveedores: " + proveedores.size());
        } catch (Exception e) {
            System.out.println(" Error al obtener la lista de proveedores: " + e.getMessage());
        }
    }
    
    }
