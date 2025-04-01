package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.customerorder.*;

public class CustomerOrderUI {
    public static void mostrarMenu(Scanner sc, CustomerOrderUseCase customerOrderUseCase) {
        int opcion;
        
        do {
            String menu = """
                        \n╔═══════════════════════════════╗
                        ║       MENÚ CUSTOMER ORDER      ║
                        ╠════════════════════════════════╣
                        ║ 1. Registrar CustomerOrder     ║
                        ║ 2. Buscar customerOrder por ID ║
                        ║ 3. Listar todos                ║
                        ║ 4. Actualizar customerOrder    ║
                        ║ 5. Eliminar customerOrder      ║
                        ║ 6. Volver                      ║
                        ╚════════════════════════════════╝
                        Seleccione una opción:""";
            ConsoleUtils.clear();
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();

            sc.nextLine();

            switch (opcion) {
                case 1:
                    
                new RegisterCustomerorder().Register(sc, customerOrderUseCase);
                ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 2:
                ConsoleUtils.clear();
                new SearchCustomerorder().Search(sc, customerOrderUseCase);
                ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 3:
                ConsoleUtils.clear();
                    customerOrderUseCase.getAllCustomerOrders();
                ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 4:
                    new UpdateCustomerorder().Update(sc, customerOrderUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 5:
                    new DeleteCustomerOrder().Delete(sc, customerOrderUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
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
