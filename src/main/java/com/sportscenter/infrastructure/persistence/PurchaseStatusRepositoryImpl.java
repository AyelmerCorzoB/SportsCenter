package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.PurchaseStatus;
import com.sportscenter.domain.repository.PurchaseStatusRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseStatusRepositoryImpl implements PurchaseStatusRepository {

    private final ConnectionDb connection;

    public PurchaseStatusRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(PurchaseStatus purchaseStatus) {
        String sql = "INSERT INTO PurchaseStatus (status_name, description) VALUES (?, ?)";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, purchaseStatus.getStatusName());
            stmt.setString(2, purchaseStatus.getDescription());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PurchaseStatus findById(int id) {
        String sql = "SELECT * FROM PurchaseStatus WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                PurchaseStatus purchaseStatus = new PurchaseStatus();
                purchaseStatus.setId(rs.getInt("id"));
                purchaseStatus.setStatusName(rs.getString("status_name"));
                purchaseStatus.setDescription(rs.getString("description"));
                return purchaseStatus;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PurchaseStatus> findAll() {
        List<PurchaseStatus> purchaseStatuses = new ArrayList<>();
        String sql = "SELECT * FROM PurchaseStatus";
        
        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                PurchaseStatus purchaseStatus = new PurchaseStatus();
                purchaseStatus.setId(rs.getInt("id"));
                purchaseStatus.setStatusName(rs.getString("status_name"));
                purchaseStatus.setDescription(rs.getString("description"));
                purchaseStatuses.add(purchaseStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchaseStatuses;
    }

    @Override
    public void update(PurchaseStatus purchaseStatus) {
        String sql = "UPDATE PurchaseStatus SET status_name = ?, description = ? WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, purchaseStatus.getStatusName());
            stmt.setString(2, purchaseStatus.getDescription());
            stmt.setInt(3, purchaseStatus.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM PurchaseStatus WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}