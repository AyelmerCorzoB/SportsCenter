package com.sportscenter.application.ui;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.color.ActualizarColor;
import com.sportscenter.application.usecase.color.BuscarColor;
import com.sportscenter.application.usecase.color.ColorUseCase;
import com.sportscenter.application.usecase.color.EliminarColor;
import com.sportscenter.application.usecase.color.RegistroColor;

public class ColorUI {
    public static void manejarMenuColor(Scanner sc, ColorUseCase colorUseCase) {
        int opcion;
        do {
            String menuColor = """
                    ******** Color ********
                    1. Registrar Color
                    2. Obtener color por ID
                    3. Listar todos los color
                    4. Actualizar un color
                    5. Eliminar un color
                    6. Salir...
                    Seleccione una opción:""";
            System.out.print(menuColor);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();

            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegistroColor().registro(sc, colorUseCase);
                    break;
                case 2:
                    new BuscarColor().buscar(sc, colorUseCase);    
                    break;
                case 3:
                    colorUseCase.getAllColors();
                    break;
                case 4:
                new ActualizarColor().actualizar(sc, colorUseCase);
                    break;
                case 5:
                    new EliminarColor().eliminar(sc, colorUseCase);
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
