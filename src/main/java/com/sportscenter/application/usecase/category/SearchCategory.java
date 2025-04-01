package com.sportscenter.application.usecase.category;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;

public class SearchCategory {
    public void Search(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.print("Ingrese el ID de la categoría a buscar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        categoryUseCase.getCategoryById(id);
        ConsoleUtils.clear();
        if (categoryUseCase.getCategoryById(id) != null) {
            String categoryId = String.valueOf(categoryUseCase.getCategoryById(id).getId());
            String nombre = categoryUseCase.getCategoryById(id).getName();
            String descripcion = categoryUseCase.getCategoryById(id).getDescription() != null
                    ? categoryUseCase.getCategoryById(id).getDescription()
                    : "N/A";
            
            // Trunca la descripción
            if (descripcion.length() > 27) {
                descripcion = descripcion.substring(0, 20) + "...";
            }

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║         INFORMACIÓN DE LA CATEGORÍA      ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println(String.format("║ ID: %-36s ║", categoryId));
            System.out.println(String.format("║ Nombre: %-32s ║", nombre));
            System.out.println(String.format("║ Descripción: %-27s ║", descripcion));
            System.out.println("╚══════════════════════════════════════════╝");
            
            ConsoleUtils.pressEnterToContinue(sc);
        } else {
            System.out.println("X No se encontró la categoría con ID: " + id);
            ConsoleUtils.pressEnterToContinue(sc);
        }
    }
}