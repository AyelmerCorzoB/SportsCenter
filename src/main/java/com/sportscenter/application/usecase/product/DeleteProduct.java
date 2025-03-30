package com.sportscenter.application.usecase.product;

import java.util.Scanner;

import com.sportscenter.adapter.validations.ValidationInt;

public class DeleteProduct {
    public void Delete(Scanner sc, ProductUseCase productUseCase) {
        System.out.println("\n=== ELIMINAR PRODUCTO ===");
        
        System.out.print("ID del producto a eliminar: ");
        ValidationInt.validate(sc);
        int id = sc.nextInt();
        sc.nextLine();
        
        productUseCase.deleteProduct(id);
        System.out.println("🚀 Producto eliminado exitosamente.");
    }
}