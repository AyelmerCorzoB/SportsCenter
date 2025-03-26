package com.sportscenter.adapter.menus;

import com.sportscenter.adapter.global.ConsoleUtils;

public class MenuClientes {
    public static void mostrarMenu() {
        ConsoleUtils.clear();
        System.out.println("\n======================================");
        System.out.println("          MENÚ PRINCIPAL               ");
        System.out.println("======================================");
        System.out.println("1. Catálogo de Productos");
        System.out.println("2. Mis Compras");
        System.out.println("3. Facturación");
        System.out.println("4. Mi Perfil");
        System.out.println("5. Cerrar Sesión");
        System.out.print("Seleccione una opción: ");
    }
}