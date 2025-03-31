# Documentación - SportsCenter


#### Descripción General

Este proyecto es un sistema de gestión para una tienda de ropa deportiva desarrollado en **Java** con **Maven**. El sistema permite la administración de usuarios, productos, ventas y proveedores mediante un sistema de roles.

### Tecnologías Utilizadas
- **Lenguaje:** Java ☕
- **Gestor de dependencias:** Maven 🪶
- **Base de datos:** MySQL 🐘
- **Arquitectura:** Consola con menús interactivos

## **Requerimientos Funcionales**

**- Gestión de Inventario**

·     El sistema permitirá registrar, editar, consultar y eliminar productos.

·     Cada producto debe tener talla, color, categoría, precio y stock.

·     El sistema mostrará alertas cuando el stock esté por debajo del mínimo.

**- Gestión de Clientes**

·     Registrar clientes con nombre, tipo (individual o empresa), documento, correo y teléfono.

·     Consultar historial de Purchases de cada cliente.

**- Gestión de Ventas**

·     Registrar una venta asociando uno o varios productos y un cliente.

·     Calcular el total con descuentos y aplicar diferentes métodos de pago.

·     Generar una factura por cada venta completada.

**- Gestión de Empleados**

·     Registrar usuarios con roles (ADMIN, CAJERO, INVENTARIO).

·     Controlar el acceso al sistema según el rol.

**- Reportes**

·     Generar reportes de ventas diarias, semanales, mensuales.

·     Listar productos más vendidos.

·     Mostrar clientes con mayor número de Purchases.



## **CASOS DE USO**

**Gestión de Inventario**

1. **Registrar un nuevo producto**: El sistema permite agregar un nuevo producto con detalles como talla, color, categoría, precio y stock.
2. **Editar un producto existente**: El sistema permite modificar la información de un producto registrado.
3. **Consultar información de un producto**: El sistema muestra los detalles de un producto específico.
4. **Eliminar un producto**: El sistema permite eliminar un producto del inventario.
5. **Mostrar alertas de stock bajo**: El sistema notifica cuando el stock de un producto está por debajo del mínimo establecido.
6. **Actualizar stock manualmente**: El sistema permite ajustar manualmente la cantidad de stock de un producto.

------

**Gestión de Clientes**

1. **Registrar un nuevo cliente**: El sistema permite agregar un cliente con nombre, tipo (individual o empresa), documento, correo y teléfono.
2. **Editar información de un cliente**: El sistema permite modificar los datos de un cliente registrado.
3. **Consultar información de un cliente**: El sistema muestra los detalles de un cliente específico.
4. **Eliminar un cliente**: El sistema permite eliminar un cliente del registro.
5. **Consultar historial de Purchases de un cliente**: El sistema muestra todas las Purchases realizadas por un cliente.

------

**Gestión de Ventas**

1. **Registrar una nueva venta**: El sistema permite registrar una venta asociando uno o varios productos y un cliente.
2. **Aplicar descuentos a una venta**: El sistema permite aplicar descuentos al total de la venta.
3. **Seleccionar método de pago**: El sistema permite elegir entre diferentes métodos de pago (efectivo, tarjeta, transferencia, etc.).
4. **Generar factura**: El sistema genera una factura con los detalles de la venta.
5. **Cancelar una venta**: El sistema permite cancelar una venta registrada.

------

**Gestión de Pedidos**

1. **Registrar un nuevo pedido a proveedor**: El sistema permite crear un pedido con productos y cantidades específicas.
2. **Cambiar estado de un pedido**: El sistema permite actualizar el estado de un pedido (pendiente, recibido, cancelado).
3. **Actualizar inventario al recibir un pedido**: El sistema actualiza automáticamente el stock de los productos cuando un pedido es recibido.
4. **Consultar detalles de un pedido**: El sistema muestra los detalles de un pedido específico.

------

**Gestión de Empleados**

1. **Registrar un nuevo empleado**: El sistema permite agregar un empleado con nombre, documento, correo, teléfono y rol (ADMIN, CAJERO, INVENTARIO).
2. **Editar información de un empleado**: El sistema permite modificar los datos de un empleado registrado.
3. **Eliminar  un empleado**: El sistema permite eliminar un empleado del registro.
4. **Controlar acceso al sistema**: El sistema restringe las funcionalidades según el rol del empleado (ADMIN, CAJERO, INVENTARIO).

------

### **Reportes**

