package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.paymentmethod.*;

public class PaymentMethodUI {
    public static void manejarMenuPaymentMethod(Scanner sc, PaymentMethodUseCase paymentMethodUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            System.out.println("\n******** MENÚ DE PAYMENTMETHOD ********");
            System.out.println("1. Registrar paymentMethod");
            System.out.println("2. Buscar paymentMethod por ID");
            System.out.println("3. Listar todas los paymentMethod");
            System.out.println("4. Actualizar un paymentMethod");
            System.out.println("5. Eliminar una paymentMethod");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegistroPaymentMethod().registro(sc, paymentMethodUseCase);
                    break;
                case 2:
                    new BuscarPaymentMethod().buscar(sc, paymentMethodUseCase);
                    break;
                case 3:
                    paymentMethodUseCase.getAllPaymentMethods();
                    break;
                case 4:
                    new ActualizarPaymentMethod().actualizar(sc, paymentMethodUseCase);
                    break;
                case 5:
                    new EliminarPaymentMethod().eliminar(sc, paymentMethodUseCase);
                    break;
                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("X Opción inválida. Por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 6);
    }
}
