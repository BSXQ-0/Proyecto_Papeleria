package org.example.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3314/papeleria_db";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "1234";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}