1. **Generar reporte de ventas:** El sistema genera un reporte detallado de todas las ventas registradas, incluyendo la información del cliente, método de pago, descuento aplicado y total de la compra.
2. **Generar reporte de ingresos:** El sistema genera un reporte con el total de ingresos generados por las ventas, desglosando cada transacción con su fecha, descuento aplicado y monto total.
3. **Generar reporte de productos vendidos en cada venta:** El sistema genera un reporte detallado de cada venta realizada, incluyendo los productos vendidos, su cantidad, precio unitario y subtotal.

------



## BASE DE DATOS RELACIONADA A LA CUARTA FORMA

![sportscenter](C:\Users\ALAN\sportscenter.png)


## DIAGRAMA DE CLASES
![sportscenter](C:\Users\ALAN\Pictures\Screenshots\Captura de pantalla 2025-03-30 144830.png)
## Roles y Funcionalidades

### 1. **ADMIN (Administrador)**
El administrador tiene acceso total al sistema y puede gestionar:
- Usuarios
- Categorías de productos
- Colores
- Pedidos
- Tipos de clientes
- Clientes
- Facturas
- Productos
- Proveedores
- Ventas
- Etc

#### Opciones del Administrador
```
1. Registrar nuevo usuario administrativo
2. Listar todos los usuarios
3. Panel de control
4. Cerrar sesión
```

**Gestión avanzada(Panel de control):**
```
1. Gestión de Categorías
2. Gestión de Colores
3. Gestión de Pedidos
4. Gestión de Tipos de Cliente
5. Gestión de Clientes

6. Siguiente página
7. Volver al menú principal
```
```
1. Gestión de Facturas
2. Gestión de Productos
3. Gestión de Proveedores
4. Gestión de Usuarios
5. Gestión de Ventas

6. Página anterior
7. Volver al menú principal
```

---

### 2. **INVENTORY (Inventario)**
Este rol gestiona los métodos de pago.

#### Opciones del Inventario
```
1. Listar todos los productos
2. Agregar nuevo producto
3. Actualizar producto
4. Eliminar producto
5. Buscar producto por ID
```

---

### 3. **CASHIER (Cajero)**
El cajero se encarga de procesar ventas y gestionar facturas y reportes.

#### Opciones del Cajero
```
1. Procesar nueva venta
2. Gestión de facturas
3. Gestión de ventas
4. Generar reportes
5. Cerrar sesión
```

**Gestión de Facturas:**
```
1. Listar todas las facturas
2. Buscar factura por ID
3. Generar nueva factura
4. Volver al menú principal
```

**Gestión de Ventas:**
```
1. Listar todas las ventas
2. Buscar venta por ID
3. Ver detalles de venta
4. Volver al menú principal
```

**Generación de Reportes:**
```
1. Ventas por período
2. Productos más vendidos
3. Ingresos
4. Volver al menú principal
```

---

### 4. **CUSTOMER (Cliente)**
El cliente tiene un perfil donde puede:
```
1. Cambiar contraseña
2. Volver al menú principal
```

---

## Base de Datos
La base de datos se llama **sportscenter** y contiene las siguientes tablas principales:

### **Tabla `User` (Usuarios)**
```sql
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'CASHIER', 'INVENTORY', 'CONSUMER') NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL,
    CHECK (LENGTH(username) >= 3),
    CHECK (LENGTH(password) >= 8)
);
```

### **Tabla `Product` (Productos)**
```sql
CREATE TABLE Product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    unit_price DECIMAL(10,2) CHECK (unit_price >= 0),
    size ENUM('S', 'M', 'L', 'XL'),
    current_stock INT CHECK (current_stock >= 0),
    minimum_stock INT CHECK (minimum_stock >= 0),
    entry_date DATE NOT NULL,
    category_id INT,
    supplier_id INT,
    color_id INT,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES Category(id) ON DELETE SET NULL,
    FOREIGN KEY (supplier_id) REFERENCES Supplier(id) ON DELETE SET NULL,
    FOREIGN KEY (color_id) REFERENCES Color(id) ON DELETE SET NULL,
    FOREIGN KEY (created_by) REFERENCES User(id) ON DELETE SET NULL,
    CHECK (LENGTH(name) > 0)
);
```

---

## Credenciales de Acceso
| Rol        | Usuario    | Contraseña    |
|------------|-----------|--------------|
| ADMIN      | admin     | admin123     |
| INVENTORY  | inventario | inventario123 |
| CASHIER    | cajero    | cajero123    |
| CUSTOMER   | cliente   | cliente123   |

---
