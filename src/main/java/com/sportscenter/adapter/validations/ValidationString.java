package com.sportscenter.adapter.validations;

import java.util.Scanner;

public class ValidationString {
    public static String validate(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Error: La entrada no puede estar vacía. Por favor, reintente.");
            System.out.print("Ingrese una cadena de texto válida: ");
        }
    }
}