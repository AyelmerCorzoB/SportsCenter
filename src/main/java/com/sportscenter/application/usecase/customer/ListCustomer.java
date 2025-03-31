package com.sportscenter.application.usecase.customer;

import com.sportscenter.domain.entities.Customer;

public class ListCustomer {
    public void List(CustomerUseCase customerUseCase) {
        System.out.println("\n=== LISTADO DE CLIENTES ===");

        System.out.printf("%-5s %-10s %-20s %-20s %-25s %-15s %-30s%n",
                "ID", "TipoID", "Nombre", "Documento", "Teléfono", "Dirección", "Fecha Reg.");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------");

        for (Customer customer : customerUseCase.getAllCustomers()) {

            System.out.printf("%-5d %-10d %-20s %-20s %-25s %-15s %-30s%n",
                    customer.getId(),
                    customer.getCustomerTypeId(),
                    customer.getName(),
                    customer.getIdentityDocument(),
                    customer.getPhone(),
                    customer.getAddress(),
                    customer.getRegistrationDate());
        }
    }

}
