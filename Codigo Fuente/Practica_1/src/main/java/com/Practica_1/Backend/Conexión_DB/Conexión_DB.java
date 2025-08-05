package com.Practica_1.Backend.Conexión_DB;

import java.sql.*;

import javax.swing.JTextArea;

import com.Practica_1.Backend.Datos.*;

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

    public void guardarParticipante(Data_Participante data) {
        String query = "INSERT INTO participante (nombre, tipo, institucion, correo) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, data.getNombreParticipante());
            preparedStatement.setString(2, data.getTipoParticipante());
            preparedStatement.setString(3, data.getInstitucionParticipante());
            preparedStatement.setString(4, data.getCorreoParticipante());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                jTextArea.append(" -> Participante registrado exitosamente.\n\n");
            } else {
                jTextArea.append(" -> Error al registrar el participante.\n\n");
            }
        } catch (SQLException e) {
            jTextArea.append(" -> Error al guardar el participante: " + e.getMessage() + "\n\n");
        }
    }

    public void guardarInscripción(Data_Inscripcion data) {
        String query = "INSERT INTO inscripcion (correo_participante, codigo_evento, tipo_inscripcion) VALUES (?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, data.getCorreoParticipante());
            preparedStatement.setString(2, data.getCodigoEvento());
            preparedStatement.setString(3, data.getTipoInscripcion());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                jTextArea.append(" -> Inscripción registrada exitosamente.\n\n");
            } else {
                jTextArea.append(" -> Error al registrar la inscripción.\n\n");
            }
        } catch (SQLException e) {
            jTextArea.append(" -> Error al guardar la inscripción: " + e.getMessage() + "\n\n");
        }
    }

    public Boolean consultarParticipante(String correo) {
        String query = "SELECT * FROM participante WHERE correo = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, correo);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            jTextArea.append(" -> Error al consultar el participante: " + e.getMessage() + "\n\n");
            return false;
        }
    }

    public boolean consultarEvento(String codigo) {
        String query = "SELECT * FROM evento WHERE codigo = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, codigo);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            jTextArea.append(" -> Error al consultar el evento: " + e.getMessage() + "\n\n");
            return false;
        }
    }

}
