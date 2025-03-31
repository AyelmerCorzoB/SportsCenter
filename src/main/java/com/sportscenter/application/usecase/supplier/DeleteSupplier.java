package com.sportscenter.application.usecase.supplier;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeleteSupplier {
    public static void Delete(Scanner sc, SupplierUseCase supplierUseCase) {
        System.out.println("\n=== ELIMINAR PROVEEDOR ===");
        
        System.out.print("ID del proveedor a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        try {
            supplierUseCase.deleteSupplier(id);
            System.out.println(":D Proveedor eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("X Error al eliminar el proveedor: " + e.getMessage());
        }
    }
}