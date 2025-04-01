package com.sportscenter.application.usecase.customer;

import com.sportscenter.domain.entities.Customer;

public class ListCustomer {
    public void List(CustomerUseCase customerUseCase) {
        System.out.println(
                "╔══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(
                "║                                        LISTADO DE CLIENTES                                           ║");
        System.out.println(
                "╠════╦════════════════╦══════════════════════╦══════════════════════╦══════════════╦══════════════╦════════════╗");
        System.out.println(
                "║ ID ║ Tipo           ║ Nombre               ║ Documento            ║ Teléfono     ║ Dirección    ║ Fecha Reg. ║");
        System.out.println(
                "╠════╬════════════════╬══════════════════════╬══════════════════════╬══════════════╬══════════════╬════════════╣");

        for (Customer customer : customerUseCase.getAllCustomers()) {
            String tipoCliente = getTipoClienteLiteral(customer.getCustomerTypeId());
            String direccion = truncate(customer.getAddress(), 12);
            String fechaRegistro = customer.getRegistrationDate() != null ? 
                customer.getRegistrationDate().toString() : "N/A";

            System.out.printf(
                    "║ %-2d ║ %-14s ║ %-20s ║ %-20s ║ %-12s ║ %-12s ║ %-10s ║%n",
                    customer.getId(),
                    tipoCliente,
                    truncate(customer.getName(), 20),
                    truncate(customer.getIdentityDocument(), 20),
                    truncate(customer.getPhone(), 12),
                    direccion,
                    fechaRegistro);
        }
        System.out.println(
                "╚════╩════════════════╩══════════════════════╩══════════════════════╩══════════════╩══════════════╩════════════╝");
    }

    private String getTipoClienteLiteral(int tipoId) {
        switch(tipoId) {
            case 1: return "INDIVIDUAL";
            case 2: return "COMPANY";
            default: return "DESCONOCIDO";
        }
    }

    private String truncate(String value, int length) {
        if (value == null || value.trim().isEmpty()) return "N/A";
        return value.length() > length ? value.substring(0, length - 3) + "..." : value;
    }
}