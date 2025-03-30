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

**- Gestión de Pedidos**

·     Registrar un pedido a proveedor con productos y cantidades.

·     Cambiar el estado del pedido según su progreso (pendiente, recibido, cancelado).

·     Al recibir el pedido, actualizar automáticamente el inventario.

**- Gestión de Empleados**

·     Registrar usuarios con roles (ADMIN, CAJERO, INVENTARIO).

·     Controlar el acceso al sistema según el rol.

**- Reportes**

·     Generar reportes de ventas diarias, semanales, mensuales.

·     Listar productos más vendidos.

·     Mostrar clientes con mayor número de Purchases.

·     Generar reporte de productos con bajo inventario.



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

**Reportes**

1. **Generar  reporte de ventas diarias**: El sistema genera un reporte de todas las ventas realizadas en un día.
2. **Generar  reporte de ventas semanales**: El sistema genera un reporte de todas las ventas realizadas en una semana.
3. **Generar reporte de ventas mensuales**: El sistema genera un reporte de todas las ventas realizadas en un mes.
4. **Listar  productos más vendidos**: El sistema muestra un listado de los productos con mayor cantidad de ventas.
5. **Mostrar clientes con mayor número de Purchases**: El sistema muestra un listado de los clientes que más Purchases han realizado.
6. **Generar reporte de productos con bajo inventario**: El sistema genera un reporte de los productos que tienen un stock por debajo del mínimo establecido.

------

**Casos de uso adicionales**

1. **Buscar un producto por nombre o categoría**: El sistema permite buscar productos utilizando filtros como nombre o categoría.
2. **Buscar un cliente por nombre o documento**: El sistema permite buscar clientes utilizando filtros como nombre o documento.
3. **Exportar reportes a formato PDF o Excel**: El sistema permite exportar los reportes generados en formatos como PDF o Excel.
4. **Notificar vencimiento de productos**: El sistema notifica cuando un producto está próximo a vencer (si aplica).
5. **Registrar devoluciones**: El sistema permite registrar devoluciones de productos y ajustar el inventario.
6. **Consultar ventas por empleado**: El sistema muestra un reporte de las ventas realizadas por cada empleado.
7. **Generar reporte de ganancias**: El sistema genera un reporte de las ganancias obtenidas en un período específico.
8. **Consultar proveedores**: El sistema muestra un listado de los proveedores registrados.
9. **Registrar un nuevo proveedor**: El sistema permite agregar un proveedor con sus datos de contacto.
10. **Editar información de un proveedor**: El sistema permite modificar los datos de un proveedor registrado.

 

## BASE DE DATOS 


````sql
-- Tablas principales normalizadas sin email:

CREATE DATABASE IF NOT EXISTS sportscenter;
USE sportscenter;

-- Tabla User (sin email)
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'CASHIER', 'INVENTORY','CONSUMER') NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL,
    CHECK (LENGTH(username) >= 3),
    CHECK (LENGTH(password) >= 8)
);

-- Tabla Category
CREATE TABLE Category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES User(id) ON DELETE SET NULL,
    CHECK (LENGTH(name) > 0)
);

-- Tabla Color
CREATE TABLE Color (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    hex_code VARCHAR(7),
    CHECK (LENGTH(name) > 0)
);

-- Tabla Supplier
CREATE TABLE Supplier (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    tax_id VARCHAR(20) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    created_by INT,
    FOREIGN KEY (created_by) REFERENCES User(id) ON DELETE SET NULL,
    CHECK (LENGTH(name) > 2),
    CHECK (LENGTH(tax_id) > 0)
);

-- Tabla Product
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

-- Tabla CustomerType
CREATE TABLE CustomerType (
    id INT PRIMARY KEY AUTO_INCREMENT,
    type_name ENUM('INDIVIDUAL', 'COMPANY') NOT NULL UNIQUE,
    description TEXT
);

