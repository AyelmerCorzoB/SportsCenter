package com.sportscenter.application.usecase.color;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class EliminarColor {
    public void eliminar(Scanner sc, ColorUseCase colorUseCase) {
        System.out.println("\n=== ELIMINAR COLOR ===");
        
        System.out.print("ID del color a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        colorUseCase.deleteColor(id);
        System.out.println("✅ Color eliminado exitosamente.");
    }
}