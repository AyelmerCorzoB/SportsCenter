package com.sportscenter.application.usecase.category;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class EliminarCategory {
    public void eliminar(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.print("Ingrese el ID de la categoría a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        categoryUseCase.deleteCategory(id);
        System.out.println("✅ Categoría eliminada exitosamente.");
    }
}