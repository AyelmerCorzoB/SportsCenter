package com.sportscenter.application.usecase.color;

import java.util.Scanner;
import com.sportscenter.adapter.validations.ValidationString;
import com.sportscenter.adapter.validations.DuplicateColorException;

public class RegisterColor {
    public void Register(Scanner sc, ColorUseCase colorUseCase) {
        System.out.println("\n=== REGISTRO DE COLOR ===");

        boolean retry = true;
        while (retry) {
            try {
                System.out.print("Nombre del color: ");
                String name = ValidationString.validate(sc);

                String hexCode = null;
                System.out.print("Código HEX (opcional, formato #RRGGBB): ");
                String inputHex = sc.nextLine().trim().toUpperCase();

                if (!inputHex.isEmpty()) {
                    while (!inputHex.matches("^#[0-9A-F]{6}$")) {
                        System.out.println("Formato inválido. Debe ser # seguido de 6 caracteres hexadecimales (ej. #FF00A1)");
                        System.out.print("Código HEX (opcional): ");
                        inputHex = sc.nextLine().trim().toUpperCase();
                        if (inputHex.isEmpty())
                            break;
                    }
                    hexCode = inputHex.isEmpty() ? null : inputHex;
                }

                colorUseCase.registerColor(name, hexCode);
                System.out.println(":D Color registrado exitosamente.");
                retry = false;
                
            } catch (DuplicateColorException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("¿Desea intentar con otro nombre? (S/N)");
                String option = sc.nextLine().trim();
                if (!option.equalsIgnoreCase("S")) {
                    retry = false;
                }
            } catch (Exception e) {
                System.out.println("Error inesperado al registrar el color. Por favor, intente nuevamente.");
                System.out.println("¿Desea intentar nuevamente? (S/N)");
                String option = sc.nextLine().trim();
                if (!option.equalsIgnoreCase("S")) {
                    retry = false;
                }
            }
        }
    }
}