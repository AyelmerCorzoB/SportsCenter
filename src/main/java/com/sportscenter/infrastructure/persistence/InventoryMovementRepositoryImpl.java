package com.sportscenter.infrastructure.persistence;

import com.sportscenter.domain.entities.InventoryMovement;
import com.sportscenter.domain.entities.InventoryMovement.MovementType;
import com.sportscenter.domain.repository.InventoryMovementRepository;
import com.sportscenter.infrastructure.database.ConnectionDb;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryMovementRepositoryImpl implements InventoryMovementRepository {

    private final ConnectionDb connection;

    public InventoryMovementRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void save(InventoryMovement movement) {
        String sql = "INSERT INTO InventoryMovement (product_id, quantity, movement_type, movement_date, reason) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, movement.getProductId());
            stmt.setInt(2, movement.getQuantity());
            stmt.setString(3, movement.getMovementType().name());
            stmt.setTimestamp(4, Timestamp.valueOf(movement.getMovementDate()));
            stmt.setString(5, movement.getReason());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InventoryMovement> findByProductId(int productId) {
        List<InventoryMovement> list = new ArrayList<>();
        String sql = "SELECT * FROM InventoryMovement WHERE product_id = ? ORDER BY movement_date DESC";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                InventoryMovement movement = new InventoryMovement();
                movement.setId(rs.getInt("id"));
                movement.setProductId(rs.getInt("product_id"));
                movement.setQuantity(rs.getInt("quantity"));
                movement.setMovementType(MovementType.valueOf(rs.getString("movement_type")));
                movement.setMovementDate(rs.getTimestamp("movement_date").toLocalDateTime());
                movement.setReason(rs.getString("reason"));

                list.add(movement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<InventoryMovement> findAll() {
        List<InventoryMovement> list = new ArrayList<>();
        String sql = "SELECT * FROM InventoryMovement ORDER BY movement_date DESC";

        try (Connection conn = connection.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                InventoryMovement movement = new InventoryMovement();
                movement.setId(rs.getInt("id"));
                movement.setProductId(rs.getInt("product_id"));
                movement.setQuantity(rs.getInt("quantity"));
                movement.setMovementType(MovementType.valueOf(rs.getString("movement_type")));
                movement.setMovementDate(rs.getTimestamp("movement_date").toLocalDateTime());
                movement.setReason(rs.getString("reason"));

                list.add(movement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
