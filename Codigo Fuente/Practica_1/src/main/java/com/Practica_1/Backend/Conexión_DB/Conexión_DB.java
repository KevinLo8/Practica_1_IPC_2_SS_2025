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
            preparedStatement.setString(3, data.getTipoEvento().toString());
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
            preparedStatement.setString(2, data.getTipoParticipante().toString());
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
            preparedStatement.setString(3, data.getTipoInscripcion().toString());

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

    public void guardarPago(Data_Pago data) {
        String query = "UPDATE inscripcion SET metodo_pago = ?, monto_pago = ? WHERE correo_participante = ? AND codigo_evento = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, data.getTipoPago().toString());
            preparedStatement.setDouble(2, data.getMontoPago());
            preparedStatement.setString(3, data.getCorreoParticipante());
            preparedStatement.setString(4, data.getCodigoEvento());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                jTextArea.append(" -> Pago registrado exitosamente.\n\n");
            } else {
                jTextArea.append(" -> Error al registrar el pago.\n\n");
            }
        } catch (SQLException e) {
            jTextArea.append(" -> Error al guardar el pago: " + e.getMessage() + "\n\n");
        }
    }

    public void guardarValidacion(Data_Validacion data) {
        String query = "UPDATE inscripcion SET estado_validacion = 1 WHERE correo_participante = ? AND codigo_evento = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, data.getCorreoParticipante());
            preparedStatement.setString(2, data.getCodigoEvento());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                jTextArea.append(" -> Validación registrada exitosamente.\n\n");
            } else {
                jTextArea.append(" -> Error al registrar la validación.\n\n");
            }
        } catch (SQLException e) {
            jTextArea.append(" -> Error al guardar la validación: " + e.getMessage() + "\n\n");
        }
    }

    public void guardarActividad(Data_Actividad data) {
        String query = "INSERT INTO actividad (codigo, codigo_evento, tipo, titulo, correo_impartidor, hora_inicio, hora_fin, cupo_maximo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, data.getCodigoActividad());
            preparedStatement.setString(2, data.getCodigoEvento());
            preparedStatement.setString(3, data.getTipoActividad().toString());
            preparedStatement.setString(4, data.getTituloActividad());
            preparedStatement.setString(5, data.getCorreoImpartidor());
            preparedStatement.setTime(6, Time.valueOf(data.getHoraInicio() + ":00"));
            preparedStatement.setTime(7, Time.valueOf(data.getHoraFin() + ":00"));
            preparedStatement.setInt(8, data.getCupoMaximo());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                jTextArea.append(" -> Actividad registrada exitosamente.\n\n");
            } else {
                jTextArea.append(" -> Error al registrar la actividad.\n\n");
            }
        } catch (SQLException e) {
            jTextArea.append(" -> Error al guardar la actividad: " + e.getMessage() + "\n\n");
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

    public boolean consultarInscripcion(String correo, String codigoEvento) {
        String query = "SELECT * FROM inscripcion WHERE correo_participante = ? AND codigo_evento = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, codigoEvento);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            jTextArea.append(" -> Error al consultar la inscripción: " + e.getMessage() + "\n\n");
            return false;
        }
    }

    public boolean consultarPago(String correo, String codigoEvento) {
        String query = "SELECT * FROM inscripcion WHERE correo_participante = ? AND codigo_evento = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, codigoEvento);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return resultSet.getString("monto_pago").equals("0.00");
            
        } catch (SQLException e) {
            jTextArea.append(" -> Error al consultar el pago: " + e.getMessage() + "\n\n");
            return false;
        }
    }

    public boolean consultarValidacion(String correo, String codigoEvento) {
        String query = "SELECT * FROM inscripcion WHERE correo_participante = ? AND codigo_evento = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, codigoEvento);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return resultSet.getString("estado_validacion").equals("0");
            
        } catch (SQLException e) {
            jTextArea.append(" -> Error al consultar la validación: " + e.getMessage() + "\n\n");
            return false;
        }
    }

    public boolean consultarActividad(String codigoActividad) {
        String query = "SELECT * FROM actividad WHERE codigo = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, codigoActividad);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            jTextArea.append(" -> Error al consultar la actividad: " + e.getMessage() + "\n\n");
            return false;
        }
    }

    public boolean revisarTipoInscripcion(String correo, String codigoEvento) {
        String query = "SELECT * FROM inscripcion WHERE correo_participante = ? AND codigo_evento = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, codigoEvento);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return !resultSet.getString("tipo_inscripcion").equals("ASISTENTE");
            
        } catch (SQLException e) {
            jTextArea.append(" -> Error al revisar el tipo de inscripción: " + e.getMessage() + "\n\n");
            return false;
        }
    }
}
