﻿package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.customerorder.*;

public class CustomerOrderUI {
    public static void mostrarMenu(Scanner sc, CustomerOrderUseCase customerOrderUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menuCustomerOrder = """
                    ******** CustomerOrder ********
                    1. Registrar CustomerOrder
                    2. Obtener customerOrder por ID
                    3. Listar todos los customerOrder
                    4. Actualizar un customerOrder
                    5. Eliminar un customerOrder
                    6. Salir...
                    Seleccione una opción:""";
            System.out.print(menuCustomerOrder);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();

            sc.nextLine();

            switch (opcion) {
                case 1:
                new RegistroCustomerorder().registro(sc, customerOrderUseCase);
                    break;
                case 2:
                new BuscarCustomerorder().buscar(sc, customerOrderUseCase);
                    break;
                case 3:
                    customerOrderUseCase.getAllCustomerOrders();
                    break;
                case 4:
                    new ActualizarCustomerorder().actualizar(sc, customerOrderUseCase);
                    break;
                case 5:
                    new EliminarCustomerOrder().eliminar(sc, customerOrderUseCase); 
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
