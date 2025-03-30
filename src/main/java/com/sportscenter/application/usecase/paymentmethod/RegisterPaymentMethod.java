package com.sportscenter.application.usecase.paymentmethod;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class RegisterPaymentMethod {
    public void Register(Scanner sc, PaymentMethodUseCase paymentMethodUseCase) {
        System.out.println("\n=== REGISTRO DE MÉTODO DE PAGO ===");

        System.out.println("Opciones: \n1. CASH \n2. CARD \n3. TRANSFER");
        System.out.print("Método de pago: ");
        ValidationInt.validate(sc);
        String methodName = null;
        int methodNameOption = sc.nextInt();
        switch (methodNameOption) {
            case 1:
                methodName = "CASH";
                break;
            case 2:
                methodName = "CARD";
                break;
            case 3:
                methodName = "TRANSFER";
                break;
            default:
                System.out.println("Opcion invalido debe ser 1,2 o 3");
                break;
        }

        System.out.print("Descripción (opcional): ");
        String description = sc.nextLine();

        paymentMethodUseCase.registerPaymentMethod(methodName, description.isEmpty() ? null : description);
        System.out.println("🚀 Método de pago registrado exitosamente.");
    }
}