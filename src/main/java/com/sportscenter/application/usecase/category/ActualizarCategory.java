package com.sportscenter.application.usecase.category;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationString;

public class ActualizarCategory {
    public void actualizar(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.println("\n=== ACTUALIZAR CATEGORÍA ===");

        int id;
        do {
            System.out.print("Ingrese el ID de la categoría a actualizar: ");
            ValidationInt.validate(sc);
            id = sc.nextInt();
            sc.nextLine();

            if (categoryUseCase.getCategoryById(id) == null) {
                System.out.println("X No existe una categoría con el ID: " + id);
                System.out.println("Por favor, ingrese un ID válido.");
                ConsoleUtils.pressEnterToContinue(sc);
                ConsoleUtils.clear();
            }
        } while (categoryUseCase.getCategoryById(id) == null);

        System.out.print("Nuevo nombre: ");
        String newName = ValidationString.validate(sc);

        System.out.print("Nueva descripción (opcional): ");
        String newDescription = sc.nextLine();

        categoryUseCase.updateCategory(id, newName, newDescription.isEmpty() ? null : newDescription);
        System.out.println("✅ Categoría actualizada exitosamente.");
    }
}