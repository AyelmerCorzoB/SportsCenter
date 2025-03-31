package com.sportscenter.application.usecase.customer_type;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeleteCustomerType {
    public void Delete(Scanner sc, CustomerTypeUseCase customerTypeUseCase) {
        System.out.println("\n=== ELIMINAR TIPO DE CLIENTE ===");
        
        System.out.print("ID del tipo a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        customerTypeUseCase.deleteCustomerType(id);
        System.out.println(":D Tipo de cliente eliminado exitosamente.");
    }
}