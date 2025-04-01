package com.sportscenter.application.usecase.customer_type;

public class ListCustomerTypes {
    public void List(CustomerTypeUseCase customerTypeUseCase) {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║           LISTADO DE TIPOS DE CLIENTE            ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  ID   TIPO          DESCRIPCIÓN                  ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        
        customerTypeUseCase.getAllCustomerTypes().forEach(t -> {
            String descripcion = t.getDescription() != null ? t.getDescription() : "N/A";
            System.out.printf("║ %-4d %-13s %-29s ║%n",
                    t.getId(),
                    t.getTypeName(),
                    descripcion.length() > 30 ? descripcion.substring(0, 27) + "..." : descripcion);
        });
        
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}