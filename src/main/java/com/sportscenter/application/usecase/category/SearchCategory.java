package com.sportscenter.application.usecase.category;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationInt;

public class SearchCategory {
    public void Search(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.print("Ingrese el ID de la categoría a Search: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        categoryUseCase.getCategoryById(id);
    }
}