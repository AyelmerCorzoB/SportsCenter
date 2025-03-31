package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.Order;
import com.sportscenter.domain.entities.OrderStatus;
import com.sportscenter.domain.repository.OrderRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private final ConnectionDb connection;

    public OrderRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> findByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getDate("order_date").toLocalDate(),
                        OrderStatus.valueOf(rs.getString("status").toUpperCase()));
            }
        } catch (SQLException e) {
            System.err.println("X Error al obtener órdenes por usuario: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("X Error al convertir estado de orden: " + e.getMessage());
        }
        return orders;
    }
}
