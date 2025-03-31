package com.sportscenter.application.ui.Admin;

import java.util.Scanner;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.adapter.validations.ValidationInt;
import com.sportscenter.application.usecase.category.*;

public class CategoryUI {

    public static void mostrarMenu(Scanner sc, CategoryUseCase categoryUseCase) {
        int opcion;
        ConsoleUtils.clear();
        do {
            String menu = """
                    \n╔════════════════════════════╗
                    ║      MENÚ CATEGORÍAS       ║
                    ╠════════════════════════════╣
                    ║ 1. Registrar categoría     ║
                    ║ 2. Buscar categoría por ID ║
                    ║ 3. Listar todas            ║
                    ║ 4. Actualizar categoría    ║
                    ║ 5. Eliminar categoría      ║
                    ║ 6. Salir                   ║
                    ╚════════════════════════════╝
                    Seleccione una opción:""";
            ConsoleUtils.clear();
            System.out.print(menu);

            ValidationInt.validate(sc);
            opcion = sc.nextInt();

            sc.nextLine();

            switch (opcion) {
                case 1:
                    new RegisterCategory().Register(sc, categoryUseCase);
                    break;
                case 2:
                    new SearchCategory().Search(sc, categoryUseCase);
                    break;
                case 3:
                    categoryUseCase.getAllCategories();
                    break;
                case 4:
                    new UpdateCategory().Update(sc, categoryUseCase);
                    break;
                case 5:
                    new DeleteCategory().Delete(sc, categoryUseCase);
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
