package com.sportscenter.application.usecase.category;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.adapter.validations.ValidationString;

public class ActualizarCategory {
    public void actualizar(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.print("Ingrese el ID de la categoría a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Ingrese el nuevo nombre: ");
        ValidationString.validate(sc);
        String newName = sc.nextLine();
        
        System.out.print("Ingrese la nueva descripción: ");
        String newDescription = sc.nextLine();
        
        categoryUseCase.updateCategory(id, newName, newDescription);
        System.out.println("✅ Categoría actualizada exitosamente.");
    }
}