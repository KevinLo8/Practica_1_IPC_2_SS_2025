package com.Practica_1.Backend.Conexión_DB;

import java.sql.*;

import javax.swing.JTextArea;

import com.Practica_1.Backend.Datos.*;
import com.Practica_1.Backend.Exception.SelecionTipoException;

public class Conexión_DB {

    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/adminstracion_eventos";
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

    public void guardarPago(Data_Inscripcion data) {
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

    public void guardarValidacion(Data_Inscripcion data) {
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

    public void guardarAsistencia(int iD, String correo, String codigo) {
        String query = "INSERT INTO asistencia (ID, codigo_actividad, correo_participante) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, iD);
            preparedStatement.setString(2,codigo);
            preparedStatement.setString(3, correo);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                jTextArea.append("");
            } else {
                jTextArea.append(" -> Error al registrar la asistencia.\n\n");
            }
        } catch (SQLException e) {
            jTextArea.append(" -> Error al guardar la asistencia: " + e.getMessage() + "\n\n");
        }
    }

    public Boolean consultarParticipante(String correo) {
        String query = "SELECT * FROM participante WHERE correo = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
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

    public boolean revisarAsistencia(String correo, String codigoActividad) {
        String query = "SELECT * FROM asistencia WHERE correo_participante = ? AND codigo_actividad = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, codigoActividad);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            jTextArea.append(" -> Error al revisar la asistencia: " + e.getMessage() + "\n\n");
            return false;
        }
    }

