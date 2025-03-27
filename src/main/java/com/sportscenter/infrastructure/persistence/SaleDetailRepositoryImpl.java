package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.SaleDetail;
import com.sportscenter.domain.repository.SaleDetailRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDetailRepositoryImpl implements SaleDetailRepository {

    private final ConnectionDb connection;

    public SaleDetailRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(SaleDetail detail) {
        String sql = "INSERT INTO SaleDetail (sale_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, detail.getSaleId());
            stmt.setInt(2, detail.getProductId());
            stmt.setInt(3, detail.getQuantity());
            stmt.setDouble(4, detail.getUnitPrice());
            
            stmt.executeUpdate();
            
            // Obtener el ID generado automáticamente
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                detail.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el detalle de venta", e);
        }
    }

    @Override
    public void update(SaleDetail detail) {
        String sql = "UPDATE SaleDetail SET sale_id = ?, product_id = ?, quantity = ?, unit_price = ? WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, detail.getSaleId());
            stmt.setInt(2, detail.getProductId());
            stmt.setInt(3, detail.getQuantity());
            stmt.setDouble(4, detail.getUnitPrice());
            stmt.setInt(5, detail.getId());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No se encontró el detalle de venta con ID: " + detail.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el detalle de venta", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM SaleDetail WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No se encontró el detalle de venta con ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el detalle de venta", e);
        }
    }

    @Override
    public void deleteBySaleId(int saleId) {
        String sql = "DELETE FROM SaleDetail WHERE sale_id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, saleId);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar los detalles de venta por ID de venta", e);
        }
    }

    @Override
    public SaleDetail findById(int id) {
        String sql = "SELECT * FROM SaleDetail WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapToSaleDetail(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el detalle de venta por ID", e);
        }
        return null;
    }

    @Override
    public List<SaleDetail> findBySaleId(int saleId) {
        List<SaleDetail> details = new ArrayList<>();
        String sql = "SELECT * FROM SaleDetail WHERE sale_id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, saleId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                details.add(mapToSaleDetail(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar los detalles de venta por ID de venta", e);
        }
        return details;
    }

    @Override
    public List<SaleDetail> findAll() {
        List<SaleDetail> details = new ArrayList<>();
        String sql = "SELECT * FROM SaleDetail";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                details.add(mapToSaleDetail(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar todos los detalles de venta", e);
        }
        return details;
    }

    private SaleDetail mapToSaleDetail(ResultSet rs) throws SQLException {
        SaleDetail detail = new SaleDetail();
        detail.setId(rs.getInt("id"));
        detail.setSaleId(rs.getInt("sale_id"));
        detail.setProductId(rs.getInt("product_id"));
        detail.setQuantity(rs.getInt("quantity"));
        detail.setUnitPrice(rs.getDouble("unit_price"));
        detail.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return detail;
    }
}