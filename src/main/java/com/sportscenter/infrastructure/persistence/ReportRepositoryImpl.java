package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.Report;
import com.sportscenter.domain.repository.ReportRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {

    private final ConnectionDb connection;

    public ReportRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(Report report) {
        String sql = "INSERT INTO Report (report_type_id, generation_date, user_id, file_path) " +
                   "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, report.getReportTypeId());
            stmt.setDate(2, Date.valueOf(report.getGenerationDate()));
            stmt.setInt(3, report.getUserId());
            stmt.setString(4, report.getFilePath());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Report searchById(int id) {
        String sql = "SELECT * FROM Report WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Report report = new Report();
                report.setId(rs.getInt("id"));
                report.setReportTypeId(rs.getInt("report_type_id"));
                report.setGenerationDate(rs.getDate("generation_date").toLocalDate());
                report.setUserId(rs.getInt("user_id"));
                report.setFilePath(rs.getString("file_path"));
                return report;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Report> listAll() {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM Report";
        
        try (Connection conn = connection.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Report report = new Report();
                report.setId(rs.getInt("id"));
                report.setReportTypeId(rs.getInt("report_type_id"));
                report.setGenerationDate(rs.getDate("generation_date").toLocalDate());
                report.setUserId(rs.getInt("user_id"));
                report.setFilePath(rs.getString("file_path"));
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public void update(Report report) {
        String sql = "UPDATE Report SET report_type_id = ?, generation_date = ?, " +
                   "user_id = ?, file_path = ? WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, report.getReportTypeId());
            stmt.setDate(2, Date.valueOf(report.getGenerationDate()));
            stmt.setInt(3, report.getUserId());
            stmt.setString(4, report.getFilePath());
            stmt.setInt(5, report.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Report WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}