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
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, invoice.getSaleId());
            stmt.setString(2, invoice.getInvoiceNumber());
            stmt.setDate(3, Date.valueOf(invoice.getIssueDate()));
            stmt.setDouble(4, invoice.getTotalAmount());
            stmt.setDouble(5, invoice.getTaxes());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating invoice failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    invoice.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving invoice", e);
        }
    }

    @Override
    public Invoice searchById(int id) {
        String sql = """
                SELECT i.*,
                    s.sale_date, s.total AS sale_total,
                    c.name AS customer_name, c.identity_document AS customer_doc,
                    pm.method_name AS payment_method
                FROM Invoice i
                JOIN Sale s ON i.sale_id = s.id
                JOIN Customer c ON s.customer_id = c.id
                JOIN PaymentMethod pm ON s.payment_method_id = pm.id
                WHERE i.id = ?
                """;

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapInvoiceWithDetails(rs);
            } else {
                System.out.println("No se encontró ninguna factura con el ID: " + id);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding invoice by id: " + id, e);
        }
    }

    private Invoice mapInvoiceWithDetails(ResultSet rs) throws SQLException {
        Invoice invoice = new Invoice();

        invoice.setId(rs.getInt("id"));
        invoice.setSaleId(rs.getInt("sale_id"));
        invoice.setInvoiceNumber(rs.getString("invoice_number"));
        invoice.setIssueDate(rs.getDate("issue_date").toLocalDate());
        invoice.setTotalAmount(rs.getDouble("total_amount"));
        invoice.setTaxes(rs.getDouble("taxes"));

        invoice.setSaleDate(rs.getDate("sale_date").toLocalDate());
        invoice.setSaleTotal(rs.getDouble("sale_total"));
        invoice.setCustomerName(rs.getString("customer_name"));
        invoice.setCustomerDocument(rs.getString("customer_doc"));
        invoice.setPaymentMethod(rs.getString("payment_method"));

        return invoice;
    }

    @Override
    public List<Invoice> listAll() {
        String sql = """
                SELECT i.*,
                    s.sale_date, s.total AS sale_total,
                    c.name AS customer_name, c.identity_document AS customer_doc,
                    pm.method_name AS payment_method,
                    (SELECT COUNT(*) FROM SaleDetail sd WHERE sd.sale_id = s.id) AS items_count
                FROM Invoice i
                JOIN Sale s ON i.sale_id = s.id
                JOIN Customer c ON s.customer_id = c.id
                JOIN PaymentMethod pm ON s.payment_method_id = pm.id
                ORDER BY i.issue_date DESC, i.invoice_number
                """;

        List<Invoice> invoices = new ArrayList<>();

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

                invoice.setSaleDate(rs.getDate("sale_date").toLocalDate());
                invoice.setSaleTotal(rs.getDouble("sale_total"));
                invoice.setCustomerName(rs.getString("customer_name"));
                invoice.setCustomerDocument(rs.getString("customer_doc"));
                invoice.setPaymentMethod(rs.getString("payment_method"));

                try {
                    invoice.setItemsCount(rs.getInt("items_count"));
                } catch (SQLException e) {
                    invoice.setItemsCount(0);
                }

                invoices.add(invoice);
            }

            return invoices;
        } catch (SQLException e) {
            throw new RuntimeException("Error listing all invoices", e);
        }
    }

    private boolean columnExists(ResultSet rs, String columnName) {
        try {
            rs.findColumn(columnName);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void update(Invoice invoice) {
        String sql = """
                UPDATE Invoice
                SET sale_id = ?, invoice_number = ?, issue_date = ?,
                    total_amount = ?, taxes = ?
                WHERE id = ?
                """;

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, invoice.getSaleId());
            stmt.setString(2, invoice.getInvoiceNumber());
            stmt.setDate(3, Date.valueOf(invoice.getIssueDate()));
            stmt.setDouble(4, invoice.getTotalAmount());
            stmt.setDouble(5, invoice.getTaxes());
            stmt.setInt(6, invoice.getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating invoice failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating invoice", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Invoice WHERE id = ?";

        try (Connection conn = connection.getConexion();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting invoice failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting invoice with id: " + id, e);
        }
    }
}