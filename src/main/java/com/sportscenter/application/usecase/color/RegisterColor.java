package com.sportscenter.application.usecase.color;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationString;

public class RegisterColor {
    public void Register(Scanner sc, ColorUseCase colorUseCase) {
        System.out.println("\n=== REGISTRO DE COLOR ===");
        
        // Nombre (validado y obligatorio)
        System.out.print("Nombre del color: ");
        String name = ValidationString.validate(sc); // Aquí se usa el resultado validado
        
        // Código HEX (validado opcional)
        String hexCode = null;
        System.out.print("Código HEX (opcional, formato #RRGGBB): ");
        String inputHex = sc.nextLine().trim().toUpperCase();
        
        if(!inputHex.isEmpty()) {
            while(!inputHex.matches("^#[0-9A-F]{6}$")) {
                System.out.println("Formato inválido. Debe ser # seguido de 6 caracteres hexadecimales (ej. #FF00A1)");
                System.out.print("Código HEX (opcional): ");
                inputHex = sc.nextLine().trim().toUpperCase();
                if(inputHex.isEmpty()) break;
            }
            hexCode = inputHex.isEmpty() ? null : inputHex;
        }
        
        colorUseCase.registerColor(name, hexCode);
        System.out.println(":D Color registrado exitosamente.");
    }
}