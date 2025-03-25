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
-- Tablas principales normalizadas:

CREATE DATABASE IF NOT EXISTS sportscenter;
USE sportscenter;

-- Tabla User (primero por dependencias)
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'CASHIER', 'INVENTORY') NOT NULL,
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

-- Tabla Color (normalizada desde el campo color en Product)
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
    email VARCHAR(100) NOT NULL,
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

-- Tabla CustomerType (normalizada desde customer_type)
CREATE TABLE CustomerType (
    id INT PRIMARY KEY AUTO_INCREMENT,
    type_name ENUM('INDIVIDUAL', 'COMPANY') NOT NULL UNIQUE,
    description TEXT
);

-- Tabla Customer
CREATE TABLE Customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_type_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    identity_document VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
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
    email VARCHAR(100) NOT NULL,
    user_id INT UNIQUE,
    hire_date DATE NOT NULL,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE SET NULL,
    FOREIGN KEY (created_by) REFERENCES User(id) ON DELETE SET NULL,
    CHECK (LENGTH(name) > 0)
);

-- Tabla PurchaseStatus (normalizada desde status)
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

-- Tabla PaymentMethod (normalizada desde payment_method)
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

-- Tabla OrderStatus (normalizada desde status)
CREATE TABLE OrderStatus (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status_name ENUM('IN PROCESS', 'DELIVERED', 'CANCELED') NOT NULL UNIQUE,
    description TEXT
);

-- Tabla CustomerOrder (renombrada de Order)
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

-- Tabla ReportType (normalizada desde type)
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
````





## DIAGRAMA DE BASE DE DATOS(ER)

![sportscenter](C:\Users\ALAN\sportscenter.png)

## TRIGGERS PARA LA BASE DE DATOS NORMALIZADA

````sql
-- Tabla para todas las auditorias de cada tabla
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

-- Triggers para cada tabla principal:

-- Trigger para User
DELIMITER //
CREATE TRIGGER after_user_insert
AFTER INSERT ON User FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (table_name, record_id, action, new_values, changed_by)
    VALUES ('User', NEW.id, 'INSERT', 
            JSON_OBJECT('username', NEW.username, 'email', NEW.email, 'role', NEW.role, 'active', NEW.active),
            NEW.id);
END //

CREATE TRIGGER after_user_update
AFTER UPDATE ON User FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (table_name, record_id, action, old_values, new_values, changed_by)
    VALUES ('User', NEW.id, 'UPDATE', 
            JSON_OBJECT('username', OLD.username, 'email', OLD.email, 'role', OLD.role, 'active', OLD.active),
            JSON_OBJECT('username', NEW.username, 'email', NEW.email, 'role', NEW.role, 'active', NEW.active),
            NEW.id);
END //

CREATE TRIGGER before_user_delete
BEFORE DELETE ON User FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (table_name, record_id, action, old_values, changed_by)
    VALUES ('User', OLD.id, 'DELETE', 
            JSON_OBJECT('username', OLD.username, 'email', OLD.email, 'role', OLD.role, 'active', OLD.active),
            OLD.id);
END //
DELIMITER ;

-- Trigger para Product (ejemplo similar para otras tablas)
DELIMITER //
CREATE TRIGGER after_product_insert
AFTER INSERT ON Product FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (table_name, record_id, action, new_values, changed_by)
    VALUES ('Product', NEW.id, 'INSERT', 
            JSON_OBJECT('name', NEW.name, 'unit_price', NEW.unit_price, 'current_stock', NEW.current_stock),
            NEW.created_by);
END //

CREATE TRIGGER after_product_update
AFTER UPDATE ON Product FOR EACH ROW
BEGIN
    IF OLD.unit_price != NEW.unit_price OR OLD.current_stock != NEW.current_stock THEN
        INSERT INTO AuditLog (table_name, record_id, action, old_values, new_values, changed_by)
        VALUES ('Product', NEW.id, 'UPDATE', 
                JSON_OBJECT('name', OLD.name, 'unit_price', OLD.unit_price, 'current_stock', OLD.current_stock),
                JSON_OBJECT('name', NEW.name, 'unit_price', NEW.unit_price, 'current_stock', NEW.current_stock),
                NEW.created_by);
    END IF;
END //

CREATE TRIGGER before_product_delete
BEFORE DELETE ON Product FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (table_name, record_id, action, old_values, changed_by)
    VALUES ('Product', OLD.id, 'DELETE', 
            JSON_OBJECT('name', OLD.name, 'unit_price', OLD.unit_price, 'current_stock', OLD.current_stock),
            OLD.created_by);
END //
DELIMITER ;

-- Triggers para actualización de stock automática
DELIMITER //
CREATE TRIGGER after_purchase_detail_insert
AFTER INSERT ON PurchaseDetail FOR EACH ROW
BEGIN
    -- Actualizar stock del producto
    UPDATE Product SET current_stock = current_stock + NEW.quantity 
    WHERE id = NEW.product_id;
    
    -- Registrar en auditoría
    INSERT INTO AuditLog (table_name, record_id, action, new_values, changed_by)
    SELECT 'Product', NEW.product_id, 'UPDATE', 
           JSON_OBJECT('stock_added', NEW.quantity, 'reason', 'Purchase'),
           p.created_by
    FROM Product p WHERE p.id = NEW.product_id;
END //

CREATE TRIGGER after_sale_detail_insert
AFTER INSERT ON SaleDetail FOR EACH ROW
BEGIN
    -- Actualizar stock del producto
    UPDATE Product SET current_stock = current_stock - NEW.quantity 
    WHERE id = NEW.product_id;
    
    -- Registrar en auditoría
    INSERT INTO AuditLog (table_name, record_id, action, new_values, changed_by)
    SELECT 'Product', NEW.product_id, 'UPDATE', 
           JSON_OBJECT('stock_removed', NEW.quantity, 'reason', 'Sale'),
           s.user_id
    FROM Sale s WHERE s.id = NEW.sale_id;
END //

CREATE TRIGGER before_sale_detail_insert
BEFORE INSERT ON SaleDetail FOR EACH ROW
BEGIN
    DECLARE available_stock INT;
    
    SELECT current_stock INTO available_stock 
    FROM Product WHERE id = NEW.product_id;
    
    IF available_stock < NEW.quantity THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Insufficient stock for this product';
    END IF;
END //
DELIMITER ;
````

