package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.OrderStatus;
import com.sportscenter.domain.repository.OrderStatusRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusRepositoryImpl implements OrderStatusRepository {

    private final ConnectionDb connection;

    public OrderStatusRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(OrderStatus orderStatus) {
        String sql = "INSERT INTO OrderStatus (status_name, description) VALUES (?, ?)";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, orderStatus.getStatusName());
            stmt.setString(2, orderStatus.getDescription());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderStatus findById(int id) {
        String sql = "SELECT * FROM OrderStatus WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setId(rs.getInt("id"));
                orderStatus.setStatusName(rs.getString("status_name"));
                orderStatus.setDescription(rs.getString("description"));
                return orderStatus;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderStatus> findAll() {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        String sql = "SELECT * FROM OrderStatus";
        
        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setId(rs.getInt("id"));
                orderStatus.setStatusName(rs.getString("status_name"));
                orderStatus.setDescription(rs.getString("description"));
                orderStatuses.add(orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderStatuses;
    }

    @Override
    public void update(OrderStatus orderStatus) {
        String sql = "UPDATE OrderStatus SET status_name = ?, description = ? WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, orderStatus.getStatusName());
            stmt.setString(2, orderStatus.getDescription());
            stmt.setInt(3, orderStatus.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM OrderStatus WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}