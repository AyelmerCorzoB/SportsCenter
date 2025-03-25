package com.sportscenter.adapter.validations;

import java.util.Scanner;

public class ValidationInt {
    public static void validate(Scanner data){
        while (!data.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            System.out.print("Reintente: ");
            data.nextLine();
        }
    }
}