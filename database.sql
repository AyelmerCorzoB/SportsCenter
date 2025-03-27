-- Tablas principales normalizadas:

CREATE DATABASE IF NOT EXISTS sportscenter;
USE sportscenter;

-- Tabla User (primero por dependencias)
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
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
-- Inserccion de datos de prueba

INSERT INTO User (username, email, password, role, active, last_login) VALUES 
('admin1', 'admin1@sportscenter.com', 'admin', 'ADMIN', TRUE, '2023-05-15 09:30:00'),
('cashier1', 'cashier1@sportscenter.com', 'cashier', 'CASHIER', TRUE, '2023-05-15 10:15:00'),
('inventory1', 'inventory1@sportscenter.com', 'inventory', 'INVENTORY', TRUE, '2023-05-14 14:20:00'),
('consumer1', 'consumer1@sportscenter.com', 'consumer', 'CONSUMER', TRUE, '2023-05-15 11:45:00'),
('consumer2', 'consumer2@sportscenter.com', 'consumer', 'CONSUMER', FALSE, '2023-05-10 16:30:00');


INSERT INTO Category (name, description, created_by) VALUES 
('Footwear', 'Sports shoes for various activities', 1),
('Apparel', 'Clothing for sports and exercise', 1),
('Equipment', 'Sports gear and equipment', 1),
('Accessories', 'Sports-related accessories', 1),
('Team Sports', 'Equipment for team sports', 1);


INSERT INTO Color (name, hex_code) VALUES 
('Red', '#FF0000'),
('Blue', '#0000FF'),
('Black', '#000000'),
('White', '#FFFFFF'),
('Green', '#00FF00');


INSERT INTO Supplier (name, phone, email, address, tax_id, created_by) VALUES 
('SportsGear Inc.', '555-100-2000', 'contact@sportsgear.com', '123 Industrial Park, City', 'TAX123456', 1),
('ActiveWear Ltd.', '555-200-3000', 'sales@activewear.com', '456 Commerce St, Town', 'TAX654321', 1),
('ProEquipment Co.', '555-300-4000', 'info@proequipment.com', '789 Business Ave, Village', 'TAX987654', 1),
('TeamSports Supply', '555-400-5000', 'orders@teamsports.com', '321 Athletic Blvd, District', 'TAX456789', 1),
('Outdoor Adventures', '555-500-6000', 'support@outdoor.com', '654 Nature Trail, County', 'TAX789123', 1);


INSERT INTO Product (name, description, unit_price, size, current_stock, minimum_stock, entry_date, category_id, supplier_id, color_id, created_by) VALUES 
('Running Shoes', 'Lightweight running shoes with cushioning', 89.99, 'M', 50, 10, '2023-01-15', 1, 1, 3, 3),
('Basketball Jersey', 'Breathable mesh basketball jersey', 29.99, 'L', 30, 5, '2023-02-20', 2, 2, 1, 3),
('Yoga Mat', 'Non-slip yoga mat with carrying strap', 24.99, NULL, 40, 8, '2023-03-10', 3, 3, 4, 3),
('Tennis Racket', 'Professional grade tennis racket', 129.99, NULL, 15, 3, '2023-04-05', 3, 4, 2, 3),
('Soccer Ball', 'FIFA approved soccer ball', 39.99, NULL, 25, 5, '2023-05-01', 5, 5, 5, 3);

INSERT INTO CustomerType (type_name, description) VALUES 
('INDIVIDUAL', 'Individual customers purchasing for personal use'),
('COMPANY', 'Business entities purchasing for their organizations');

INSERT INTO Customer (customer_type_id, name, identity_document, email, phone, address, registration_date, created_by) VALUES 
(1, 'John Smith', 'ID123456789', 'john.smith@email.com', '555-111-2222', '123 Main St, City', '2023-01-10', 1),
(1, 'Maria Garcia', 'ID987654321', 'maria.garcia@email.com', '555-222-3333', '456 Oak Ave, Town', '2023-02-15', 1),
(2, 'ABC Corporation', 'TAXCORP123', 'purchasing@abc.com', '555-333-4444', '789 Business Park, District', '2023-03-20', 1),
(1, 'Robert Johnson', 'ID456123789', 'robert.j@email.com', '555-444-5555', '321 Pine Rd, Village', '2023-04-05', 1),
(2, 'XYZ Sports Club', 'TAXCLUB456', 'manager@xyzclub.com', '555-555-6666', '654 Stadium Blvd, County', '2023-05-01', 1);


INSERT INTO Employee (name, position, phone, email, user_id, hire_date, created_by) VALUES 
('Alice Johnson', 'ADMINISTRATOR', '555-111-0001', 'alice.j@sportscenter.com', 1, '2022-01-15', 1),
('Bob Williams', 'CASHIER', '555-111-0002', 'bob.w@sportscenter.com', 2, '2022-03-20', 1),
('Charlie Brown', 'INVENTORY', '555-111-0003', 'charlie.b@sportscenter.com', 3, '2022-05-10', 1),
('Diana Prince', 'CASHIER', '555-111-0004', 'diana.p@sportscenter.com', NULL, '2022-07-15', 1),
('Ethan Hunt', 'INVENTORY', '555-111-0005', 'ethan.h@sportscenter.com', NULL, '2022-09-20', 1);


