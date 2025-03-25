package com.sportscenter.application.usecase.supplier;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class EliminarSupplier {
    public void eliminar(Scanner sc, SupplierUseCase supplierUseCase) {
        System.out.println("\n=== ELIMINAR PROVEEDOR ===");
        
        System.out.print("ID del proveedor a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        try {
            supplierUseCase.deleteSupplier(id);
            System.out.println("✅ Proveedor eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar el proveedor: " + e.getMessage());
        }
    }
}