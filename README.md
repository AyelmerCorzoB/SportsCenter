# **Sistema de Gestión de Centros Deportivos**

<details open>
<summary><strong><img src="https://flagcdn.com/es.svg" alt="Bandera de España" width="20" height="15"> -- Español</strong></summary>


Este proyecto es un sistema de gestión para un centro deportivo que permite administrar categorías, proveedores, productos, clientes, empleados, órdenes de compra, ventas, pedidos de clientes, facturas y reportes. Está diseñado para ser escalable y modular, utilizando una base de datos MySQL y una aplicación Java Maven nativa.

---

## **Tabla de Contenidos**
1. [Requisitos](#requisitos-español)
2. [Estructura del Proyecto](#estructura-del-proyecto-español)
3. [Configuración de la Base de Datos](#configuración-de-la-base-de-datos-español)
4. [Entidades y Repositorios](#entidades-y-repositorios-español)
5. [Casos de Uso](#casos-de-uso-español)
6. [Ejecución del Proyecto](#ejecución-del-proyecto-español)
7. [Escalabilidad](#escalabilidad-español)
8. [Contribución](#contribución-español)

---

## **Requisitos** <a name="requisitos-español"></a>
- **Java JDK 11 o superior**
- **Maven 3.6 o superior**
- **MySQL 8.0 o superior**
- **IDE (Eclipse, IntelliJ IDEA, etc.)**
- **Dependencias:**
  - MySQL Connector/J

---

## **Estructura del Proyecto** <a name="estructura-del-proyecto-español"></a>
El proyecto está organizado en los siguientes paquetes:

```
src/main/java/com/sportscenter
├───adapter
│   ├───ui                  # Interfaz de usuario (consola, web, etc.)
│   └───validations         # Validaciones de datos
├───application
│   └───usecase             # Casos de uso (lógica de negocio)
├───config                  # Configuraciones de la aplicación
├───domain
│   ├───entities            # Entidades del dominio
│   ├───model               # Modelos de datos (DTOs, etc.)
│   └───repository          # Interfaces de repositorio
└───infrastructure
    ├───database            # Configuración de la base de datos
    └───persistence         # Implementaciones de repositorios
```

---

## **Configuración de la Base de Datos** <a name="configuración-de-la-base-de-datos-español"></a>
1. **Crear la base de datos:**
   ```sql
   CREATE DATABASE IF NOT EXISTS sportscenter;
   USE sportscenter;
   ```
2. **Ejecutar el script SQL** proporcionado en el archivo `database.sql` para crear las tablas y relaciones.
3. **Configurar la conexión a la base de datos** en `config.properties`:
   ```properties
   db.url=jdbc:mysql://localhost:3306/sportscenter
   db.user=tuUsuario
   db.password=tuContraseña
   ```

---

## **Entidades y Repositorios** <a name="entidades-y-repositorios-español"></a>
Cada tabla de la base de datos está mapeada a una entidad. Los repositorios proporcionan operaciones CRUD.

### Ejemplo de Entidad:
```java
public class Product {
    private int id;
    private String name;
    private String description;
    private double unit_price;
    private String size;
    private String color;
    private int current_stock;
    private int minimum_stock;
    private Date entry_date;
    private Category category;
    private Supplier supplier;

    // Getters y Setters
    // Constructores y toString
}
```

### Ejemplo de Repositorio:
```java
public interface ProductRepository {
    void save(Product product);
    Product searchById(int id);
    List<Product> listAll();
    void update(Product product);
    void delete(int id);
}
```

---

## **Casos de Uso** <a name="casos-de-uso-español"></a>
Los casos de uso implementan la lógica de negocio. Cada entidad importante tiene su propio caso de uso.

### Ejemplo de Caso de Uso:
```java
public class ProductUseCase {
    private final ProductRepository repository;
    public ProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public void registerProduct(String name, String description, double unit_price, String size, String color,
            int current_stock, int minimum_stock, Date entry_date, Category category, Supplier supplier) {
        Product product = new Product(name, description, unit_price, size, color, current_stock, minimum_stock,
                entry_date, category, supplier);
        repository.save(product);
    }

    public Product getProduct(int id) {
        return repository.searchById(id);
    }

    public List<Product> listProducts() {
        return repository.listAll();
    }

    public void updateProduct(int id, String name, String description, double unit_price, String size, String color,
            int current_stock, int minimum_stock, Date entry_date, Category category, Supplier supplier) {
        Product product = new Product(id, name, description, unit_price, size, color, current_stock, minimum_stock,
                entry_date, category, supplier);
        repository.update(product);
    }

    public void deleteProduct(int id) {
        repository.delete(id);
    }
}
```

---

## **Ejecución del Proyecto** <a name="ejecución-del-proyecto-español"></a>
1. Clona el repositorio:
   ```bash
   git clone https://github.com/AyelmerCorzoB/SportsCenter.git
   ```
2. Navega al directorio del proyecto:
   ```bash
   cd sportscenter
   ```
3. Compila el proyecto con Maven:
   ```bash
   mvn clean install
   ```
4. Ejecuta la aplicación:
   - Si usas un IDE, ejecuta la clase `Main`.
   - Desde la terminal:
     ```bash
     java -cp target/classes com.sportscenter.Main
     ```

---

## **Escalabilidad** <a name="escalabilidad-español"></a>
El proyecto está diseñado para ser escalable:
- **Módulos Independientes:** Cada entidad y su lógica están encapsuladas en su propio paquete.
- **Uso de DTOs:** Evita exponer las entidades directamente y permite adaptar la salida según sea necesario.
- **Configuración Externa:** La configuración de la base de datos y otros parámetros se manejan en `config.properties`.
- **Caché:** Puedes implementar un sistema de caché manualmente o usar librerías como Caffeine.

---

## **Contribución** <a name="contribución-español"></a>
1. Haz un fork del proyecto.
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

---

## **Autores**
Este proyecto ha sido desarrollado por:

[![Developer](https://img.shields.io/badge/Developer-AyelmerCorzoB-green?style=flat&logo=github)](https://github.com/AyelmerCorzoB)

[![Developer](https://img.shields.io/badge/Developer-JeissonPerez-red?style=flat&logo=github)](https://github.com/stivenpe)

¡Gracias por usar el **Sistema de Gestión de Centros Deportivos**! Si tienes alguna pregunta o sugerencia, no dudes en contactarnos. 😊

</details>


<details>
 <summary><strong><img src="https://flagcdn.com/us.svg" alt="Bandera de Estados Unidos" width="20" height="15"> -- English</strong></summary>


This project is a management system for a sports center that allows managing categories, suppliers, products, customers, employees, purchase orders, sales, customer orders, invoices, and reports. It is designed to be scalable and modular, using a MySQL database and a native Java Maven application.

---

## **Table of Contents**
1. [Requirements](#requirements-english)
2. [Project Structure](#project-structure-english)
3. [Database Configuration](#database-configuration-english)
4. [Entities and Repositories](#entities-and-repositories-english)
5. [Use Cases](#use-cases-english)
6. [Running the Project](#running-the-project-english)
7. [Scalability](#scalability-english)
8. [Contribution](#contribution-english)

---

## **Requirements** <a name="requirements-english"></a>
- **Java JDK 11 or higher**
- **Maven 3.6 or higher**
- **MySQL 8.0 or higher**
- **IDE (Eclipse, IntelliJ IDEA, etc.)**
- **Dependencies:**
  - MySQL Connector/J

---

## **Project Structure** <a name="project-structure-english"></a>
The project is organized into the following packages:

```
src/main/java/com/sportscenter
├───adapter
│   ├───ui                  # User interface (console, web, etc.)
│   └───validations         # Data validations
├───application
│   └───usecase             # Use cases (business logic)
├───config                  # Application configurations
├───domain
│   ├───entities            # Domain entities
│   ├───model               # Data models (DTOs, etc.)
│   └───repository          # Repository interfaces
└───infrastructure
    ├───database            # Database configuration
    └───persistence         # Repository implementations
```

---

## **Database Configuration** <a name="database-configuration-english"></a>
1. **Create the database:**
   ```sql
   CREATE DATABASE IF NOT EXISTS sportscenter;
   USE sportscenter;
   ```
2. **Run the SQL script** provided in the `database.sql` file to create tables and relationships.
3. **Configure the database connection** in `config.properties`:
   ```properties
   db.url=jdbc:mysql://localhost:3306/sportscenter
   db.user=yourUser
   db.password=yourPassword
   ```

---

## **Entities and Repositories** <a name="entities-and-repositories-english"></a>
Each database table is mapped to an entity. Repositories provide CRUD operations.

### Example Entity:
```java
public class Product {
    private int id;
    private String name;
    private String description;
    private double unit_price;
    private String size;
    private String color;
    private int current_stock;
    private int minimum_stock;
    private Date entry_date;
    private Category category;
    private Supplier supplier;

    // Getters and Setters
    // Constructors and toString
}
```

### Example Repository:
```java
public interface ProductRepository {
    void save(Product product);
    Product searchById(int id);
    List<Product> listAll();
    void update(Product product);
    void delete(int id);
}
```

---

## **Use Cases** <a name="use-cases-english"></a>
Use cases implement the business logic. Each important entity has its own use case.

### Example Use Case:
```java
public class ProductUseCase {
    private final ProductRepository repository;
    public ProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public void registerProduct(String name, String description, double unit_price, String size, String color,
            int current_stock, int minimum_stock, Date entry_date, Category category, Supplier supplier) {
        Product product = new Product(name, description, unit_price, size, color, current_stock, minimum_stock,
                entry_date, category, supplier);
        repository.save(product);
    }

    public Product getProduct(int id) {
        return repository.searchById(id);
    }

    public List<Product> listProducts() {
        return repository.listAll();
    }

    public void updateProduct(int id, String name, String description, double unit_price, String size, String color,
            int current_stock, int minimum_stock, Date entry_date, Category category, Supplier supplier) {
        Product product = new Product(id, name, description, unit_price, size, color, current_stock, minimum_stock,
                entry_date, category, supplier);
        repository.update(product);
    }

    public void deleteProduct(int id) {
        repository.delete(id);
    }
}
```

---

## **Running the Project** <a name="running-the-project-english"></a>
1. Clone the repository:
   ```bash
   git clone https://github.com/AyelmerCorzoB/SportsCenter.git
   ```
2. Navigate to the project directory:
   ```bash
   cd sportscenter
   ```
3. Compile the project with Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   - If using an IDE, run the `Main` class.
   - From the terminal:
     ```bash
     java -cp target/classes com.sportscenter.Main
     ```

---

## **Scalability** <a name="scalability-english"></a>
The project is designed to be scalable:
- **Independent Modules:** Each entity and its logic are encapsulated in their own package.
- **Use of DTOs:** Avoids exposing entities directly and allows adapting the output as needed.
- **External Configuration:** Database configuration and other parameters are managed in `config.properties`.
- **Caching:** You can implement a caching system manually or use libraries like Caffeine.

---

## **Contribution** <a name="contribution-english"></a>
1. Fork the project.
2. Create a branch for your feature (`git checkout -b feature/new-feature`).
3. Make your changes and commit (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Open a Pull Request.

---

## **Authors**
This project has been developed by:

[![Developer](https://img.shields.io/badge/Developer-AyelmerCorzoB-green?style=flat&logo=github)](https://github.com/AyelmerCorzoB)

[![Developer](https://img.shields.io/badge/Developer-JeissonPerez-red?style=flat&logo=github)](https://github.com/stivenpe)

Thank you for using the **Sports Center Management System**! If you have any questions or suggestions, feel free to contact us. 😊

</details>

---
