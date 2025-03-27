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



-- Inserccion de datos de prueba

-- Insertar tipos de cliente
INSERT INTO CustomerType (type_name, description) VALUES 
('INDIVIDUAL', 'Clientes individuales o personas naturales'),
('COMPANY', 'Empresas u organizaciones');

-- Insertar métodos de pago
INSERT INTO PaymentMethod (method_name, description) VALUES 
('CASH', 'Pago en efectivo'),
('CARD', 'Pago con tarjeta de crédito/débito'),
('TRANSFER', 'Transferencia bancaria');

-- Insertar estados de compra
INSERT INTO PurchaseStatus (status_name, description) VALUES 
('PENDING', 'Compra pendiente de recepción'),
('RECEIVED', 'Compra recibida completamente'),
('CANCELED', 'Compra cancelada');

-- Insertar estados de pedido
INSERT INTO OrderStatus (status_name, description) VALUES 
('IN PROCESS', 'Pedido en proceso'),
('DELIVERED', 'Pedido entregado'),
('CANCELED', 'Pedido cancelado');

-- Insertar tipos de reporte
INSERT INTO ReportType (type_name, description) VALUES 
('SALES', 'Reportes de ventas'),
('INVENTORY', 'Reportes de inventario'),
('CUSTOMERS', 'Reportes de clientes');

-- Insertar colores
INSERT INTO Color (name, hex_code) VALUES 
('Rojo', '#FF0000'),
('Azul', '#0000FF'),
('Verde', '#00FF00'),
('Negro', '#000000'),
('Blanco', '#FFFFFF'),
('Gris', '#808080'),
('Amarillo', '#FFFF00'),
('Morado', '#800080');

-- Insertar categorías
INSERT INTO Category (name, description, created_by) VALUES 
('Ropa', 'Artículos de vestir deportivos', NULL),
('Calzado', 'Zapatos y zapatillas deportivas', NULL),
('Equipamiento', 'Equipo deportivo variado', NULL),
('Accesorios', 'Accesorios para deportes', NULL);

-- Insertar usuarios administradores
INSERT INTO User (username, password, role, active) VALUES 
('admin1', '12345678', 'ADMIN', TRUE),
('admin2', '12345678', 'ADMIN', TRUE),
('cajero1', '12345678', 'CASHIER', TRUE),
('inventario1', '12345678', 'INVENTORY', TRUE),
('cliente1', '12345678', 'CONSUMER', TRUE),
('cliente2', '12345678', 'CONSUMER', TRUE),
('cliente3', '12345678', 'CONSUMER', TRUE);

-- Insertar empleados (asociados a usuarios)
INSERT INTO Employee (name, position, phone, user_id, hire_date, created_by) VALUES 
('Juan Pérez', 'ADMINISTRATOR', '555-1234', 1, '2022-01-15', 1),
('María García', 'CASHIER', '555-5678', 3, '2022-02-20', 1),
('Carlos López', 'INVENTORY', '555-9012', 4, '2022-03-10', 1);

INSERT INTO Supplier (name, phone, address, tax_id, created_by) VALUES 
('Deportes S.A.', '555-1001', 'Calle Principal 123', 'SA12345678', 1),
('Equipamiento Plus', '555-2002', 'Avenida Central 456', 'EP87654321', 1),
('Ropa Deportiva VIP', '555-3003', 'Boulevard Deportivo 789', 'RDV45678912', 1);

INSERT INTO Product (name, description, unit_price, size, current_stock, minimum_stock, entry_date, category_id, supplier_id, color_id, created_by) VALUES 
('Camiseta Deportiva', 'Camiseta de algodón para entrenamiento', 25.99, 'M', 50, 10, '2023-01-10', 1, 1, 1, 1),
('Short Running', 'Short ligero para correr', 19.99, 'L', 30, 5, '2023-01-15', 1, 1, 4, 1),
('Zapatillas Running', 'Zapatillas para correr con amortiguación', 89.99, '42', 20, 5, '2023-02-01', 2, 2, 3, 1),
('Raqueta Tenis', 'Raqueta profesional de tenis', 120.00, NULL, 15, 3, '2023-02-10', 3, 3, 5, 1),
('Balón Fútbol', 'Balón oficial tamaño 5', 29.99, NULL, 40, 8, '2023-03-05', 3, 2, 1, 1),
('Gorra Deportiva', 'Gorra ajustable con protección UV', 15.99, 'Única', 60, 15, '2023-03-15', 4, 1, 4, 1);

