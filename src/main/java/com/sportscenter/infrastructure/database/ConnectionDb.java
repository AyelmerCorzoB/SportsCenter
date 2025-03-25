package com.sportscenter.infrastructure.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionDb {
    Connection getConnection() throws SQLException;
}
