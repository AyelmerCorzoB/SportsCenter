package com.sportscenter.application.usecase.category;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;

public class ListCategories {
    public void List(CategoryUseCase categoryUseCase) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== LISTADO DE CATEGORÍAS ===");
        categoryUseCase.getAllCategories()
                .forEach(c -> System.out.println("ID: " + c.getId() + " | Nombre: " + c.getName()));
        ConsoleUtils.pressEnterToContinue(sc);
    }
}