-- Insertar clientes adicionales
INSERT INTO Customer (customer_type_id, name, identity_document, phone, address, registration_date, created_by) VALUES 
(2, 'Empresa Deportes XYZ', 'J-123456789', '555-4004', 'Avenida Comercial 321', '2023-01-05', 1),
(1, 'Ana Rodríguez', 'V-98765432', '555-5005', 'Calle Secundaria 654', '2023-02-15', 1);

INSERT INTO Purchase (date, supplier_id, status_id, employee_id) VALUES 
('2023-03-01', 1, 2, 3);

SET @purchase_id = LAST_INSERT_ID();

INSERT INTO PurchaseDetail (purchase_id, product_id, quantity, unit_price) VALUES 
(@purchase_id, 1, 30, 18.50),
(@purchase_id, 2, 20, 14.75);

-- Compra 2
INSERT INTO Purchase (date, supplier_id, status_id, employee_id) VALUES 
('2023-03-15', 2, 1, 3);

SET @purchase_id = LAST_INSERT_ID();

INSERT INTO PurchaseDetail (purchase_id, product_id, quantity, unit_price) VALUES 
(@purchase_id, 3, 10, 65.00),
(@purchase_id, 5, 15, 20.00);

-- Venta 1
INSERT INTO Sale (customer_id, sale_date, payment_method_id, discount, total, user_id) VALUES 
(1, '2023-03-10', 1, 5.00, 120.95, 3);

SET @sale_id = LAST_INSERT_ID();

INSERT INTO SaleDetail (sale_id, product_id, quantity, unit_price) VALUES 
(@sale_id, 1, 2, 25.99),
(@sale_id, 2, 1, 19.99),
(@sale_id, 6, 3, 15.99);

-- Venta 2
INSERT INTO Sale (customer_id, sale_date, payment_method_id, discount, 0, 89.99, user_id) VALUES 
(2, '2023-03-12', 2, 0, 89.99, 3);

SET @sale_id = LAST_INSERT_ID();

INSERT INTO SaleDetail (sale_id, product_id, quantity, unit_price) VALUES 
(@sale_id, 3, 1, 89.99);

-- Pedido 1
INSERT INTO CustomerOrder (customer_id, order_date, status_id, total, user_id) VALUES 
(3, '2023-03-05', 1, 240.00, 3);

SET @order_id = LAST_INSERT_ID();

-- Pedido 2
INSERT INTO CustomerOrder (customer_id, order_date, status_id, total, user_id) VALUES 
(4, '2023-03-08', 3, 120.00, 3);

INSERT INTO Report (report_type_id, generation_date, user_id, file_path, parameters) VALUES 
(1, '2023-03-01', 1, '/reports/sales_20230301.pdf', '{"start_date":"2023-01-01","end_date":"2023-03-01"}'),
(2, '2023-03-15', 1, '/reports/inventory_20230315.pdf', '{"threshold":"minimum_stock"}');

-- Triggers:

DELIMITER //
CREATE TRIGGER after_user_insert_consumer
AFTER INSERT ON User
FOR EACH ROW
BEGIN
    DECLARE default_customer_type INT;
    
    -- Obtener el ID del tipo de cliente INDIVIDUAL
    SELECT id INTO default_customer_type FROM CustomerType WHERE type_name = 'INDIVIDUAL' LIMIT 1;
    
    -- Si el nuevo usuario es CONSUMER, crear automáticamente un cliente asociado
    IF NEW.role = 'CONSUMER' THEN
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
            NEW.username, -- Usamos el username como nombre inicial
            CONCAT('USER-', NEW.id), -- Documento de identidad generado
            '000-000-0000', -- Teléfono por defecto
            'Dirección no especificada', -- Dirección por defecto
            CURDATE(), -- Fecha de registro actual
            NEW.id -- El propio usuario como creador
        );
    END IF;
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_saledetail_insert
AFTER INSERT ON SaleDetail
FOR EACH ROW
BEGIN
    -- Reducir el stock del producto vendido
    UPDATE Product 
    SET current_stock = current_stock - NEW.quantity,
        updated_at = CURRENT_TIMESTAMP
    WHERE id = NEW.product_id;
END//
DELIMITER ;
3. Trigger para Actualizar Stock al Cancelar una Venta

