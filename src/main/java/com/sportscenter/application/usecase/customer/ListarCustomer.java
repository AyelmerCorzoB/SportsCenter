package com.sportscenter.application.usecase.customer;

import com.sportscenter.domain.entities.Customer;

public class ListarCustomer {
    public void listar(CustomerUseCase customerUseCase) {
        System.out.println("\n=== LISTADO DE CLIENTES ===");
        System.out.printf("%-5s %-10s %-20s %-20s %-25s %-15s %-30s %-15s%n", 
            "ID", "TipoID", "Nombre", "Documento", "Email", "Teléfono", "Dirección", "Fecha Reg.");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        for (Customer customer : customerUseCase.getAllCustomers()) {
            System.out.printf("%-5d %-10d %-20s %-20s %-25s %-15s %-30s %-15s%n",
                customer.getId(),
                customer.getCustomerTypeId(),
                customer.getName(),
                customer.getIdentityDocument(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getRegistrationDate()
            );
        }
    }
}
