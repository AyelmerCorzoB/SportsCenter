package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.paymentmethod.*;

public class PaymentMethodUI {
    public static void manejarMenuPaymentMethod(Scanner sc, PaymentMethodUseCase paymentMethodUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                        \n╔══════════════════════════════════════╗
                        ║         MENÚ METODO DE PAGOS         ║
                        ╠══════════════════════════════════════╣
                        ║ 1. Registrar Metodo de pago          ║
                        ║ 2. Buscar Metodo de pago por ID      ║
                        ║ 3. Listar todos los Metodos de pagos ║
                        ║ 4. Actualizar Metodo de pago         ║
                        ║ 5. Eliminar Metodo de pago           ║
                        ║ 6. Salir                             ║
                        ╚══════════════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);
            

            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterPaymentMethod().Register(sc, paymentMethodUseCase);
                    break;
                case 2:
                    new SearchPaymentMethod().Search(sc, paymentMethodUseCase);
                    break;
                case 3:
                    paymentMethodUseCase.getAllPaymentMethods();
                    break;
                case 4:
                    new UpdatePaymentMethod().Update(sc, paymentMethodUseCase);
                    break;
                case 5:
                    new DeletePaymentMethod().Delete(sc, paymentMethodUseCase);
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
