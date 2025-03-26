package com.sportscenter.infrastructure.database;

public class ConnectionFactory {
    public static ConnectionDb crearConexion() {
        return new ConnMySql();
    }

    public static ConnectionDb createConnection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createConnection'");
    }
}
