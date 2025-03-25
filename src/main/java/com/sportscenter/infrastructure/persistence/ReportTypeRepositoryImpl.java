package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.ReportType;
import com.sportscenter.domain.repository.ReportTypeRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportTypeRepositoryImpl implements ReportTypeRepository {

    private final ConnectionDb connection;

    public ReportTypeRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(ReportType reportType) {
        String sql = "INSERT INTO ReportType (type_name, description) VALUES (?, ?)";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, reportType.getTypeName());
            stmt.setString(2, reportType.getDescription());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ReportType findById(int id) {
        String sql = "SELECT * FROM ReportType WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                ReportType reportType = new ReportType();
                reportType.setId(rs.getInt("id"));
                reportType.setTypeName(rs.getString("type_name"));
                reportType.setDescription(rs.getString("description"));
                return reportType;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ReportType> findAll() {
        List<ReportType> reportTypes = new ArrayList<>();
        String sql = "SELECT * FROM ReportType";
        
        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ReportType reportType = new ReportType();
                reportType.setId(rs.getInt("id"));
                reportType.setTypeName(rs.getString("type_name"));
                reportType.setDescription(rs.getString("description"));
                reportTypes.add(reportType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportTypes;
    }

    @Override
    public void update(ReportType reportType) {
        String sql = "UPDATE ReportType SET type_name = ?, description = ? WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, reportType.getTypeName());
            stmt.setString(2, reportType.getDescription());
            stmt.setInt(3, reportType.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM ReportType WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}