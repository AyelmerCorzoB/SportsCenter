package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.Employee;
import com.sportscenter.domain.repository.EmployeeRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final ConnectionDb connection;

    public EmployeeRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO Employee (name, position, phone, email, user_id) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getPosition());
            stmt.setString(3, employee.getPhone());
            stmt.setString(4, employee.getEmail());
            stmt.setInt(5, employee.getUserId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee searchById(int id) {
        String sql = "SELECT * FROM Employee WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setPosition(rs.getString("position"));
                employee.setPhone(rs.getString("phone"));
                employee.setEmail(rs.getString("email"));
                employee.setUserId(rs.getInt("user_id"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> listAll() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        
        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setPosition(rs.getString("position"));
                employee.setPhone(rs.getString("phone"));
                employee.setEmail(rs.getString("email"));
                employee.setUserId(rs.getInt("user_id"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE Employee SET name = ?, position = ?, phone = ?, email = ?, user_id = ? WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getPosition());
            stmt.setString(3, employee.getPhone());
            stmt.setString(4, employee.getEmail());
            stmt.setInt(5, employee.getUserId());
            stmt.setInt(6, employee.getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Employee WHERE id = ?";
        
        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}