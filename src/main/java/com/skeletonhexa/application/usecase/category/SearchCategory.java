package com.skeletonhexa.application.usecase.category;

import java.util.Scanner;

import com.skeletonhexa.adapter.validations.ValidationInt;

public class SearchCategory {
    public void buscar(Scanner sc, CategoryUseCase categoryUseCase) {
        System.out.print("Ingresa el ID de la categoria a buscar: ");
        ValidationInt.validate(sc);
        int idBusqueda = sc.nextInt();
        sc.nextLine();
        categoryUseCase.getCategory(idBusqueda);
    }
}