-- Tabla Customer (sin email)
CREATE TABLE Customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_type_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    identity_document VARCHAR(20) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    registration_date DATE NOT NULL,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_type_id) REFERENCES CustomerType(id),
    FOREIGN KEY (created_by) REFERENCES User(id) ON DELETE SET NULL,
    CHECK (LENGTH(name) > 0),
    CHECK (LENGTH(identity_document) > 0)
);

-- Tabla Employee
CREATE TABLE Employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    position ENUM('ADMINISTRATOR', 'CASHIER', 'INVENTORY') NOT NULL,
    phone VARCHAR(20) NOT NULL,
    user_id INT UNIQUE,
    hire_date DATE NOT NULL,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE SET NULL,
    FOREIGN KEY (created_by) REFERENCES User(id) ON DELETE SET NULL,
    CHECK (LENGTH(name) > 0)
);

-- Tabla PurchaseStatus
CREATE TABLE PurchaseStatus (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status_name ENUM('PENDING', 'RECEIVED', 'CANCELED') NOT NULL UNIQUE,
    description TEXT
);

-- Tabla Purchase
CREATE TABLE Purchase (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    supplier_id INT NOT NULL,
    status_id INT NOT NULL,
    employee_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (supplier_id) REFERENCES Supplier(id) ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES PurchaseStatus(id),
    FOREIGN KEY (employee_id) REFERENCES Employee(id) ON DELETE CASCADE
);

-- Tabla PurchaseDetail
CREATE TABLE PurchaseDetail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    purchase_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT CHECK (quantity > 0),
    unit_price DECIMAL(10,2) CHECK (unit_price >= 0),
    subtotal DECIMAL(10,2) GENERATED ALWAYS AS (quantity * unit_price) STORED,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (purchase_id) REFERENCES Purchase(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Product(id) ON DELETE CASCADE
);

-- Tabla PaymentMethod
CREATE TABLE PaymentMethod (
    id INT PRIMARY KEY AUTO_INCREMENT,
    method_name ENUM('CASH', 'CARD', 'TRANSFER') NOT NULL UNIQUE,
    description TEXT
);

-- Tabla Sale
CREATE TABLE Sale (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    sale_date DATE NOT NULL,
    payment_method_id INT NOT NULL,
    discount DECIMAL(10,2) CHECK (discount >= 0),
    total DECIMAL(10,2) CHECK (total >= 0),
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customer(id) ON DELETE CASCADE,
    FOREIGN KEY (payment_method_id) REFERENCES PaymentMethod(id),
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

-- Tabla SaleDetail
CREATE TABLE SaleDetail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sale_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT CHECK (quantity > 0),
    unit_price DECIMAL(10,2) CHECK (unit_price >= 0),
    subtotal DECIMAL(10,2) GENERATED ALWAYS AS (quantity * unit_price) STORED,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sale_id) REFERENCES Sale(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Product(id) ON DELETE CASCADE
);

-- Tabla OrderStatus
CREATE TABLE OrderStatus (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status_name ENUM('IN PROCESS', 'DELIVERED', 'CANCELED') NOT NULL UNIQUE,
    description TEXT
);

