package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.CustomerType;
import com.sportscenter.domain.repository.CustomerTypeRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeRepositoryImpl implements CustomerTypeRepository {

    private final ConnectionDb connection;

    public CustomerTypeRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(CustomerType customerType) {
        String sql = "INSERT INTO CustomerType (type_name, description) VALUES (?, ?)";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerType.getTypeName());
            stmt.setString(2, customerType.getDescription());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CustomerType findById(int id) {
        String sql = "SELECT * FROM CustomerType WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                CustomerType customerType = new CustomerType();
                customerType.setId(rs.getInt("id"));
                customerType.setTypeName(rs.getString("type_name"));
                customerType.setDescription(rs.getString("description"));
                return customerType;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerType> findAll() {
        List<CustomerType> customerTypes = new ArrayList<>();
        String sql = "SELECT * FROM CustomerType";

        try (Connection conn = connection.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CustomerType customerType = new CustomerType();
                customerType.setId(rs.getInt("id"));
                customerType.setTypeName(rs.getString("type_name"));
                customerType.setDescription(rs.getString("description"));
                customerTypes.add(customerType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("|-------------------------------------------------------------------------|");
        System.out.println("|                          TIPOS DE CLIENTES                              |");
        for (CustomerType customerType : customerTypes) {

            System.out.println("| ID: " + customerType.getId() + "\n" +
                    "| Nombre: " + customerType.getTypeName() + "\n" +
                    "| Descripcion: " + customerType.getDescription());
            System.out.println("|-------------------------------------------------------------------------|");
        }
        return customerTypes;
    }

    @Override
    public void update(CustomerType customerType) {
        String sql = "UPDATE CustomerType SET type_name = ?, description = ? WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerType.getTypeName());
            stmt.setString(2, customerType.getDescription());
            stmt.setInt(3, customerType.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM CustomerType WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}