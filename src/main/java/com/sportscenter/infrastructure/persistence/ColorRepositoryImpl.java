package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.Color;
import com.sportscenter.domain.repository.ColorRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColorRepositoryImpl implements ColorRepository {

    private final ConnectionDb connection;

    public ColorRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(Color color) {
        String sql = "INSERT INTO Color (name, hex_code) VALUES (?, ?)";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, color.getName());
            stmt.setString(2, color.getHexCode());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Color findById(int id) {
        String sql = "SELECT * FROM Color WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Color color = new Color();
                color.setId(rs.getInt("id"));
                color.setName(rs.getString("name"));
                color.setHexCode(rs.getString("hex_code"));
                return color;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Color> findAll() {
        List<Color> colors = new ArrayList<>();
        String sql = "SELECT * FROM Color";
        
        try (Connection conn = connection.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Color color = new Color();
                color.setId(rs.getInt("id"));
                color.setName(rs.getString("name"));
                color.setHexCode(rs.getString("hex_code"));
                colors.add(color);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }

    @Override
    public void update(Color color) {
        String sql = "UPDATE Color SET name = ?, hex_code = ? WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, color.getName());
            stmt.setString(2, color.getHexCode());
            stmt.setInt(3, color.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Color WHERE id = ?";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}