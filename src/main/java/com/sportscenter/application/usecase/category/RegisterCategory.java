package com.sportscenter.application.usecase.category;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationString;

public class RegisterCategory {
    public void Register(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.print("Ingrese el nombre de la categoría: ");
        String name = ValidationString.validate(sc);
        
        System.out.print("Ingrese la descripción (opcional): ");
        String description = sc.nextLine();
        
        categoryUseCase.registerCategory(name, description);
        System.out.println("🚀 Categoría registrada exitosamente.");
    }
}