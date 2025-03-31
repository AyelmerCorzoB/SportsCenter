package com.sportscenter.application.usecase.supplier;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationString;

public class UpdateSupplier {
    public static void Update(Scanner sc, SupplierUseCase supplierUseCase) {
        System.out.println("\n=== ACTUALIZAR PROVEEDOR ===");
        
        System.out.print("ID del proveedor a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Nuevo nombre: ");
        ValidationString.validate(sc);
        String newName = sc.nextLine();
        
        System.out.print("Nuevo teléfono: ");
        ValidationString.validate(sc);
        String newPhone = sc.nextLine();
        
        System.out.print("Nuevo email: ");
        ValidationString.validate(sc);
        String newEmail = sc.nextLine();
        
        System.out.print("Nueva dirección: ");
        ValidationString.validate(sc);
        String newAddress = sc.nextLine();
        
        System.out.print("Nuevo RUC/NIT: ");
        ValidationString.validate(sc);
        String newTaxId = sc.nextLine();
        
        

        try {
            supplierUseCase.updateSupplier(id, newName, newPhone, newEmail, newAddress, newTaxId);
        System.out.println(":D Proveedor actualizado exitosamente.");

        } catch (Exception e) {
            System.out.println("X Error al actualizar el proveedor: " + e.getMessage());
        }
    }
}