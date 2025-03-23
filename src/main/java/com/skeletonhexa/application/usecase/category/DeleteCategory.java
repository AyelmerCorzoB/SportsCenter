package com.skeletonhexa.application.usecase.category;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationInt;

public class DeleteCategory {
    public void eliminar(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.print("Ingresa el ID de la categoria que quieres eliminar: ");
        ValidationInt.validate(sc);
        int idEliminar = sc.nextInt();
        categoryUseCase.deleteCategory(idEliminar);
    }
}
