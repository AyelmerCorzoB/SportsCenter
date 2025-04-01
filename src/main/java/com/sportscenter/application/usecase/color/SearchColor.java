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
        if (color != null) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║     INFORMACIÓN DEL COLOR    ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println(String.format("║ ID: %-24s ║", color.getId()));
            System.out.println(String.format("║ Nombre: %-21s║", color.getName()));
            String hexCode = color.getHexCode() != null ? color.getHexCode() : "N/A";
            System.out.println(String.format("║ HEX: %-23s ║", hexCode));
            System.out.println("╚══════════════════════════════╝");
        } else {
            System.out.println("X No se encontró el color con ID: " + id);
        }
    }
}