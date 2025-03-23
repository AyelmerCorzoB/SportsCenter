package com.skeletonhexa.adapter.validations;

import java.util.Scanner;

public class ValidationString {
    public static String validate(Scanner data) {
        while (true) {
            System.out.print("Ingrese una cadena de texto: ");
            String input = data.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Error: La entrada no puede estar vacía. Por favor, reintente.");
        }
    }
}
