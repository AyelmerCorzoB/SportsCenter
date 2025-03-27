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
        String sql = "INSERT INTO saledetail (sale_id, product_id, quantity, unit_price, subtotal) " +
                    "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, detail.getSaleId());
            stmt.setInt(2, detail.getProductId());
            stmt.setInt(3, detail.getQuantity());
            stmt.setDouble(4, detail.getUnitPrice());
            stmt.setDouble(5, detail.getSubtotal());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating sale detail failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    detail.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating sale detail failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el detalle de venta", e);
        }
    }

    @Override
    public void update(SaleDetail detail) {
        String sql = "UPDATE saledetail SET " +
                     "sale_id = ?, product_id = ?, quantity = ?, " +
                     "unit_price = ?, subtotal = ? " +
                     "WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, detail.getSaleId());
            stmt.setInt(2, detail.getProductId());
            stmt.setInt(3, detail.getQuantity());
            stmt.setDouble(4, detail.getUnitPrice());
            stmt.setDouble(5, detail.getSubtotal());
            stmt.setInt(6, detail.getId());
            
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
        String sql = "DELETE FROM saledetail WHERE id = ?";
        
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
        String sql = "DELETE FROM saledetail WHERE sale_id = ?";
        
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
        String sql = "SELECT id, sale_id, product_id, quantity, unit_price, subtotal, created_at " +
                     "FROM saledetail WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSaleDetail(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el detalle de venta por ID", e);
        }
        return null;
    }

    @Override
    public List<SaleDetail> findBySaleId(int saleId) {
        String sql = "SELECT id, sale_id, product_id, quantity, unit_price, subtotal, created_at " +
                     "FROM saledetail WHERE sale_id = ?";
        
        List<SaleDetail> saleDetails = new ArrayList<>();
    
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, saleId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    saleDetails.add(mapResultSetToSaleDetail(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar detalles de venta por ID de venta", e);
        }
        return saleDetails;
    }

    @Override
    public List<SaleDetail> findAll() {
        String sql = "SELECT id, sale_id, product_id, quantity, unit_price, subtotal, created_at " +
                     "FROM saledetail";
        
        List<SaleDetail> saleDetails = new ArrayList<>();
    
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                saleDetails.add(mapResultSetToSaleDetail(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los detalles de venta", e);
        }
        return saleDetails;
    }

    private SaleDetail mapResultSetToSaleDetail(ResultSet rs) throws SQLException {
        SaleDetail detail = new SaleDetail();
        detail.setId(rs.getInt("id"));
        detail.setSaleId(rs.getInt("sale_id"));
        detail.setProductId(rs.getInt("product_id"));
        detail.setQuantity(rs.getInt("quantity"));
        detail.setUnitPrice(rs.getDouble("unit_price"));
        
        Timestamp timestamp = rs.getTimestamp("created_at");
        if (timestamp != null) {
            detail.setCreatedAt(timestamp.toLocalDateTime());
        }
        
        return detail;
    }
}