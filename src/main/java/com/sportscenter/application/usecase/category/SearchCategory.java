package com.sportscenter.application.usecase.category;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;

public class SearchCategory {
    public void Search(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.print("Ingrese el ID de la categoría a Search: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        categoryUseCase.getCategoryById(id);
        ConsoleUtils.clear();
        if (categoryUseCase.getCategoryById(id) != null) {
            System.out.println("\nInformación de la categoría:");
            System.out.println("ID: " + categoryUseCase.getCategoryById(id).getId());
            System.out.println("Nombre: " + categoryUseCase.getCategoryById(id).getName());
            System.out.println("Descripción: " + (categoryUseCase.getCategoryById(id).getDescription() != null ? categoryUseCase.getCategoryById(id).getDescription() : "N/A"));
            ConsoleUtils.pressEnterToContinue(sc);
        } else {
            System.out.println("X No se encontró la categoría con ID: " + id);
            ConsoleUtils.pressEnterToContinue(sc);
        }
    }
}