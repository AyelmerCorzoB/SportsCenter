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
        String sql = "INSERT INTO SaleDetail (sale_id, product_id, quantity, unit_price) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, detail.getSaleId());
            stmt.setInt(2, detail.getProductId());
            stmt.setInt(3, detail.getQuantity());
            stmt.setDouble(4, detail.getUnitPrice());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                SaleDetail detail = new SaleDetail();
                detail.setId(rs.getInt("id"));
                detail.setSaleId(rs.getInt("sale_id"));
                detail.setProductId(rs.getInt("product_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setUnitPrice(rs.getDouble("unit_price"));
                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    @Override
    public void deleteBySaleId(int saleId) {
        String sql = "DELETE FROM SaleDetail WHERE sale_id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, saleId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}