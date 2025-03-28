package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.Sale;
import com.sportscenter.domain.repository.SaleRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleRepositoryImpl implements SaleRepository {

    private final ConnectionDb connection;

    public SaleRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(Sale sale) {
        String sql = "INSERT INTO Sale (customer_id, sale_date, payment_method_id, total, user_id) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, sale.getCustomerId());
            stmt.setDate(2, Date.valueOf(sale.getSaleDate()));
            stmt.setInt(3, sale.getPaymentMethodId());
            stmt.setDouble(4, sale.getTotal());
            stmt.setInt(5, sale.getUserId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Sale findById(int id) {
        String sql = "SELECT * FROM Sale WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Sale sale = new Sale();
                sale.setId(rs.getInt("id"));
                sale.setCustomerId(rs.getInt("customer_id"));
                sale.setSaleDate(rs.getDate("sale_date").toLocalDate());
                sale.setPaymentMethodId(rs.getInt("payment_method_id"));
                sale.setTotal(rs.getDouble("total"));
                sale.setUserId(rs.getInt("user_id"));
                return sale;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Sale> findAll() {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM Sale";
        
        try (Connection conn = connection.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setId(rs.getInt("id"));
                sale.setCustomerId(rs.getInt("customer_id"));
                sale.setSaleDate(rs.getDate("sale_date").toLocalDate());
                sale.setPaymentMethodId(rs.getInt("payment_method_id"));
                sale.setTotal(rs.getDouble("total"));
                sale.setUserId(rs.getInt("user_id"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
public List<Sale> findByUserId(int userId) {
    List<Sale> sales = new ArrayList<>();
    String sql = "SELECT * FROM Sale WHERE user_id = ?";
    
    try (Connection conn = connection.getConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Sale sale = new Sale();
            sale.setId(rs.getInt("id"));
            sale.setCustomerId(rs.getInt("customer_id"));
            sale.setSaleDate(rs.getDate("sale_date").toLocalDate());
            sale.setPaymentMethodId(rs.getInt("payment_method_id"));
            sale.setTotal(rs.getDouble("total"));
            sale.setUserId(rs.getInt("user_id"));
            sales.add(sale);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return sales;
}
    @Override
    public void update(Sale sale) {
        String sql = "UPDATE Sale SET customer_id = ?, sale_date = ?, payment_method_id = ?, " +
                     "total = ? WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, sale.getCustomerId());
            stmt.setDate(2, Date.valueOf(sale.getSaleDate()));
            stmt.setInt(3, sale.getPaymentMethodId());
            stmt.setDouble(4, sale.getTotal());
            stmt.setInt(5, sale.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Sale WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}