INSERT INTO PurchaseStatus (status_name, description) VALUES 
('PENDING', 'Order placed but not yet received'),
('RECEIVED', 'Order has been received and processed'),
('CANCELED', 'Order was canceled');


INSERT INTO Purchase (date, supplier_id, status_id, employee_id) VALUES 
('2023-04-01', 1, 2, 3),
('2023-04-15', 2, 2, 3),
('2023-05-01', 3, 1, 3),
('2023-05-10', 4, 3, 5),
('2023-05-12', 5, 2, 5);


INSERT INTO PurchaseDetail (purchase_id, product_id, quantity, unit_price) VALUES 
(1, 1, 20, 60.00),
(1, 2, 15, 18.00),
(2, 3, 30, 15.00),
(3, 4, 10, 90.00),
(4, 5, 20, 25.00);


INSERT INTO PaymentMethod (method_name, description) VALUES 
('CASH', 'Payment with physical currency'),
('CARD', 'Payment with credit/debit card'),
('TRANSFER', 'Electronic bank transfer');


INSERT INTO Sale (customer_id, sale_date, payment_method_id, discount, total, user_id) VALUES 
(1, '2023-05-01', 2, 5.00, 84.99, 2),
(2, '2023-05-02', 1, 0.00, 59.98, 2),
(3, '2023-05-05', 3, 10.00, 224.97, 4),
(4, '2023-05-08', 2, 0.00, 129.99, 2),
(5, '2023-05-10', 2, 15.00, 169.96, 4);


INSERT INTO SaleDetail (sale_id, product_id, quantity, unit_price) VALUES 
(1, 1, 1, 89.99),
(2, 2, 2, 29.99),
(3, 3, 3, 24.99),
(3, 4, 1, 129.99),
(4, 4, 1, 129.99),
(5, 5, 4, 39.99);


INSERT INTO OrderStatus (status_name, description) VALUES 
('IN PROCESS', 'Order is being prepared'),
('DELIVERED', 'Order has been delivered'),
('CANCELED', 'Order was canceled');


INSERT INTO CustomerOrder (customer_id, order_date, status_id, total, user_id) VALUES 
(1, '2023-05-01', 2, 89.99, 2),
(2, '2023-05-03', 1, 59.98, 2),
(3, '2023-05-05', 1, 224.97, 4),
(4, '2023-05-08', 3, 129.99, 2),
(5, '2023-05-10', 2, 159.96, 4);


INSERT INTO Invoice (sale_id, invoice_number, issue_date, total_amount, taxes) VALUES 
(1, 'INV-2023-001', '2023-05-01', 84.99, 12.74),
(2, 'INV-2023-002', '2023-05-02', 59.98, 8.99),
(3, 'INV-2023-003', '2023-05-05', 224.97, 33.74),
(4, 'INV-2023-004', '2023-05-08', 129.99, 19.49),
(5, 'INV-2023-005', '2023-05-10', 169.96, 25.49);


INSERT INTO ReportType (type_name, description) VALUES 
('SALES', 'Reports related to sales data'),
('INVENTORY', 'Reports about stock levels and inventory'),
('CUSTOMERS', 'Reports on customer activities and trends');


INSERT INTO Report (report_type_id, generation_date, user_id, file_path, parameters) VALUES 
(1, '2023-05-01', 1, '/reports/sales_20230501.pdf', '{"date_from": "2023-04-01", "date_to": "2023-04-30"}'),
(2, '2023-05-02', 1, '/reports/inventory_20230502.pdf', '{"category": "all", "stock_level": "low"}'),
(3, '2023-05-05', 1, '/reports/customers_20230505.pdf', '{"customer_type": "all", "period": "monthly"}'),
(1, '2023-05-10', 1, '/reports/sales_20230510.pdf', '{"date_from": "2023-05-01", "date_to": "2023-05-10"}'),
(2, '2023-05-15', 1, '/reports/inventory_20230515.pdf', '{"category": "Footwear", "stock_level": "all"}');
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
DELIMITER //
CREATE TRIGGER after_user_insert_customer
AFTER INSERT ON User FOR EACH ROW
BEGIN
    -- Solo actuar si el nuevo usuario es CONSUMER
    IF NEW.role = 'CONSUMER' THEN
        -- Verificar si ya existe un customer para este usuario
        IF NOT EXISTS (SELECT 1 FROM Customer WHERE created_by = NEW.id) THEN
            -- Insertar en la tabla Customer con valores por defecto
            INSERT INTO Customer (
                customer_type_id,
                name,
                identity_document,
                email,
                phone,
                address,
                registration_date,
                created_by
            ) VALUES (
                1,
                NEW.username,
                'PENDIENTE',
                NEW.email,
                'PENDIENTE',
                'PENDIENTE',
                CURDATE(),
                NEW.id
            );
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_user_update_customer
AFTER UPDATE ON User FOR EACH ROW
BEGIN
    
    IF NEW.role = 'CONSUMER' AND (OLD.email != NEW.email OR OLD.username != NEW.username) THEN
        UPDATE Customer
        SET
            email = NEW.email,
            name = CASE WHEN name = OLD.username THEN NEW.username ELSE name END
        WHERE created_by = NEW.id;
    END IF;
END //
DELIMITER ;