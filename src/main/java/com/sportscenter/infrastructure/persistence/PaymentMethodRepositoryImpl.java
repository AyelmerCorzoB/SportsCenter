package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.PaymentMethod;
import com.sportscenter.domain.repository.PaymentMethodRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

    private final ConnectionDb connection;

    public PaymentMethodRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(PaymentMethod paymentMethod) {
        String sql = "INSERT INTO PaymentMethod (method_name, description) VALUES (?, ?)";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, paymentMethod.getMethodName());
            stmt.setString(2, paymentMethod.getDescription());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PaymentMethod findById(int id) {
        String sql = "SELECT * FROM PaymentMethod WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setId(rs.getInt("id"));
                paymentMethod.setMethodName(rs.getString("method_name"));
                paymentMethod.setDescription(rs.getString("description"));
                return paymentMethod;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PaymentMethod> findAll() {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        String sql = "SELECT * FROM PaymentMethod";
        
        try (Connection conn = connection.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setId(rs.getInt("id"));
                paymentMethod.setMethodName(rs.getString("method_name"));
                paymentMethod.setDescription(rs.getString("description"));
                paymentMethods.add(paymentMethod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentMethods;
    }

    @Override
    public void update(PaymentMethod paymentMethod) {
        String sql = "UPDATE PaymentMethod SET method_name = ?, description = ? WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, paymentMethod.getMethodName());
            stmt.setString(2, paymentMethod.getDescription());
            stmt.setInt(3, paymentMethod.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM PaymentMethod WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}