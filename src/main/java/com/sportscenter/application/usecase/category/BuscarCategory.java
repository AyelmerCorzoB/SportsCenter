package com.sportscenter.application.usecase.category;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;

public class BuscarCategory {
    public void buscar(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.print("Ingrese el ID de la categoría a buscar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        categoryUseCase.getCategoryById(id);
    }
}