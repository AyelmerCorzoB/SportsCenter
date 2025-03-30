package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.CustomerOrder;
import com.sportscenter.domain.repository.CustomerOrderRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderRepositoryImpl implements CustomerOrderRepository {

    private final ConnectionDb connection;

    public CustomerOrderRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(CustomerOrder customerOrder) {
        String sql = "INSERT INTO CustomerOrder (customer_id, order_date, status_id, total, user_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerOrder.getCustomerId());
            stmt.setDate(2, Date.valueOf(customerOrder.getOrderDate()));
            stmt.setInt(3, customerOrder.getStatusId());
            stmt.setDouble(4, customerOrder.getTotal());
            stmt.setInt(5, customerOrder.getUserId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CustomerOrder findById(int id) {
        String sql = "SELECT * FROM CustomerOrder WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setId(rs.getInt("id"));
                customerOrder.setCustomerId(rs.getInt("customer_id"));
                customerOrder.setOrderDate(rs.getDate("order_date").toLocalDate());
                customerOrder.setStatusId(rs.getInt("status_id"));
                customerOrder.setTotal(rs.getDouble("total"));
                customerOrder.setUserId(rs.getInt("user_id"));
                return customerOrder;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerOrder> findAll() {
        List<CustomerOrder> customerOrders = new ArrayList<>();
        String sql = "SELECT co.*, c.name AS customer_name, os.status_name, u.username " +
                "FROM CustomerOrder co " +
                "JOIN Customer c ON co.customer_id = c.id " +
                "JOIN OrderStatus os ON co.status_id = os.id " +
                "JOIN User u ON co.user_id = u.id";

        try (Connection conn = connection.getConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CustomerOrder customerOrder = new CustomerOrder();
                customerOrder.setId(rs.getInt("id"));
                customerOrder.setCustomerId(rs.getInt("customer_id"));
                customerOrder.setCustomerName(rs.getString("customer_name"));
                customerOrder.setOrderDate(rs.getDate("order_date").toLocalDate());
                customerOrder.setStatusId(rs.getInt("status_id"));
                customerOrder.setStatusName(rs.getString("status_name"));
                customerOrder.setTotal(rs.getDouble("total"));
                customerOrder.setUserId(rs.getInt("user_id"));
                customerOrder.setUserName(rs.getString("username"));
                customerOrders.add(customerOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("|=================================================================|");
        System.out.println("|                       LISTADO DE PEDIDOS                        |");
        System.out.println("|=================================================================|");
        System.out.printf("| %-4s | %-20s | %-12s | %-15s | %-8s |\n",
                "ID", "CLIENTE", "FECHA", "ESTADO", "TOTAL");
        System.out.println("|-----------------------------------------------------------------|");

        for (CustomerOrder order : customerOrders) {
            System.out.printf("| %-4d | %-20s | %-12s | %-15s | $%-7.2f |\n",
                    order.getId(),
                    order.getCustomerName(),
                    order.getOrderDate(),
                    order.getStatusName(),
                    order.getTotal());
        }
        System.out.println("|=================================================================|");

        return customerOrders;
    }

    @Override
    public void update(CustomerOrder customerOrder) {
        String sql = "UPDATE CustomerOrder SET customer_id = ?, order_date = ?, status_id = ?, " +
                "total = ? WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerOrder.getCustomerId());
            stmt.setDate(2, Date.valueOf(customerOrder.getOrderDate()));
            stmt.setInt(3, customerOrder.getStatusId());
            stmt.setDouble(4, customerOrder.getTotal());
            stmt.setInt(5, customerOrder.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM CustomerOrder WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}