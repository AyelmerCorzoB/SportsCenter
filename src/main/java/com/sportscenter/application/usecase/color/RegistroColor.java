package com.sportscenter.application.usecase.color;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationString;

public class RegistroColor {
    public void registro(Scanner sc, ColorUseCase colorUseCase) {
        System.out.print("Ingrese el nombre del color: ");
        ValidationString.validate(sc);
        String name = sc.nextLine();
        
        System.out.print("Ingrese el código HEX (opcional): ");
        String hexCode = sc.nextLine();
        
        colorUseCase.registerColor(name, hexCode.isEmpty() ? null : hexCode);
        System.out.println("✅ Color registrado exitosamente.");
    }
}