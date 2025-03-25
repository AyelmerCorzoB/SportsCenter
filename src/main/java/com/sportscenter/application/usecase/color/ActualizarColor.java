package com.sportscenter.application.usecase.color;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationString;

public class ActualizarColor {
    public void actualizar(Scanner sc, ColorUseCase colorUseCase) {
        System.out.println("\n=== ACTUALIZAR COLOR ===");
        
        System.out.print("ID del color a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Nuevo nombre: ");
        ValidationString.validate(sc);
        String newName = sc.nextLine();
        
        System.out.print("Nuevo código HEX (opcional): ");
        String newHexCode = sc.nextLine();
        
        colorUseCase.updateColor(id, newName, newHexCode.isEmpty() ? null : newHexCode);
        System.out.println("✅ Color actualizado exitosamente.");
    }
}