package com.sportscenter.application.usecase.customer_type;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.domain.entities.CustomerType;

public class SearchCustomerType {
    public void Search(Scanner sc, CustomerTypeUseCase customerTypeUseCase) {
        System.out.println("\n=== BUSCAR TIPO DE CLIENTE ===");

        System.out.print("ID del tipo de cliente: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();

        CustomerType type = customerTypeUseCase.getCustomerTypeById(id);
        if (type != null) {
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║        INFORMACIÓN DEL TIPO DE CLIENTE   ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println(String.format("║ ID: %-36s ║", type.getId()));
            System.out.println(String.format("║ Tipo: %-34s ║", type.getTypeName()));
            String descripcion = type.getDescription() != null ? type.getDescription() : "N/A";
            System.out.println(String.format("║ Descripción: %-27s ║",
                    descripcion.length() > 27 ? descripcion.substring(0, 24) + "..." : descripcion));
            System.out.println("╚══════════════════════════════════════════╝");
        } else {
            System.out.println("X No se encontró el tipo con ID: " + id);
        }
    }
}