package com.sportscenter.adapter.menus;

import com.sportscenter.adapter.global.ConsoleUtils;

public class MenuCajero {

    public static void mostrarMenu() {
        ConsoleUtils.clear();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║        MENÚ PRINCIPAL        ║");
        System.out.println("║           CAJERO             ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1. Procesar nueva venta      ║");
        System.out.println("║ 2. Gestión de facturas       ║");
        System.out.println("║ 3. Gestión de ventas         ║");
        System.out.println("║ 4. Generar reportes          ║");
        System.out.println("║ 5. Cerrar sesión             ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostrarSubmenuFacturas() {
        ConsoleUtils.clear();
        System.out.println("");
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║      GESTIÓN DE FACTURAS     ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1. Listar todas las facturas ║");
        System.out.println("║ 2. Search factura por ID     ║");
        System.out.println("║ 3. Generar nueva factura     ║");
        System.out.println("║ 4. Volver al menú principal  ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostrarSubmenuVentas() {
        ConsoleUtils.clear();
        System.out.println("");
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║       GESTIÓN DE VENTAS      ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1. Listar todas las ventas   ║");
        System.out.println("║ 2. Search venta por ID       ║");
        System.out.println("║ 3. Ver detalles de venta     ║");
        System.out.println("║ 4. Volver al menú principal  ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostrarSubmenuReportes() {
        ConsoleUtils.clear();
        System.out.println("");
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║    GENERACIÓN DE REPORTES    ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1. Ventas por período        ║");
        System.out.println("║ 2. Productos más vendidos    ║");
        System.out.println("║ 3. Ingresos                  ║");
        System.out.println("║ 4. Volver al menú principal  ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostrarEncabezadoProcesarVenta() {
        ConsoleUtils.clear();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     PROCESAR NUEVA VENTA     ║");
        System.out.println("╚══════════════════════════════╝");
    }

    public static void mostrarEncabezadoFacturas() {
        ConsoleUtils.clear();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║       LISTA DE FACTURAS      ║");
        System.out.println("╚══════════════════════════════╝");
    }

    public static void mostrarEncabezadoVentas() {
        ConsoleUtils.clear();
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║        LISTA DE VENTAS       ║");
        System.out.println("╚══════════════════════════════╝");
    }
}