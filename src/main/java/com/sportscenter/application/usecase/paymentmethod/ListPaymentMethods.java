package com.sportscenter.application.usecase.paymentmethod;

public class ListPaymentMethods {
    public void List(PaymentMethodUseCase paymentMethodUseCase) {
        System.out.println("\n=== LISTADO DE MÉTODOS DE PAGO ===");
        System.out.printf("%-5s %-15s %-30s%n", "ID", "MÉTODO", "DESCRIPCIÓN");
        System.out.println("--------------------------------------------------");

        paymentMethodUseCase.getAllPaymentMethods().forEach(m -> System.out.printf("%-5d %-15s %-30s%n",
                m.getId(),
                m.getMethodName(),
                m.getDescription() != null ? m.getDescription() : "N/A"));
    }
}