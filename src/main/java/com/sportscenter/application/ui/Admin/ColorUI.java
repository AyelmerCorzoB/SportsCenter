package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.color.*;

public class ColorUI {
    public static void mostrarMenu(Scanner sc, ColorUseCase colorUseCase) {
        int opcion;

        do {

            String menuColor = """
                    \n╔═══════════════════════════╗
                    ║        MENÚ COLOR         ║
                    ╠═══════════════════════════╣
                    ║ 1. Registrar Color        ║
                    ║ 2. Buscar color por ID    ║
                    ║ 3. Listar todos           ║
                    ║ 4. Actualizar color       ║
                    ║ 5. Eliminar color         ║
                    ║ 6. Volver                 ║
                    ╚═══════════════════════════╝
                    Seleccione una opción:""";
            ConsoleUtils.clear();
            System.out.print(menuColor);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();

            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterColor().Register(sc, colorUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 2:
                    new SearchColor().Search(sc, colorUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 3:
                    ConsoleUtils.clear();
                    colorUseCase.getAllColors();
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 4:
                    new UpdateColor().Update(sc, colorUseCase);
                    ConsoleUtils.pressEnterToContinue(sc);
                    break;
                case 5:
                    new DeleteColor().Delete(sc, colorUseCase);
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