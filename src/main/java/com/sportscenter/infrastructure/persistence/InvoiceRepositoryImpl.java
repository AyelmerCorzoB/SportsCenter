package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.Invoice;
import com.sportscenter.domain.repository.InvoiceRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final ConnectionDb connection;

    public InvoiceRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(Invoice invoice) {
        String sql = "INSERT INTO Invoice (sale_id, invoice_number, issue_date, total_amount, taxes) " +
                   "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, invoice.getSaleId());
            stmt.setString(2, invoice.getInvoiceNumber());
            stmt.setDate(3, Date.valueOf(invoice.getIssueDate()));
            stmt.setDouble(4, invoice.getTotalAmount());
            stmt.setDouble(5, invoice.getTaxes());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Invoice searchById(int id) {
        String sql = "SELECT * FROM Invoice WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setSaleId(rs.getInt("sale_id"));
                invoice.setInvoiceNumber(rs.getString("invoice_number"));
                invoice.setIssueDate(rs.getDate("issue_date").toLocalDate());
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoice.setTaxes(rs.getDouble("taxes"));
                return invoice;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Invoice> listAll() {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM Invoice";
        
        try (Connection conn = connection.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setSaleId(rs.getInt("sale_id"));
                invoice.setInvoiceNumber(rs.getString("invoice_number"));
                invoice.setIssueDate(rs.getDate("issue_date").toLocalDate());
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoice.setTaxes(rs.getDouble("taxes"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    @Override
    public void update(Invoice invoice) {
        String sql = "UPDATE Invoice SET sale_id = ?, invoice_number = ?, issue_date = ?, " +
                   "total_amount = ?, taxes = ? WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, invoice.getSaleId());
            stmt.setString(2, invoice.getInvoiceNumber());
            stmt.setDate(3, Date.valueOf(invoice.getIssueDate()));
            stmt.setDouble(4, invoice.getTotalAmount());
            stmt.setDouble(5, invoice.getTaxes());
            stmt.setInt(6, invoice.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Invoice WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}