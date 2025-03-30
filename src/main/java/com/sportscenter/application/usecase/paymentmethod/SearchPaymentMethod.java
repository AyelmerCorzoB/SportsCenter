package com.sportscenter.application.usecase.paymentmethod;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.PaymentMethod;

public class SearchPaymentMethod {
    public void Search(Scanner sc, PaymentMethodUseCase paymentMethodUseCase) {
        System.out.println("\n=== BUSCAR MÉTODO DE PAGO ===");
        
        System.out.print("ID del método: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        PaymentMethod method = paymentMethodUseCase.getPaymentMethodById(id);
        if(method != null) {
            System.out.println("\nInformación del método:");
            System.out.println("ID: " + method.getId());
            System.out.println("Método: " + method.getMethodName());
            System.out.println("Descripción: " + (method.getDescription() != null ? method.getDescription() : "N/A"));
        } else {
            System.out.println("X No se encontró el método con ID: " + id);
        }
    }
}