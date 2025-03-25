package com.sportscenter;

import java.sql.Connection;
import java.sql.SQLException;

import com.sportscenter.infrastructure.database.ConnectionDb;
import com.sportscenter.infrastructure.database.ConnectionFactory;

public class Main {
    public static void main(String[] args) {
        ConnectionDb connectionDb = ConnectionFactory.crearConexion();

        try (Connection connection = connectionDb.getConnection()) {

            if (connection != null && !connection.isClosed()) {
                System.out.println("¡Conexión a la base de datos establecida correctamente!");
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {

            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}