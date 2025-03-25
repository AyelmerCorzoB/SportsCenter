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

-- Triggers:

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