-- Tabla CustomerOrder
CREATE TABLE CustomerOrder (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    order_date DATE NOT NULL,
    status_id INT NOT NULL,
    total DECIMAL(10,2) CHECK (total >= 0),
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES Customer(id) ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES OrderStatus(id),
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

-- Tabla Invoice
CREATE TABLE Invoice (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sale_id INT NOT NULL,
    invoice_number VARCHAR(50) NOT NULL UNIQUE,
    issue_date DATE NOT NULL,
    total_amount DECIMAL(10,2) CHECK (total_amount >= 0),
    taxes DECIMAL(10,2) CHECK (taxes >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sale_id) REFERENCES Sale(id) ON DELETE CASCADE
);

-- Tabla ReportType
CREATE TABLE ReportType (
    id INT PRIMARY KEY AUTO_INCREMENT,
    type_name ENUM('SALES', 'INVENTORY', 'CUSTOMERS') NOT NULL UNIQUE,
    description TEXT
);

-- Tabla Report
CREATE TABLE Report (
    id INT PRIMARY KEY AUTO_INCREMENT,
    report_type_id INT NOT NULL,
    generation_date DATE NOT NULL,
    user_id INT NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    parameters JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (report_type_id) REFERENCES ReportType(id),
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

-- Tabla AuditLog
CREATE TABLE AuditLog (
    id INT PRIMARY KEY AUTO_INCREMENT,
    table_name VARCHAR(50) NOT NULL,
    record_id INT NOT NULL,
    action ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
    old_values JSON,
    new_values JSON,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    changed_by INT,
    FOREIGN KEY (changed_by) REFERENCES User(id) ON DELETE SET NULL
);
-- 1. Insertar usuarios primero (son referenciados en muchas tablas)
INSERT INTO User (username, password, role, active, last_login) VALUES 
('admin', 'admin123', 'ADMIN', TRUE, '2023-01-15 09:30:00'),
('cajero', 'cajero123', 'CASHIER', TRUE, '2023-01-16 10:15:00'),
('inventario', 'inventario123', 'INVENTORY', TRUE, '2023-01-17 11:20:00'),
('cliente', 'cliente123', 'CONSUMER', TRUE, '2023-01-18 14:45:00'),
('admin2', 'Admin5678', 'ADMIN', TRUE, '2023-01-19 08:10:00'),
('cajero2', 'Cajero456', 'CASHIER', TRUE, '2023-01-20 13:25:00'),
('inventario2', 'Invent456', 'INVENTORY', TRUE, '2023-01-21 16:30:00'),
('cliente2', 'Cliente456', 'CONSUMER', TRUE, '2023-01-22 17:15:00'),
('cajero3', 'Cajero789', 'CASHIER', FALSE, '2023-01-23 09:40:00'),
('cliente3', 'Cliente789', 'CONSUMER', TRUE, '2023-01-24 12:50:00');

-- 2. Insertar tipos de cliente
INSERT INTO CustomerType (type_name, description) VALUES 
('INDIVIDUAL', 'Clientes particulares'),
('COMPANY', 'Empresas o organizaciones');

-- 3. Insertar clientes (referencian CustomerType)
INSERT INTO Customer (customer_type_id, name, identity_document, phone, address, registration_date, created_by) VALUES 
(1, 'Juan Pérez', '12345678A', '611223344', 'Calle Primavera 1', '2023-01-10', 1),
(1, 'María García', '87654321B', '622334455', 'Avenida Verano 2', '2023-01-11', 1),
(2, 'Gimnasio Active', 'A12345678', '633445566', 'Boulevard Otoño 3', '2023-01-12', 1),
(1, 'Carlos López', '23456789C', '644556677', 'Calle Invierno 4', '2023-01-13', 1),
(2, 'Club Deportivo', 'B23456789', '655667788', 'Avenida Estación 5', '2023-01-14', 1),
(1, 'Ana Martínez', '34567891D', '666778899', 'Boulevard Sol 6', '2023-01-15', 1),
(1, 'Luis Fernández', '45678912E', '677889900', 'Calle Luna 7', '2023-01-16', 1),
(2, 'Escuela Natación', 'C34567891', '688990011', 'Avenida Mar 8', '2023-01-17', 1),
(1, 'Sofía Rodríguez', '56789123F', '699001122', 'Boulevard Río 9', '2023-01-18', 1),
(2, 'Equipo Ciclismo', 'D45678912', '600112233', 'Calle Montaña 10', '2023-01-19', 1);

-- 4. Insertar empleados (referencian User)
INSERT INTO Employee (name, position, phone, user_id, hire_date, created_by) VALUES 
('Pedro Sánchez', 'ADMINISTRATOR', '611223344', 1, '2022-01-10', 1),
('Laura Gómez', 'CASHIER', '622334455', 2, '2022-02-15', 1),
('Miguel Torres', 'INVENTORY', '633445566', 3, '2022-03-20', 1),
('Elena Ruiz', 'ADMINISTRATOR', '644556677', 5, '2022-04-25', 1),
('David Jiménez', 'CASHIER', '655667788', 6, '2022-05-30', 1),
('Sara Castro', 'INVENTORY', '666778899', 7, '2022-06-05', 1),
('Jorge Navarro', 'CASHIER', '677889900', 9, '2022-07-10', 1),
('Lucía Méndez', 'ADMINISTRATOR', '688990011', NULL, '2022-08-15', 1),
('Pablo Ortega', 'CASHIER', '699001122', NULL, '2022-09-20', 1),
('Marta Serrano', 'INVENTORY', '600112233', NULL, '2022-10-25', 1);

-- 5. Insertar categorías
INSERT INTO Category (name, description, created_by) VALUES 
('Fútbol', 'Artículos para fútbol', 1),
('Baloncesto', 'Artículos para baloncesto', 1),
('Tenis', 'Artículos para tenis', 1),
('Natación', 'Artículos para natación', 1),
('Atletismo', 'Artículos para atletismo', 1),
('Ciclismo', 'Artículos para ciclismo', 1),
('Gimnasio', 'Artículos para gimnasio', 1),
('Running', 'Artículos para correr', 1),
('Yoga', 'Artículos para yoga', 1),
('Senderismo', 'Artículos para senderismo', 1);

-- 6. Insertar colores
INSERT INTO Color (name, hex_code) VALUES 
('Rojo', '#FF0000'),
('Azul', '#0000FF'),
('Verde', '#00FF00'),
('Amarillo', '#FFFF00'),
('Negro', '#000000'),
('Blanco', '#FFFFFF'),
('Gris', '#808080'),
('Naranja', '#FFA500'),
('Morado', '#800080'),
('Rosa', '#FFC0CB');

-- 7. Insertar proveedores
INSERT INTO Supplier (name, phone, address, tax_id, created_by) VALUES 
('Deportes S.A.', '123456789', 'Calle Falsa 123', 'SA12345678', 1),
('Suministros Deportivos', '987654321', 'Avenida Siempre Viva 456', 'SD87654321', 1),
('Equipamiento Plus', '555123456', 'Boulevard Deportivo 789', 'EP55512345', 1),
('Proveedor Total', '555987654', 'Carrera Atleta 321', 'PT55598765', 1),
('Distribuidora Sport', '222333444', 'Calle Campeón 654', 'DS22233344', 1),
('Suministros Elite', '666777888', 'Avenida Olimpia 987', 'SE66677788', 1),
('Deportes Unidos', '111222333', 'Boulevard Victoria 159', 'DU11122233', 1),
('Proveedora Activa', '444555666', 'Calle Resistencia 753', 'PA44455566', 1),
('Suministros Rápidos', '777888999', 'Avenida Velocidad 951', 'SR77788899', 1),
('Equipo Total', '333444555', 'Carrera Fuerza 357', 'ET33344455', 1);

-- 8. Insertar productos (referencian Category, Supplier, Color)
INSERT INTO Product (name, description, unit_price, size, current_stock, minimum_stock, entry_date, category_id, supplier_id, color_id, created_by) VALUES 
('Balón de Fútbol', 'Balón oficial tamaño 5', 29.99, 'M', 50, 10, '2023-01-10', 1, 1, 1, 1),
('Camiseta de Baloncesto', 'Camiseta de algodón para baloncesto', 24.99, 'L', 30, 5, '2023-01-11', 2, 2, 2, 1),
('Raqueta de Tenis', 'Raqueta profesional', 89.99, NULL, 20, 3, '2023-01-12', 3, 3, 5, 1),
('Gafas de Natación', 'Gafas anti-empañamiento', 19.99, NULL, 40, 8, '2023-01-13', 4, 4, 3, 1),
('Zapatillas Running', 'Zapatillas para correr', 59.99, NULL, 25, 5, '2023-01-14', 8, 5, 6, 1),
('Casco Ciclismo', 'Casco de seguridad', 39.99, 'L', 15, 3, '2023-01-15', 6, 6, 4, 1),
('Mancuernas 5kg', 'Par de mancuernas de 5kg', 29.99, NULL, 35, 7, '2023-01-16', 7, 7, 7, 1),
('Colchoneta Yoga', 'Colchoneta antideslizante', 34.99, NULL, 45, 9, '2023-01-17', 9, 8, 8, 1),
('Mochila Senderismo', 'Mochila 30L impermeable', 49.99, NULL, 20, 4, '2023-01-18', 10, 9, 9, 1),
('Pelota de Tenis', 'Pack de 3 pelotas', 12.99, NULL, 60, 12, '2023-01-19', 3, 10, 10, 1);
-- 9. Insertar estados de compra
INSERT INTO PurchaseStatus (status_name, description) VALUES 
('PENDING', 'Pedido realizado pero no recibido'),
('RECEIVED', 'Pedido recibido completamente'),
('CANCELED', 'Pedido cancelado');

-- 10. Insertar compras (referencian Supplier, PurchaseStatus, Employee)
INSERT INTO Purchase (date, supplier_id, status_id, employee_id) VALUES 
('2023-01-05', 1, 2, 1),
('2023-01-06', 2, 2, 2),
('2023-01-07', 3, 1, 3),
('2023-01-08', 4, 2, 4),
('2023-01-09', 5, 3, 5),
('2023-01-10', 6, 2, 6),
('2023-01-11', 7, 2, 7),
('2023-01-12', 8, 1, 8),
('2023-01-13', 9, 2, 9),
('2023-01-14', 10, 2, 10);

-- 11. Insertar detalles de compra (referencian Purchase, Product)
INSERT INTO PurchaseDetail (purchase_id, product_id, quantity, unit_price) VALUES 
(1, 1, 10, 25.00),
(1, 2, 5, 20.00),
(2, 3, 3, 85.00),
(2, 4, 8, 15.00),
(3, 5, 4, 55.00),
(4, 6, 2, 35.00),
(5, 7, 6, 25.00),
(6, 8, 5, 30.00),
(7, 9, 3, 45.00),
(8, 10, 10, 10.00);

-- 12. Insertar métodos de pago
INSERT INTO PaymentMethod (method_name, description) VALUES 
('CASH', 'Pago en efectivo'),
('CARD', 'Pago con tarjeta'),
('TRANSFER', 'Transferencia bancaria');

-- 13. Insertar ventas (referencian Customer, PaymentMethod, User)
INSERT INTO Sale (customer_id, sale_date, payment_method_id, discount, total, user_id) VALUES 
(1, '2023-01-15', 1, 0.00, 59.98, 2),
(2, '2023-01-16', 2, 5.00, 109.98, 2),
(3, '2023-01-17', 3, 10.00, 179.98, 6),
(4, '2023-01-18', 1, 0.00, 39.98, 2),
(5, '2023-01-19', 2, 15.00, 299.95, 6),
(6, '2023-01-20', 3, 0.00, 89.99, 2),
(7, '2023-01-21', 1, 5.00, 64.99, 6),
(8, '2023-01-22', 2, 0.00, 149.97, 2),
(9, '2023-01-23', 3, 20.00, 199.96, 6),
(10, '2023-01-24', 1, 0.00, 24.99, 2);

-- 14. Insertar detalles de venta (referencian Sale, Product)
INSERT INTO SaleDetail (sale_id, product_id, quantity, unit_price) VALUES 
(1, 1, 2, 29.99),
(2, 2, 1, 24.99),
(2, 3, 1, 89.99),
(3, 4, 2, 19.99),
(3, 5, 2, 59.99),
(4, 6, 1, 39.99),
(5, 7, 5, 29.99),
(5, 8, 3, 34.99),
(6, 9, 1, 89.99),
(7, 10, 5, 12.99);

-- 15. Insertar estados de pedido
INSERT INTO OrderStatus (status_name, description) VALUES 
('IN PROCESS', 'Pedido en proceso'),
('DELIVERED', 'Pedido entregado'),
('CANCELED', 'Pedido cancelado');

-- 16. Insertar pedidos de clientes (referencian Customer, OrderStatus, User)
INSERT INTO CustomerOrder (customer_id, order_date, status_id, total, user_id) VALUES 
(1, '2023-01-20', 2, 89.99, 2),
(2, '2023-01-21', 1, 149.98, 6),
(3, '2023-01-22', 1, 199.97, 2),
(4, '2023-01-23', 3, 59.99, 6),
(5, '2023-01-24', 2, 119.98, 2),
(6, '2023-01-25', 1, 34.99, 6),
(7, '2023-01-26', 2, 179.97, 2),
(8, '2023-01-27', 1, 49.99, 6),
(9, '2023-01-28', 3, 99.99, 2),
(10, '2023-01-29', 2, 24.99, 6);

-- 17. Insertar facturas (referencian Sale)
INSERT INTO Invoice (sale_id, invoice_number, issue_date, total_amount, taxes) VALUES 
(1, 'FAC-2023-0001', '2023-01-15', 59.98, 12.60),
(2, 'FAC-2023-0002', '2023-01-16', 109.98, 23.10),
(3, 'FAC-2023-0003', '2023-01-17', 179.98, 37.80),
(4, 'FAC-2023-0004', '2023-01-18', 39.98, 8.40),
(5, 'FAC-2023-0005', '2023-01-19', 299.95, 62.99),
(6, 'FAC-2023-0006', '2023-01-20', 89.99, 18.90),
(7, 'FAC-2023-0007', '2023-01-21', 64.99, 13.65),
(8, 'FAC-2023-0008', '2023-01-22', 149.97, 31.49),
(9, 'FAC-2023-0009', '2023-01-23', 199.96, 41.99),
(10, 'FAC-2023-0010', '2023-01-24', 24.99, 5.25);

-- 18. Insertar tipos de reporte
INSERT INTO ReportType (type_name, description) VALUES 
('SALES', 'Reportes de ventas'),
('INVENTORY', 'Reportes de inventario'),
('CUSTOMERS', 'Reportes de clientes');

-- 19. Insertar reportes (referencian ReportType, User)
INSERT INTO Report (report_type_id, generation_date, user_id, file_path, parameters) VALUES 
(1, '2023-01-31', 1, '/reports/sales/jan2023.pdf', '{"month": "January", "year": 2023}'),
(2, '2023-01-31', 1, '/reports/inventory/jan2023.pdf', '{"month": "January", "year": 2023}'),
(3, '2023-01-31', 1, '/reports/customers/jan2023.pdf', '{"month": "January", "year": 2023}'),
(1, '2023-02-28', 5, '/reports/sales/feb2023.pdf', '{"month": "February", "year": 2023}'),
(2, '2023-02-28', 5, '/reports/inventory/feb2023.pdf', '{"month": "February", "year": 2023}'),
(3, '2023-02-28', 5, '/reports/customers/feb2023.pdf', '{"month": "February", "year": 2023}'),
(1, '2023-03-31', 1, '/reports/sales/mar2023.pdf', '{"month": "March", "year": 2023}'),
(2, '2023-03-31', 1, '/reports/inventory/mar2023.pdf', '{"month": "March", "year": 2023}'),
(3, '2023-03-31', 1, '/reports/customers/mar2023.pdf', '{"month": "March", "year": 2023}'),
(1, '2023-04-30', 5, '/reports/sales/apr2023.pdf', '{"month": "April", "year": 2023}');

-- Triggers:
-- 1. Trigger para crear cliente automático al insertar usuario CONSUMER
DELIMITER //
CREATE TRIGGER after_user_insert_consumer
AFTER INSERT ON User
FOR EACH ROW
BEGIN
    DECLARE default_customer_type INT;
    
    -- Solo proceder si el nuevo usuario es CONSUMER
    IF NEW.role = 'CONSUMER' THEN
        -- Obtener el ID del tipo de cliente INDIVIDUAL con manejo de error
        SELECT id INTO default_customer_type FROM CustomerType WHERE type_name = 'INDIVIDUAL' LIMIT 1;
        
        IF default_customer_type IS NOT NULL THEN
            INSERT INTO Customer (
                customer_type_id,
                name,
                identity_document,
                phone,
                address,
                registration_date,
                created_by
            ) VALUES (
                default_customer_type,
                NEW.username,
                CONCAT('USER-', NEW.id),
                '000-000-0000',
                'Dirección no especificada',
                CURDATE(),
                NEW.id
            );
        END IF;
    END IF;
END//
DELIMITER ;

-- 2. Trigger para actualizar stock al vender productos
DELIMITER //
CREATE TRIGGER after_saledetail_insert
AFTER INSERT ON SaleDetail
FOR EACH ROW
BEGIN
    -- Verificar que haya suficiente stock antes de actualizar
    DECLARE current_stock_val INT;
    
    SELECT current_stock INTO current_stock_val FROM Product WHERE id = NEW.product_id;
    
    IF current_stock_val >= NEW.quantity THEN
        UPDATE Product 
        SET current_stock = current_stock - NEW.quantity,
            updated_at = CURRENT_TIMESTAMP
        WHERE id = NEW.product_id;
    ELSE
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'No hay suficiente stock para este producto';
    END IF;
END//
DELIMITER ;

-- 3. Trigger para devolver stock al cancelar venta
DELIMITER //
CREATE TRIGGER before_sale_delete
BEFORE DELETE ON Sale
FOR EACH ROW
BEGIN
    -- Actualizar stock de productos
    UPDATE Product p
    JOIN SaleDetail sd ON p.id = sd.product_id
    SET p.current_stock = p.current_stock + sd.quantity,
        p.updated_at = CURRENT_TIMESTAMP
    WHERE sd.sale_id = OLD.id;
    
    -- Eliminar detalles de venta primero (por la restricción de clave foránea)
    DELETE FROM SaleDetail WHERE sale_id = OLD.id;
END//
DELIMITER ;

-- 4. Trigger para actualizar stock al recibir compra
DELIMITER //
CREATE TRIGGER after_purchase_status_update
AFTER UPDATE ON Purchase
FOR EACH ROW
BEGIN
    DECLARE received_status_id INT;
    
    -- Solo proceder si el estado cambió
    IF NEW.status_id != OLD.status_id THEN
        SELECT id INTO received_status_id FROM PurchaseStatus WHERE status_name = 'RECEIVED' LIMIT 1;
        
        -- Si el nuevo estado es RECEIVED, aumentar el stock
        IF NEW.status_id = received_status_id THEN
            UPDATE Product p
            JOIN PurchaseDetail pd ON p.id = pd.product_id
            SET p.current_stock = p.current_stock + pd.quantity,
                p.updated_at = CURRENT_TIMESTAMP
            WHERE pd.purchase_id = NEW.id;
        END IF;
    END IF;
END//
DELIMITER ;

-- 5. Trigger para alertar sobre stock bajo
DELIMITER //
CREATE TRIGGER after_product_stock_update
AFTER UPDATE ON Product
FOR EACH ROW
BEGIN
    -- Verificar si el stock actual cayó por debajo del mínimo
    IF NEW.current_stock < NEW.minimum_stock AND (OLD.current_stock >= OLD.minimum_stock OR OLD.current_stock IS NULL) THEN
        INSERT INTO AuditLog (
            table_name,
            record_id,
            action,
            old_values,
            new_values,
            changed_by
        ) VALUES (
            'Product',
            NEW.id,
            'ALERT',
            JSON_OBJECT('current_stock', OLD.current_stock, 'minimum_stock', OLD.minimum_stock),
            JSON_OBJECT('current_stock', NEW.current_stock, 'minimum_stock', NEW.minimum_stock),
            NEW.created_by
        );
    END IF;
END//
DELIMITER ;

-- 6. Trigger para generar factura automática
DELIMITER //
CREATE TRIGGER after_sale_insert
AFTER INSERT ON Sale
FOR EACH ROW
BEGIN
    DECLARE v_tax_rate DECIMAL(5,2) DEFAULT 0.15;
    
    -- Verificar que la venta tenga un total válido
    IF NEW.total > 0 THEN
        INSERT INTO Invoice (
            sale_id,
            invoice_number,
            issue_date,
            total_amount,
            taxes,
            created_at
        ) VALUES (
            NEW.id,
            CONCAT('FAC-', DATE_FORMAT(NEW.sale_date, '%Y-%m-'), LPAD(NEW.id, 5, '0')),
            NEW.sale_date,
            NEW.total,
            NEW.total * v_tax_rate,
            CURRENT_TIMESTAMP
        );
    END IF;
END//
DELIMITER ;

-- 7. Triggers de auditoría consolidados
DELIMITER //
CREATE TRIGGER audit_user_changes
AFTER INSERT ON User
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (
        table_name,
        record_id,
        action,
        new_values,
        changed_by
    ) VALUES (
        'User',
        NEW.id,
        'INSERT',
        JSON_OBJECT(
            'username', NEW.username,
            'role', NEW.role,
            'active', NEW.active,
            'last_login', NEW.last_login
        ),
        NULL
    );
END //
CREATE TRIGGER audit_user_updates
AFTER UPDATE ON User
FOR EACH ROW
BEGIN
    IF NEW.username != OLD.username OR NEW.role != OLD.role OR NEW.active != OLD.active OR NEW.last_login != OLD.last_login THEN
        INSERT INTO AuditLog (
            table_name,
            record_id,
            action,
            old_values,
            new_values,
            changed_by
        ) VALUES (
            'User',
            NEW.id,
            'UPDATE',
            JSON_OBJECT(
                'username', OLD.username,
                'role', OLD.role,
                'active', OLD.active,
                'last_login', OLD.last_login
            ),
            JSON_OBJECT(
                'username', NEW.username,
                'role', NEW.role,
                'active', NEW.active,
                'last_login', NEW.last_login
            ),
            NULL 
        );
    END IF;
END //

CREATE TRIGGER audit_user_deletions
AFTER DELETE ON User
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (
        table_name,
        record_id,
        action,
        old_values,
        changed_by
    ) VALUES (
        'User',
        OLD.id,
        'DELETE',
        JSON_OBJECT(
            'username', OLD.username,
            'role', OLD.role,
            'active', OLD.active
        ),
        NULL
    );
END//

CREATE TRIGGER audit_product_changes
AFTER UPDATE ON Product
FOR EACH ROW
BEGIN
    IF NEW.name != OLD.name OR NEW.unit_price != OLD.unit_price OR NEW.current_stock != OLD.current_stock 
       OR NEW.minimum_stock != OLD.minimum_stock OR NEW.category_id != OLD.category_id THEN
        
        INSERT INTO AuditLog (
            table_name,
            record_id,
            action,
            old_values,
            new_values,
            changed_by
        ) VALUES (
            'Product',
            NEW.id,
            'UPDATE',
            JSON_OBJECT(
                'name', OLD.name,
                'unit_price', OLD.unit_price,
                'current_stock', OLD.current_stock,
                'minimum_stock', OLD.minimum_stock,
                'category_id', OLD.category_id
            ),
            JSON_OBJECT(
                'name', NEW.name,
                'unit_price', NEW.unit_price,
                'current_stock', NEW.current_stock,
                'minimum_stock', NEW.minimum_stock,
                'category_id', NEW.category_id
            ),
            NEW.created_by
        );
    END IF;
END//
DELIMITER ;
````

