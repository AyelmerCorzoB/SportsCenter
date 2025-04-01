package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.Customer;
import com.sportscenter.domain.repository.CustomerRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    private final ConnectionDb connection;

    public CustomerRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(Customer customer) {

        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono es obligatorio");
        }
        if (customer.getRegistrationDate() == null) {
            customer.setRegistrationDate(LocalDate.now());
        }

        String sql = "INSERT INTO Customer (customer_type_id, name, identity_document, phone, address, registration_date) "
                +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customer.getCustomerTypeId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getIdentityDocument());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getAddress());
            stmt.setDate(6, Date.valueOf(customer.getRegistrationDate()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public Customer findById(int id) {
        String sql = "SELECT * FROM Customer WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setCustomerTypeId(rs.getInt("customer_type_id"));
                customer.setName(rs.getString("name"));
                customer.setIdentityDocument(rs.getString("identity_document"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setRegistrationDate(
                        rs.getDate("registration_date") != null ? rs.getDate("registration_date").toLocalDate() : null);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";

        try (Connection conn = connection.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setCustomerTypeId(rs.getInt("customer_type_id"));
                customer.setName(rs.getString("name"));
                customer.setIdentityDocument(rs.getString("identity_document"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setRegistrationDate(
                        rs.getDate("registration_date") != null ? rs.getDate("registration_date").toLocalDate() : null);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE Customer SET customer_type_id = ?, name = ?, identity_document = ?, " +
                "phone = ?, address = ? WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customer.getCustomerTypeId());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getIdentityDocument());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getAddress());
            stmt.setInt(6, customer.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarCliente(int customerTypeId, String name, String identityDocument, String phone, String address,
            LocalDate registrationDate, int createdBy) {

        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de teléfono es obligatorio");
        }

        String sql = "INSERT INTO Customer (customer_type_id, name, identity_document, " +
                "phone, address, registration_date, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, customerTypeId);
            stmt.setString(2, name);
            stmt.setString(3, identityDocument);
            stmt.setString(4, phone);
            stmt.setString(5, address);
            stmt.setDate(6, Date.valueOf(registrationDate));
            stmt.setInt(7, createdBy);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo guardar el cliente, ninguna fila afectada.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    System.out.println("Cliente registrado con ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar cliente: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Customer WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}