DELIMITER //
CREATE TRIGGER before_sale_delete
BEFORE DELETE ON Sale
FOR EACH ROW
BEGIN
    -- Devolver los productos al stock si se cancela una venta
    UPDATE Product p
    JOIN SaleDetail sd ON p.id = sd.product_id
    SET p.current_stock = p.current_stock + sd.quantity,
        p.updated_at = CURRENT_TIMESTAMP
    WHERE sd.sale_id = OLD.id;
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_purchase_status_update
AFTER UPDATE ON Purchase
FOR EACH ROW
BEGIN
    DECLARE received_status_id INT;
    
    -- Obtener el ID del estado RECEIVED
    SELECT id INTO received_status_id FROM PurchaseStatus WHERE status_name = 'RECEIVED' LIMIT 1;
    
    -- Si el estado cambió a RECEIVED, aumentar el stock
    IF NEW.status_id = received_status_id AND OLD.status_id != received_status_id THEN
        UPDATE Product p
        JOIN PurchaseDetail pd ON p.id = pd.product_id
        SET p.current_stock = p.current_stock + pd.quantity,
            p.updated_at = CURRENT_TIMESTAMP
        WHERE pd.purchase_id = NEW.id;
    END IF;
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_product_stock_update
AFTER UPDATE ON Product
FOR EACH ROW
BEGIN
    -- Verificar si el stock actual cayó por debajo del mínimo
    IF NEW.current_stock < NEW.minimum_stock AND OLD.current_stock >= OLD.minimum_stock THEN
        -- Aquí podrías insertar en una tabla de notificaciones o alertas
        -- Por ejemplo:
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
            JSON_OBJECT('current_stock', OLD.current_stock, 'minimum_stock', OLD.minimum_stock),
            JSON_OBJECT('current_stock', NEW.current_stock, 'minimum_stock', NEW.minimum_stock),
            NULL -- O el ID del usuario si está disponible en el contexto
        );
    END IF;
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER before_product_update
BEFORE UPDATE ON Product
FOR EACH ROW
BEGIN
    -- Registrar cambios importantes en el producto
    IF NEW.unit_price != OLD.unit_price OR NEW.current_stock != OLD.current_stock THEN
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
                'unit_price', OLD.unit_price,
                'current_stock', OLD.current_stock,
                'name', OLD.name
            ),
            JSON_OBJECT(
                'unit_price', NEW.unit_price,
                'current_stock', NEW.current_stock,
                'name', NEW.name
            ),
            NEW.created_by
        );
    END IF;
END//

DELIMITER //
CREATE TRIGGER after_sale_insert
AFTER INSERT ON Sale
FOR EACH ROW
BEGIN
    DECLARE v_tax_rate DECIMAL(5,2) DEFAULT 0.15; -- Suponiendo un 15% de impuestos
    
    -- Crear factura automáticamente para cada venta
    INSERT INTO Invoice (
        sale_id,
        invoice_number,
        issue_date,
        total_amount,
        taxes,
        created_at
    ) VALUES (
        NEW.id,
        CONCAT('FAC-', DATE_FORMAT(NEW.sale_date, '%Y%m%d-'), NEW.id),
        NEW.sale_date,
        NEW.total,
        NEW.total * v_tax_rate,
        CURRENT_TIMESTAMP
    );
END//
DELIMITER ;

DELIMITER //

CREATE TRIGGER audit_user_insert
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
            'active', NEW.active
        ),
        NEW.created_by
    );
END//

CREATE TRIGGER audit_user_update
AFTER UPDATE ON User
FOR EACH ROW
BEGIN
    IF NEW.username != OLD.username OR NEW.role != OLD.role OR NEW.active != OLD.active THEN
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
                'active', OLD.active
            ),
            JSON_OBJECT(
                'username', NEW.username,
                'role', NEW.role,
                'active', NEW.active
            ),
            NEW.created_by
        );
    END IF;
END//

CREATE TRIGGER audit_user_delete
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
        NULL -- No sabemos quién lo eliminó
    );
END//

CREATE TRIGGER audit_product_changes
AFTER UPDATE ON Product
FOR EACH ROW
BEGIN
    IF NEW.name != OLD.name OR NEW.unit_price != OLD.unit_price OR NEW.current_stock != OLD.current_stock THEN
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
                'current_stock', OLD.current_stock
            ),
            JSON_OBJECT(
                'name', NEW.name,
                'unit_price', NEW.unit_price,
                'current_stock', NEW.current_stock
            ),
            NEW.created_by
        );
    END IF;
END//

DELIMITER ;