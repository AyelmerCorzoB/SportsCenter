package com.skeletonhexa.application.usecase.category;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationString;

public class RegisterCategory {
    public void register(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.print("Ingrese el nombre para la categoria: ");
        ValidationString.validate(sc);
        String name = sc.nextLine();
    
        System.out.print("Ingrese la descripcion: ");
        ValidationString.validate(sc);
        String description = sc.nextLine();
    
        categoryUseCase.registerCategory( name, description);
        System.out.println("✅ Categoria registrada exitosamente.");
    }
}
