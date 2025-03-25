package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.User;
import com.sportscenter.domain.repository.UserRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final ConnectionDb connection;

    public UserRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO User (username, email, password, role, active) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setBoolean(5, user.isActive());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM User WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setActive(rs.getBoolean("active"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";
        
        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setActive(rs.getBoolean("active"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE User SET username = ?, email = ?, password = ?, role = ?, active = ? WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setBoolean(5, user.isActive());
            stmt.setInt(6, user.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM User WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}