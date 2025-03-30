package com.sportscenter.application.usecase.paymentmethod;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class UpdatePaymentMethod {
    public void Update(Scanner sc, PaymentMethodUseCase paymentMethodUseCase) {
        System.out.println("\n=== ACTUALIZAR MÉTODO DE PAGO ===");

        System.out.print("ID del método a actualizar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Opciones: \n1. CASH \n2. CARD \n3. TRANSFER");
        System.out.print("Método de pago: ");
        ValidationInt.validate(sc);
        String newMethodName = null;
        int methodNameOption = sc.nextInt();
        switch (methodNameOption) {
            case 1:
                newMethodName = "CASH";
                break;
            case 2:
                newMethodName = "CARD";
                break;
            case 3:
                newMethodName = "TRANSFER";
                break;
            default:
                System.out.println("Opcion invalido debe ser 1,2 o 3");
                break;
        }

        System.out.print("Nueva descripción (opcional): ");
        String newDescription = sc.nextLine();

        paymentMethodUseCase.updatePaymentMethod(id, newMethodName, newDescription.isEmpty() ? null : newDescription);
        System.out.println("🚀 Método de pago actualizado exitosamente.");
    }
}