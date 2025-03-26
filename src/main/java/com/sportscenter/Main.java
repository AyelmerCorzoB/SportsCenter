package com.sportscenter;

import java.sql.Connection;
import java.sql.SQLException;

import com.sportscenter.adapter.global.ConsoleUtils;
import com.sportscenter.application.LoginPrimeraPropuesta;
import com.sportscenter.infrastructure.database.ConnectionDb;
import com.sportscenter.infrastructure.database.ConnectionFactory;

public class Main {
    public static void main(String[] args) {
        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        ConsoleUtils.clear();
        LoginPrimeraPropuesta.main(args);;
    }
}