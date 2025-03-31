package com.sportscenter.application.usecase.paymentmethod;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeletePaymentMethod {
    public void Delete(Scanner sc, PaymentMethodUseCase paymentMethodUseCase) {
        System.out.println("\n=== ELIMINAR MÉTODO DE PAGO ===");
        
        System.out.print("ID del método a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        paymentMethodUseCase.deletePaymentMethod(id);
        System.out.println(":D Método de pago eliminado exitosamente.");
    }
}