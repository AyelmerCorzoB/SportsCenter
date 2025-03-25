package com.sportscenter.adapter.validations;

import java.util.Scanner;

public class ValidationDouble {
    public static void validate(Scanner data){
        while (!data.hasNextDouble()) {
            System.out.println("Entrada inválida. Por favor, ingrese un Double.");
            System.out.print("Reintente: ");
            data.nextLine();
        }
    }
}
