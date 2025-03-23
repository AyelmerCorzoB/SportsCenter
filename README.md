# **Sports Center Management System**

Este proyecto es un sistema de gestión para un centro deportivo que permite administrar categorías, proveedores, productos, clientes, empleados, órdenes de compra, ventas, pedidos de clientes, facturas y reportes. Está diseñado para ser escalable y modular, utilizando una base de datos MySQL y una aplicación Java Maven nativa.

---

## **Tabla de Contenidos**
1. [Requisitos](#requisitos)
2. [Estructura del Proyecto](#estructura-del-proyecto)
3. [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
4. [Entidades y Repositorios](#entidades-y-repositorios)
5. [Casos de Uso](#casos-de-uso)
6. [Ejecución del Proyecto](#ejecución-del-proyecto)
7. [Pruebas](#pruebas)
8. [Escalabilidad](#escalabilidad)
9. [Contribución](#contribución)
10. [Licencia](#licencia)

---

## **Requisitos**
- **Java JDK 11 o superior**
- **Maven 3.6 o superior**
- **MySQL 8.0 o superior**
- **IDE (Eclipse, IntelliJ IDEA, etc.)**
- **Dependencias:**
  - MySQL Connector/J

---

## **Estructura del Proyecto**
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

## **Configuración de la Base de Datos**
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

## **Entidades y Repositorios**
Cada tabla de la base de datos está mapeada a una entidad JPA. Los repositorios extienden `JpaRepository` para proporcionar operaciones CRUD.

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

    // Constuctors and toString
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

## **Casos de Uso**
Los casos de uso implementan la lógica de negocio. Cada entidad importante tiene su propio caso de uso.

### Ejemplo de Caso de Uso:
```java
public class ProductUseCase {
    private final ProductRepository repository;
    public ProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public void registerProduct(String name, String description, double unit_price, String size, String color,
            int current_stock,
            int minimum_stock, Date entry_date, Category category, Supplier supplier) {
        Product product = new Product(name, description, unit_price, size, color, current_stock, minimum_stock,
                entry_date, category, supplier);
        repository.save(product);
    }

    public Product getProduct(int id) {
        return repository.searchById(id);
    }

    public List<Product> ListProducts() {
        return repository.listAll();
    }

    public void updateProduct(int id, String name, String description, double unit_price, String size, String color,
    int current_stock, int minimum_stock, Date entry_date, Category category, Supplier supplier){
        Product product = new Product(id, name, description, unit_price, size, color, current_stock, minimum_stock, entry_date, category, supplier);
        repository.update(product);
    }

    public void deleteProduct(int id) {
        repository.delete(id);
    }
}
```

---
## Ejecución del Proyecto
1. Clona el repositorio:
````bash
git clone https://github.com/AyelmerCorzoB/SportsCenter.git
````
2. Navega al directorio del proyecto:

````bash
cd sportscenter
````
3. Compila el proyecto con Maven:
````bash
mvn clean install
````
4. Ejecuta la aplicación:

Si estás usando un IDE como IntelliJ IDEA o Eclipse, simplemente ejecuta la clase Main desde el IDE.

Si prefieres ejecutar desde la terminal, usa el siguiente comando:

````bash
java -cp target/classes com.sportscenter.Main
````
---

## **Escalabilidad**
El proyecto está diseñado para ser escalable:
- **Módulos Independientes:** Cada entidad y su lógica están encapsuladas en su propio paquete.
- **Uso de DTOs:** Evita exponer las entidades directamente y permite adaptar la salida según sea necesario.
- **Configuración Externa:** La configuración de la base de datos y otros parámetros se manejan en `config.properties`.
- **Caché:** puedes implementar un sistema de caché manualmente utilizando estructuras de datos como HashMap o librerías como Caffeine o Ehcache.

---

## **Contribución**
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

¡Gracias por usar el **Sports Center Management System**! Si tienes alguna pregunta o sugerencia, no dudes en contactarnos. 😊
