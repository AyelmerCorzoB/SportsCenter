package com.sportscenter.application.usecase.color;

public class ListarColores {
    public void listar(ColorUseCase colorUseCase) {
        System.out.println("\n=== LISTADO DE COLORES ===");
        System.out.printf("%-5s %-20s %-10s%n", "ID", "NOMBRE", "CÓDIGO");
        System.out.println("----------------------------------");

        colorUseCase.getAllColors().forEach(c -> System.out.printf("%-5d %-20s %-10s%n",
                c.getId(),
                c.getName(),
                c.getHexCode() != null ? c.getHexCode() : "N/A"));
    }
}