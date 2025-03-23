package com.skeletonhexa.application.usecase.category;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationInt;
import com.skeletonhexa.adapter.validations.ValidationString;

public class UpdateCategory {
    public void update(Scanner sc, CategoryUseCase categoryUseCase) {

        System.out.print("Ingrese el ID de la categoria que desea update: ");
        ValidationInt.validate(sc);
        int newId = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese el nuevo nombre para la categoria: ");
        ValidationString.validate(sc);
        String newName = sc.nextLine();
    
        
    
        System.out.print("Ingrese la nueva descripcion: ");
        ValidationString.validate(sc);
        String newDescription = sc.nextLine();
    
    
        categoryUseCase.updateCategry(newId, newName, newDescription);
        System.out.println("✅ Categoria actualizada exitosamente.");
    }
}