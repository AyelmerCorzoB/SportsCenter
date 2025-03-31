package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.customer_type.*;

public class CustomerTypeUI {

    public static void mostrarMenu(Scanner sc, CustomerTypeUseCase customerTypeUseCase) {
        int opcion;
        do {
            String menu = """
                    \n╔═══════════════════════════════════════╗
                    ║         MENU CustomerType               ║
                    ║  Recordatorio: si ya tienes los 2       ║
                    ║  tipos que son:(INDIVIDUAL) y (COMPANY) ║
                    ╠═════════════════════════════════════════╣
                    ║ 1. Registrar CustomerType               ║
                    ║ 2. Obtener customerType por ID          ║
                    ║ 3. Listar todos los customerType        ║
                    ║ 4. Update un customerType               ║
                    ║ 5. Eliminar un customerType             ║
                    ║ 6. Salir...                             ║
                    ╚═════════════════════════════════════════╝
                    Seleccione una opción:""";
            ConsoleUtils.clear();
            System.out.print(menu);
            ValidationInt.validate(sc);
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    new RegisterCustomerType().Register(sc, customerTypeUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 2:
                    new SearchCustomerType().Search(sc, customerTypeUseCase);
                    break;
                case 3:
                    customerTypeUseCase.getAllCustomerTypes();
                    break;
                case 4:
                    new UpdateCustomerType().Update(sc, customerTypeUseCase);
                    break;
                case 5:
                    new DeleteCustomerType().Delete(sc, customerTypeUseCase);
                    break;
                case 6:
                    System.out.println("Saliendo....");
                    break;
                default:
                    System.out.println("Opción inválida. Vuelva a intentarlo.");
                    break;
            }
        } while (opcion != 6);
    }
}
