package com.Practica_1.Backend.Conexión_DB;

import java.sql.*;

import javax.swing.JTextArea;

public class Conexión_DB {

    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/ADMINISTRACION_EVENTOS";
    private static final String USER = "admindba";
    private static final String PASSWORD = "12345";

    private Connection connection = null;

    private JTextArea jTextArea;

    public Conexión_DB(JTextArea jTextArea) {

        this.jTextArea = jTextArea;

        try {
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            this.jTextArea.append(" -> Conexión a la base de datos establecida.\n\n");
        } catch (SQLException ex) {
            this.jTextArea.append(" -> Error al conectar a la base de datos.\n\n");
            System.out.println("error al conectar a la DB");
            ex.printStackTrace();
        }
    }

}
