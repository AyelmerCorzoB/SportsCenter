package com.sportscenter.application.ui.Inventory;

import java.util.List;
import java.util.Scanner;
import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.application.usecase.product.*;
import com.sportscenter.domain.entities.Product;
import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.service.UserService;

public class ProductUI {
    private final ProductUseCase productUseCase;
    private final Scanner scanner;
    private final UserService userService;
    private User currentUser;

    public ProductUI(Scanner scanner, ProductUseCase productUseCase, UserService userService) {
        this.scanner = scanner;
        this.productUseCase = productUseCase;
        this.userService = userService;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void agregarProducto() {
        new RegisterProduct().Register(scanner, productUseCase, this.currentUser);
    }

    public void UpdateProducto() {
        new UpdateProduct().Update(scanner, productUseCase, currentUser);
    }

    public void SearchProductoPorId() {
        new SearchProduct().Search(scanner, productUseCase);
    }

    public void DeleteProducto() {
        new DeleteProduct().Delete(scanner, productUseCase);
    }

    public void ListProductos() {
        List<Product> productos = productUseCase.getAllProducts();

        if (productos.isEmpty()) {
            System.out.println("\nNo hay productos registrados.");
            return;
        }

        System.out.println("\nLISTADO DE PRODUCTOS");
        System.out.println(
                "+----------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-5s | %-20s | %-30s | %-10s | %-8s | %-15s | %-15s | %-10s |\n",
                "ID", "Nombre", "Descripción", "Precio", "Stock", "Categoría", "Proveedor", "Color");
        System.out.println(
                "+----------------------------------------------------------------------------------------------------------------+");

        for (Product p : productos) {
            System.out.printf("| %-5d | %-20s | %-30s | $%-9.2f | %-8d | %-15s | %-15s | %-10s |\n",
                    p.getId(),
                    p.getName(),
                    p.getDescription() != null
                            ? (p.getDescription().length() > 30 ? p.getDescription().substring(0, 27) + "..."
                                    : p.getDescription())
                            : "",
                    p.getUnitPrice(),
                    p.getCurrentStock(),
                    p.getCategoryName(),
                    p.getSupplierName(),
                    p.getColorName());
        }
        System.out.println(
                "+----------------------------------------------------------------------------------------------------------------+");
        System.out.println("Total de productos: " + productos.size());
    }

    public void mostrarMenu() {
        boolean volver = false;
        
        while (!volver) {
            ConsoleUtils.clear();
            String menu = """
                        \n╔═════════════════════════════╗
                        ║       MENÚ PRODUCTOS        ║
                        ╠═════════════════════════════╣
                        ║ 1. Registrar PRODUCTOS      ║
                        ║ 2. Buscar PRODUCTOS por ID  ║
                        ║ 3. Listar todos             ║
                        ║ 4. Actualizar PRODUCTOS     ║
                        ║ 5. Eliminar PRODUCTOS       ║
                        ║ 6. Salir                    ║
                        ╚═════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> this.ListProductos();
                case 2 -> this.agregarProducto();
                case 3 -> this.SearchProductoPorId();
                case 4 -> this.UpdateProducto();
                case 5 -> this.DeleteProducto();
                case 6 -> volver = true;
                default -> System.out.println("Opción inválida");
            }

            if (opcion != 6) {
                ConsoleUtils.pressEnterToContinue(scanner);
            }
        }
    }
}