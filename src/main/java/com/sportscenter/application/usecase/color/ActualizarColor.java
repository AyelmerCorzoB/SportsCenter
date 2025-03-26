package com.sportscenter.application.usecase.color;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationString;

public class ActualizarColor {
    public void actualizar(Scanner sc, ColorUseCase colorUseCase) {
        System.out.println("\n=== ACTUALIZAR COLOR ===");
        
        // Solicitar ID (validado)
        System.out.print("ID del color a actualizar: ");
        ValidationInt.validate(sc); // Valida que sea entero
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        
        // Solicitar nuevo nombre (validado)
        System.out.print("Nuevo nombre: ");
        String newName = sc.nextLine();
        while(newName.trim().isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío");
            System.out.print("Nuevo nombre: ");
            newName = sc.nextLine();
        }
        
        // Solicitar código HEX (opcional con validación)
        String newHexCode = null;
        System.out.print("Nuevo código HEX (opcional, formato #RRGGBB): ");
        String hexInput = sc.nextLine().trim().toUpperCase();
        
        if(!hexInput.isEmpty()) {
            if(hexInput.matches("^#[0-9A-F]{6}$")) {
                newHexCode = hexInput;
            } else {
                System.out.println("Formato HEX inválido. Se guardará sin código.");
            }
        }
        
        colorUseCase.updateColor(id, newName, newHexCode);
        System.out.println("✅ Color actualizado exitosamente.");
    }
}