    public boolean revisarCupoAsistencia(String codigoActividad, int cupoMaximo) {
        String query = "SELECT * FROM asistencia WHERE codigo_actividad = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            preparedStatement.setString(1, codigoActividad);
            resultSet = preparedStatement.executeQuery();

            resultSet.last();

            return 0 >= cupoMaximo - resultSet.getRow();

        } catch (SQLException e) {
            jTextArea.append(" -> Error al revisar el cupo de asistencia: " + e.getMessage() + "\n\n");
            return false;
        }
    }

    public Data_Participante solicitarParticipante(String correo) {
        String query = "SELECT * FROM participante WHERE correo = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, correo);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Data_Participante data = new Data_Participante(resultSet.getString("nombre"),
                        resultSet.getString("tipo"),
                        resultSet.getString("institucion"), resultSet.getString("correo"));

                return data;

            } else {
                return null;
            }

        } catch (SQLException e) {
            jTextArea.append(" -> Error al solicitar el participante: " + e.getMessage() + "\n\n");
            return null;
        }

    }

    public Data_Participante[] solicitarParticipantes(String evento, String tipo, String institucion) {
        String query = "SELECT * FROM participante p JOIN inscripcion i ON p.correo = i.correo_participante WHERE i.codigo_evento = ?";
        ResultSet resultSet = null;

        if (!tipo.equals("")) {
            query = query + " AND p.tipo = ?";
        }

        if (!institucion.equals("")) {
            query = query + " AND p.institucion = ?";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            int i = 1;

            preparedStatement.setString(i, evento);
            i++;

            if (!tipo.equals("")) {
                preparedStatement.setString(i, tipo);
                i++;
            }

            if (!institucion.equals("")) {
                preparedStatement.setString(i, institucion);
            }

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Data_Participante[] participantes = new Data_Participante[0];

                do {

                    Data_Participante data = new Data_Participante(resultSet.getString("nombre"),
                            resultSet.getString("tipo"), resultSet.getString("institucion"),
                            resultSet.getString("correo"), resultSet.getBoolean("estado_validacion"));

                    Data_Participante[] partTemp = new Data_Participante[participantes.length + 1];

                    i = 0;
                    while (i < participantes.length) {
                        partTemp[i] = participantes[i];
                        i++;
                    }

                    partTemp[participantes.length] = data;

                    participantes = partTemp;

                } while (resultSet.next());

                return participantes;

            } else {
                return null;
            }

        } catch (SQLException e) {
            jTextArea.append(" -> Error al solicitar el participante: " + e.getMessage() + "\n\n");
            return null;
        }

    }

    public Data_Evento solicitarEvento(String codigo) {
        String query = "SELECT * FROM evento WHERE codigo = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, codigo);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                Data_Evento data = new Data_Evento(resultSet.getString("codigo"),
                        resultSet.getDate("fecha").toLocalDate(), resultSet.getString("tipo"),
                        resultSet.getString("titulo"), resultSet.getString("ubicacion"),
                        resultSet.getInt("cupo"));

                return data;

            } else {
                return null;
            }

        } catch (SQLException e) {
            jTextArea.append(" -> Error al solicitar el evento: " + e.getMessage() + "\n\n");
            return null;
        }

    }

    public Data_Evento[] solicitarEventos(String tipo, String fechaInicial, String fechaFinal, int cupoMinimo, int cupoMaximo) {
        String query = "SELECT * FROM evento WHERE";
        ResultSet resultSet = null;

        int i = 1;
        if (!tipo.equals("")) {
            query = query + " tipo = ?";
        }

        if (!fechaInicial.equals("") && !fechaFinal.equals("")) {
            if (i > 1) {
                query = query + " AND";
            }
            query = query + " fecha BETWEEN ? AND ?";
        }

        if (cupoMinimo >= 0 && cupoMaximo > 0) {
            if (i > 1) {
                query = query + " AND";
            }
            query = query + " cupo BETWEEN ? AND ?";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            i = 1;

            if (!tipo.equals("")) {
                preparedStatement.setString(i, tipo);
                i++;
            }

            if (!fechaInicial.equals("") && !fechaFinal.equals("")) {
                preparedStatement.setString(i, fechaInicial);
                i++;
                preparedStatement.setString(i, fechaFinal);
                i++;
            }

            if (cupoMinimo >= 0 && cupoMaximo > 0) {
                preparedStatement.setInt(i, cupoMinimo);
                i++;
                preparedStatement.setInt(i, cupoMaximo);
                i++;
            }

            resultSet = preparedStatement.executeQuery();

            Data_Evento[] eventos = new Data_Evento[0];

            if (resultSet.next()) {

                do {

                Data_Evento data = new Data_Evento(resultSet.getString("codigo"),
                        resultSet.getDate("fecha").toLocalDate(), resultSet.getString("tipo"),
                        resultSet.getString("titulo"), resultSet.getString("ubicacion"),
                        resultSet.getInt("cupo"));

                    Data_Evento[] evenTemp = new Data_Evento[eventos.length + 1];

                    i = 0;
                    while (i < eventos.length) {
                        evenTemp[i] = eventos[i];
                        i++;
                    }

                    evenTemp[eventos.length] = data;

                    eventos = evenTemp;

                } while (resultSet.next());

            }

            return eventos;

        } catch (SQLException e) {
            //jTextArea.append(" -> Error al solicitar el participante: " + e.getMessage() + "\n\n");
            return null;
        }

    }

    public Data_Inscripcion solicitarInscripcion(String correo, String codigoEvento) {
        String query = "SELECT * FROM inscripcion WHERE correo_participante = ? AND codigo_evento = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, codigoEvento);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Data_Inscripcion(resultSet.getString("correo_participante"),
                        resultSet.getString("codigo_evento"),
                        resultSet.getString("tipo_inscripcion"), resultSet.getString("metodo_pago"),
                        resultSet.getDouble("monto_pago"), resultSet.getInt("estado_validacion"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            jTextArea.append(" -> Error al consultar la inscripción: " + e.getMessage() + "\n\n");
            return null;
        } catch (SelecionTipoException e) {
            jTextArea.append(" -> Error al mapear los tipos de inscripción o pago: " + e.getMessage() + "\n\n");
            return null;
        }
    }

    public Data_Inscripcion solicitarAsiInscripcion(String correo, String codigoActividad) {
        String query = "SELECT * FROM inscripcion i JOIN actividad a ON i.codigo_evento = a.codigo_evento WHERE i.correo_participante = ? AND a.codigo = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, codigoActividad);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Data_Inscripcion(resultSet.getDouble("monto_pago"), resultSet.getInt("estado_validacion"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            jTextArea.append(" -> Error al revisar la inscripción para el evento: " + e.getMessage() + "\n\n");
            return null;
        }
    }

    public Data_Actividad[] solicitarActividades(String evento, String tipo, String correo) {
        String query = "SELECT * FROM actividad WHERE codigo_evento = ?";
        ResultSet resultSet = null;

        if (!tipo.equals("")) {
            query = query + " AND tipo = ?";
        }

        if (!correo.equals("")) {
            query = query + " AND correo_impartidor = ?";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            int i = 1;

            preparedStatement.setString(i, evento);
            i++;

            if (!tipo.equals("")) {
                preparedStatement.setString(i, tipo);
                i++;
            }

            if (!correo.equals("")) {
                preparedStatement.setString(i, correo);
            }

            resultSet = preparedStatement.executeQuery();

            Data_Actividad[] actividades = new Data_Actividad[0];

            if (resultSet.next()) {

                do {

                    Data_Actividad data = new Data_Actividad(resultSet.getString("codigo"),
                            resultSet.getString("codigo_evento"), resultSet.getString("tipo"),
                            resultSet.getString("titulo"), resultSet.getString("correo_impartidor"),
                            resultSet.getTime("hora_inicio").toString(), resultSet.getTime("hora_fin").toString(),
                            resultSet.getInt("cupo_maximo"));

                    Data_Actividad[] actiTemp = new Data_Actividad[actividades.length + 1];

                    i = 0;
                    while (i < actividades.length) {
                        actiTemp[i] = actividades[i];
                        i++;
                    }

                    actiTemp[actividades.length] = data;

                    actividades = actiTemp;

                } while (resultSet.next());

            }

            return actividades;

        } catch (SQLException e) {
            jTextArea.append(" -> Error al solicitar el participante: " + e.getMessage() + "\n\n");
            return null;
        }

    }

    public Data_Actividad solicitarAsiActividad(String codigoActividad) {
        String query = "SELECT * FROM actividad WHERE codigo = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, codigoActividad);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Data_Actividad(resultSet.getString("correo_impartidor"), resultSet.getInt("cupo_maximo"));
            } else {
                return null;
            }

        } catch (SQLException e) {
            jTextArea.append(" -> Error al consultar la actividad para asistencia: " + e.getMessage() + "\n\n");
            return null;
        }
    }

    public Data_Actividad[] solicitarAsistencias(String correo, String codigo) {
        String query = "SELECT * FROM asistencia JOIN actividad "
                + "ON asistencia.codigo_actividad = actividad.codigo "
                + "WHERE correo_participante = ? AND codigo_evento = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, codigo);
            resultSet = preparedStatement.executeQuery();

            Data_Actividad[] asistencias = new Data_Actividad[0];

            if (resultSet.next()) {

                do {

                    Data_Actividad data = new Data_Actividad(resultSet.getString("codigo"),
                            resultSet.getString("codigo_evento"), resultSet.getString("tipo"),
                            resultSet.getString("titulo"), resultSet.getString("correo_impartidor"),
                            resultSet.getTime("hora_inicio").toString(), resultSet.getTime("hora_fin").toString(),
                            resultSet.getInt("cupo_maximo"));

                    Data_Actividad[] asisTemp = new Data_Actividad[asistencias.length + 1];

                    int i = 0;
                    while (i < asistencias.length) {
                        asisTemp[i] = asistencias[i];
                        i++;
                    }

                    asisTemp[asistencias.length] = data;

                    asistencias = asisTemp;

                } while (resultSet.next());

                return asistencias;

            } else {
                return null;
            }

        } catch (SQLException e) {
            jTextArea.append(" -> Error al solicitar el evento: " + e.getMessage() + "\n\n");
            return null;
        }

    }

    public int solicitarID() {
        String query = "SELECT * FROM asistencia";
        ResultSet resultSet = null;

        try {
            Statement statementID = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statementID.executeQuery(query);

            if (!resultSet.isLast()) {
                resultSet.last();
                int iD = resultSet.getInt("ID") + 1;
                return iD;
            } else {
                return 1;
            }

        } catch (SQLException e) {
            return 0;
        }
    }

    public int solicitarCantidadParticipantes(String codigo) {
        String query = "SELECT * FROM asistencia WHERE codigo_actividad = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            preparedStatement.setString(1, codigo);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.last()) {
                return resultSet.getRow();
            } else {
                return 0;
            }

        } catch (SQLException e) {
            return -1;
        }

    }

}
