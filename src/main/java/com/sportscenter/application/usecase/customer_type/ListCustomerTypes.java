package com.sportscenter.application.usecase.customer_type;

public class ListCustomerTypes {
    public void List(CustomerTypeUseCase customerTypeUseCase) {
        System.out.println("\n=== LISTADO DE TIPOS DE CLIENTE ===");
        System.out.printf("%-5s %-15s %-30s%n", "ID", "TIPO", "DESCRIPCIÓN");
        System.out.println("--------------------------------------------------");

        customerTypeUseCase.getAllCustomerTypes().forEach(t -> System.out.printf("%-5d %-15s %-30s%n",
                t.getId(),
                t.getTypeName(),
                t.getDescription() != null ? t.getDescription() : "N/A"));
    }
}