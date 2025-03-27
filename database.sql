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

INSERT INTO User (username, password, role, active, last_login) VALUES 
('admin1', 'Admin1234', 'ADMIN', TRUE, '2023-01-15 09:30:00'),
('cajero1', 'Cajero123', 'CASHIER', TRUE, '2023-01-16 10:15:00'),
('inventario1', 'Invent123', 'INVENTORY', TRUE, '2023-01-17 11:20:00'),
('cliente1', 'Cliente123', 'CONSUMER', TRUE, '2023-01-18 14:45:00'),
('admin2', 'Admin5678', 'ADMIN', TRUE, '2023-01-19 08:10:00'),
('cajero2', 'Cajero456', 'CASHIER', TRUE, '2023-01-20 13:25:00'),
('inventario2', 'Invent456', 'INVENTORY', TRUE, '2023-01-21 16:30:00'),
('cliente2', 'Cliente456', 'CONSUMER', TRUE, '2023-01-22 17:15:00'),
('cajero3', 'Cajero789', 'CASHIER', FALSE, '2023-01-23 09:40:00'),
('cliente3', 'Cliente789', 'CONSUMER', TRUE, '2023-01-24 12:50:00');

INSERT INTO Category (name, description, created_by) VALUES 
('Fútbol', 'Artículos para fútbol', 1),
('Baloncesto', 'Artículos para baloncesto', 1),
('Tenis', 'Artículos para tenis', 3),
('Natación', 'Artículos para natación', 3),
('Atletismo', 'Artículos para atletismo', 1),
('Ciclismo', 'Artículos para ciclismo', 3),
('Gimnasio', 'Artículos para gimnasio', 1),
('Running', 'Artículos para correr', 3),
('Yoga', 'Artículos para yoga', 1),
('Senderismo', 'Artículos para senderismo', 3);

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

INSERT INTO Supplier (name, phone, address, tax_id, created_by) VALUES 
('Deportes S.A.', '123456789', 'Calle Falsa 123', 'SA12345678', 1),
('Suministros Deportivos', '987654321', 'Avenida Siempre Viva 456', 'SD87654321', 3),
('Equipamiento Plus', '555123456', 'Boulevard Deportivo 789', 'EP55512345', 1),
('Proveedor Total', '555987654', 'Carrera Atleta 321', 'PT55598765', 3),
('Distribuidora Sport', '222333444', 'Calle Campeón 654', 'DS22233344', 1),
('Suministros Elite', '666777888', 'Avenida Olimpia 987', 'SE66677788', 3),
('Deportes Unidos', '111222333', 'Boulevard Victoria 159', 'DU11122233', 1),
('Proveedora Activa', '444555666', 'Calle Resistencia 753', 'PA44455566', 3),
('Suministros Rápidos', '777888999', 'Avenida Velocidad 951', 'SR77788899', 1),
('Equipo Total', '333444555', 'Carrera Fuerza 357', 'ET33344455', 3);


INSERT INTO CustomerType (type_name, description) VALUES 
('INDIVIDUAL', 'Clientes particulares'),
('COMPANY', 'Empresas o organizaciones');

INSERT INTO Product (name, description, unit_price, size, current_stock, minimum_stock, entry_date, category_id, supplier_id, color_id, created_by) VALUES 
('Balón de Fútbol', 'Balón oficial tamaño 5', 29.99, 'M', 50, 10, '2023-01-10', 1, 1, 1, 3),
('Camiseta de Baloncesto', 'Camiseta de algodón para baloncesto', 24.99, 'L', 30, 5, '2023-01-11', 2, 2, 2, 3),
('Raqueta de Tenis', 'Raqueta profesional', 89.99, NULL, 20, 3, '2023-01-12', 3, 3, 5, 1),
('Gafas de Natación', 'Gafas anti-empañamiento', 19.99, NULL, 40, 8, '2023-01-13', 4, 4, 3, 1),
('Zapatillas Running', 'Zapatillas para correr', 59.99, '42', 25, 5, '2023-01-14', 8, 5, 6, 3),
('Casco Ciclismo', 'Casco de seguridad', 39.99, 'L', 15, 3, '2023-01-15', 6, 6, 4, 1),
('Mancuernas 5kg', 'Par de mancuernas de 5kg', 29.99, NULL, 35, 7, '2023-01-16', 7, 7, 7, 3),
('Colchoneta Yoga', 'Colchoneta antideslizante', 34.99, NULL, 45, 9, '2023-01-17', 9, 8, 8, 1),
('Mochila Senderismo', 'Mochila 30L impermeable', 49.99, NULL, 20, 4, '2023-01-18', 10, 9, 9, 3),
('Pelota de Tenis', 'Pack de 3 pelotas', 12.99, NULL, 60, 12, '2023-01-19', 3, 10, 10, 1);

INSERT INTO Customer (customer_type_id, name, identity_document, phone, address, registration_date, created_by) VALUES 
(1, 'Juan Pérez', '12345678A', '611223344', 'Calle Primavera 1', '2023-01-10', 1),
(1, 'María García', '87654321B', '622334455', 'Avenida Verano 2', '2023-01-11', 3),
(2, 'Gimnasio Active', 'A12345678', '633445566', 'Boulevard Otoño 3', '2023-01-12', 1),
(1, 'Carlos López', '23456789C', '644556677', 'Calle Invierno 4', '2023-01-13', 3),
(2, 'Club Deportivo', 'B23456789', '655667788', 'Avenida Estación 5', '2023-01-14', 1),
(1, 'Ana Martínez', '34567891D', '666778899', 'Boulevard Sol 6', '2023-01-15', 3),
(1, 'Luis Fernández', '45678912E', '677889900', 'Calle Luna 7', '2023-01-16', 1),
(2, 'Escuela Natación', 'C34567891', '688990011', 'Avenida Mar 8', '2023-01-17', 3),
(1, 'Sofía Rodríguez', '56789123F', '699001122', 'Boulevard Río 9', '2023-01-18', 1),
(2, 'Equipo Ciclismo', 'D45678912', '600112233', 'Calle Montaña 10', '2023-01-19', 3);

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

INSERT INTO PurchaseStatus (status_name, description) VALUES 
('PENDING', 'Pedido realizado pero no recibido'),
('RECEIVED', 'Pedido recibido completamente'),
('CANCELED', 'Pedido cancelado');

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

INSERT INTO PaymentMethod (method_name, description) VALUES 
('CASH', 'Pago en efectivo'),
('CARD', 'Pago con tarjeta'),
('TRANSFER', 'Transferencia bancaria');

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
(10, '2023-01-24', 1, 0.00, 24.99, 2),
INSERT INTO Sale (customer_id, sale_date, payment_method_id, discount, total, user_id) VALUES 
(11,CURRENT_TIMESTAMP,1,0.00,188.85,11);
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

INSERT INTO OrderStatus (status_name, description) VALUES 
('IN PROCESS', 'Pedido en proceso'),
('DELIVERED', 'Pedido entregado'),
('CANCELED', 'Pedido cancelado');

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

INSERT INTO ReportType (type_name, description) VALUES 
('SALES', 'Reportes de ventas'),
('INVENTORY', 'Reportes de inventario'),
('CUSTOMERS', 'Reportes de clientes');

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