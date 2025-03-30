package com.sportscenter.application.usecase.color;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.Color;

public class SearchColor {
    public void Search(Scanner sc, ColorUseCase colorUseCase) {
        System.out.println("\n=== BUSCAR COLOR ===");
        
        System.out.print("Ingrese el ID del color: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        Color color = colorUseCase.getColorById(id);
        if(color != null) {
            System.out.println("\nInformación del color:");
            System.out.println("ID: " + color.getId());
            System.out.println("Nombre: " + color.getName());
            System.out.println("Código HEX: " + (color.getHexCode() != null ? color.getHexCode() : "N/A"));
        } else {
            System.out.println("X No se encontró el color con ID: " + id);
        }
    }
}