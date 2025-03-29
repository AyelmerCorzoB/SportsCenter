package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.Product;
import com.sportscenter.domain.repository.ProductRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final ConnectionDb connection;

    public ProductRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(Product product) {
        save(product, -1); // Valor por defecto si no se proporciona userId
    }

    // Nuevo método sobrecargado
    public void save(Product product, int userId) {
        String productSql = "INSERT INTO product (name, description, unit_price, size, current_stock, category_id, supplier_id, color_id, minimum_stock, entry_date) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = connection.getConexion()) {
            try (PreparedStatement productStmt = conn.prepareStatement(productSql)) {
                productStmt.setString(1, product.getName());
                productStmt.setString(2, product.getDescription());
                productStmt.setDouble(3, product.getUnitPrice());
                productStmt.setString(4, product.getSize());
                productStmt.setInt(5, product.getCurrentStock());
                productStmt.setInt(6, product.getCategoryId());
                productStmt.setInt(7, product.getSupplierId());
                productStmt.setInt(8, product.getColorId());
                productStmt.setInt(9, product.getMinimumStock());
                productStmt.setDate(10, Date.valueOf(product.getEntryDate()));
    
                productStmt.executeUpdate();
            }
        } catch (SQLException e) {
            
        }
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setUnitPrice(rs.getDouble("unit_price"));
                product.setSize(rs.getString("size"));
                product.setCurrentStock(rs.getInt("current_stock"));
                product.setCategoryId(rs.getInt("category_id"));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = """
                SELECT p.*, 
                       c.name AS category_name,
                       s.name AS supplier_name,
                       col.name AS color_name
                FROM Product p
                LEFT JOIN Category c ON p.category_id = c.id
                LEFT JOIN Supplier s ON p.supplier_id = s.id
                LEFT JOIN Color col ON p.color_id = col.id
                ORDER BY p.name
                """;

        try (Connection conn = connection.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setUnitPrice(rs.getDouble("unit_price"));
                product.setSize(rs.getString("size"));
                product.setCurrentStock(rs.getInt("current_stock"));
                product.setMinimumStock(rs.getInt("minimum_stock"));
                product.setEntryDate(rs.getDate("entry_date").toLocalDate());
                product.setCategoryId(rs.getInt("category_id"));
                product.setSupplierId(rs.getInt("supplier_id"));
                product.setColorId(rs.getInt("color_id"));

                // Relaciones
                product.setCategoryName(rs.getString("category_name"));
                product.setSupplierName(rs.getString("supplier_name"));
                product.setColorName(rs.getString("color_name"));

                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE Product SET name = ?, description = ?, unit_price = ?, " +
                "size = ?, current_stock = ?, category_id = ? WHERE id = ?";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getUnitPrice());
            stmt.setString(4, product.getSize());
            stmt.setInt(5, product.getCurrentStock());
            stmt.setInt(6, product.getCategoryId());
            stmt.setInt(7, product.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Product WHERE id = ?";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
