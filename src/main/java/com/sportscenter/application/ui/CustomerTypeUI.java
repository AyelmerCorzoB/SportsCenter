package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.customer_type.ActualizarCustomerType;
import com.sportscenter.application.usecase.customer_type.BuscarCustomerType;
import com.sportscenter.application.usecase.customer_type.CustomerTypeUseCase;
import com.sportscenter.application.usecase.customer_type.EliminarCustomerType;
import com.sportscenter.application.usecase.customer_type.RegistroCustomerType;

public class CustomerTypeUI {

    public static void manejarMenuCustomerType(Scanner sc, CustomerTypeUseCase customerTypeUseCase) {
        int opcion;
        do {
            String menuCustomerType = """
                    ******** CustomerType ********
                    1. Registrar CustomerType
                    2. Obtener customerType por ID
                    3. Listar todos los customerType
                    4. Actualizar un customerType
                    5. Eliminar un customerType
                    6. Salir...
                    Seleccione una opción:""";
            System.out.print(menuCustomerType);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();

            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegistroCustomerType().registro(sc, customerTypeUseCase);
                    break;
                case 2:
                    new BuscarCustomerType().buscar(sc, customerTypeUseCase);
                    break;
                case 3:
                    customerTypeUseCase.getAllCustomerTypes();
                    break;
                case 4:
                new ActualizarCustomerType().actualizar(sc, customerTypeUseCase);
                    break;
                case 5:
                    new EliminarCustomerType().eliminar(sc, customerTypeUseCase);
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
