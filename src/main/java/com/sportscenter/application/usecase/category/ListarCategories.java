package com.sportscenter.application.usecase.category;

public class ListarCategories {
    public void listar(CategoryUseCase categoryUseCase) {
        System.out.println("\n=== LISTADO DE CATEGORÍAS ===");
        categoryUseCase.getAllCategories()
                .forEach(c -> System.out.println("ID: " + c.getId() + " | Nombre: " + c.getName()));
    }
}
