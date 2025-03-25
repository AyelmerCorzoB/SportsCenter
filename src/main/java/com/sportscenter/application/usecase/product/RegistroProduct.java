// package com.sportscenter.application.usecase.product;

// import java.util.Scanner;

// import com.sportscenter.adapter.validations.ValidationDouble;
// import com.sportscenter.adapter.validations.ValidationInt;
// import com.sportscenter.adapter.validations.ValidationString;

// public class RegistroProduct {
//     public void registro(Scanner sc, ProductUseCase productUseCase) {
//         System.out.println("\n=== REGISTRO DE PRODUCTO ===");

//         System.out.print("Nombre: ");
//         ValidationString.validate(sc);
//         String name = sc.nextLine();

//         System.out.print("Descripción (opcional): ");
//         String description = sc.nextLine();

//         System.out.print("Precio unitario: ");
//         ValidationDouble.validate(sc);
//         double unitPrice = sc.nextDouble();
//         sc.nextLine();

//         System.out.println("Tallas disponibles: S, M, L, XL");
//         System.out.print("Talla: ");
//         String size = sc.nextLine().toUpperCase();

//         System.out.print("Stock actual: ");
//         ValidationInt.validate(sc);
//         int currentStock = sc.nextInt();
//         sc.nextLine();

//         System.out.print("Stock mínimo: ");
//         ValidationInt.validate(sc);
//         int minimumStock = sc.nextInt();
//         sc.nextLine();

//         System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
//         String entryDate = sc.nextLine();

//         System.out.print("ID de categoría: ");
//         ValidationInt.validate(sc);
//         int categoryId = sc.nextInt();
//         sc.nextLine();

//         System.out.print("ID de proveedor: ");
//         ValidationInt.validate(sc);
//         int supplierId = sc.nextInt();
//         sc.nextLine();

//         System.out.print("ID de color: ");
//         ValidationInt.validate(sc);
//         int colorId = sc.nextInt();
//         sc.nextLine();

//         productUseCase.registerProduct(name, description, unitPrice, size, currentStock, minimumStock, entryDate, categoryId, supplierId, colorId, colorId);
//         System.out.println("✅ Producto registrado exitosamente.");
//     }
// }