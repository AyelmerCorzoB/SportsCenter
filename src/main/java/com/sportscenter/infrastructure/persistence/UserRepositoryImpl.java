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
    public User save(User user) {
        String sql = "INSERT INTO User (username, password, role, active, created_at) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setBoolean(4, user.isActive());
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                    return user;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM User WHERE id = ?";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by id: " + e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection conn = connection.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all users: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE User SET username = ?, password = ?, role = ?, active = ?, last_login = ? WHERE id = ?";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setBoolean(4, user.isActive());
            stmt.setTimestamp(5, user.getLast_login());
            stmt.setInt(6, user.getId());

            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM User WHERE id = ?";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Deleting user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user: " + e.getMessage(), e);
        }
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM User WHERE username = ?";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by username: " + e.getMessage(), e);
        }
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setActive(rs.getBoolean("active"));
        user.setCreated_at(rs.getTimestamp("created_at"));
        user.setLast_login(rs.getTimestamp("last_login"));
        return user;
    }
}