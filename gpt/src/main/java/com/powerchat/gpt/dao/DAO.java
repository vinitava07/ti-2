package com.powerchat.gpt.dao;

import java.sql.*;
import java.security.*;
import java.math.*;

public class DAO {
    protected Connection connection;

    public DAO() {
        connection = null;
    }

    public boolean connect() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String myDatabase = "powerchat";
        int port = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + port +"/" + myDatabase;
        String username = "ti2cc";
        String password = "123456";
        boolean connetctionStatus = false;

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão efetuada com o postgres!");
            connetctionStatus = true;
        } catch (ClassNotFoundException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return connetctionStatus;
    }

    public boolean close() {
        boolean status = false;

        try {
            connection.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

}