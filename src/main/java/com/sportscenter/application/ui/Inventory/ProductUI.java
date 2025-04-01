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
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║   No hay productos registrados  ║");
            System.out.println("╚════════════════════════════════╝");
            return;
        }
    
        System.out.println("\n╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                            LISTADO DE PRODUCTOS                                                     ║");
        System.out.println("╠════╦══════════════════════╦════════════════════════════╦══════════╦════════╦══════════════╦══════════════╦══════════╗");
        System.out.println("║ ID ║ Nombre               ║ Descripción                ║ Precio   ║ Stock  ║ Categoría    ║ Proveedor    ║ Color    ║");
        System.out.println("╠════╬══════════════════════╬════════════════════════════╬══════════╬════════╬══════════════╬══════════════╬══════════╣");
    
        for (Product p : productos) {
            System.out.printf(
                "║ %-2d ║ %-20s ║ %-26s ║ $%-7.2f ║ %-6d ║ %-12s ║ %-12s ║ %-8s ║%n",
                p.getId(),
                truncate(p.getName(), 20),
                truncate(p.getDescription(), 26),
                p.getUnitPrice(),
                p.getCurrentStock(),
                truncate(p.getCategoryName(), 12),
                truncate(p.getSupplierName(), 12),
                truncate(p.getColorName(), 8)
            );
        }
        System.out.println("╚════╩══════════════════════╩════════════════════════════╩══════════╩════════╩══════════════╩══════════════╩══════════╝");
        System.out.println("║ Total de productos: " + productos.size()+"    ║");
        System.out.println("╚═══════════════════════════╝");
    }
    
    private String truncate(String value, int maxLength) {
        if (value == null) return "N/A";
        return value.length() > maxLength ? value.substring(0, maxLength - 3) + "..." : value;
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
                        ║ 6. Volver                   ║
                        ╚═════════════════════════════╝
                        Seleccione una opción:""";
            System.out.print(menu);

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> this.agregarProducto();
                case 2 -> this.SearchProductoPorId();
                case 3 -> this.ListProductos();
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