package com.Practica_1.Backend.Conexión_DB;

import java.sql.*;

import javax.swing.JTextArea;

import com.Practica_1.Backend.Datos.Data_Evento;

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

    public void guardarEvento(Data_Evento data) {
        String query = "INSERT INTO evento (codigo, fecha, tipo, titulo, ubicacion, cupo) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, data.getCodigoEvento());
            preparedStatement.setDate(2, Date.valueOf(data.getFechaEvento()));
            preparedStatement.setString(3, data.getTipoEvento());
            preparedStatement.setString(4, data.getTituloEvento());
            preparedStatement.setString(5, data.getUbicacionEvento());
            preparedStatement.setInt(6, data.getCupoEvento());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                jTextArea.append(" -> Evento registrado exitosamente.\n\n");
            } else {
                jTextArea.append(" -> Error al registrar el evento.\n\n");
            }
        } catch (SQLException e) {
            jTextArea.append(" -> Error al guardar el evento: " + e.getMessage() + "\n\n");
        }
    }

}
