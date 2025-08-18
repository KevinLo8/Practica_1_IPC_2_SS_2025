package com.Practica_1.Backend.Analizador;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import com.Practica_1.Backend.ConexiónArchivo.ConexionArchivo;
import com.Practica_1.Backend.Conexión_DB.Conexión_DB;
import com.Practica_1.Backend.Datos.*;
import com.Practica_1.Backend.Exception.*;
import com.Practica_1.Backend.GeneradorHTML.GeneradorHTML;
import com.Practica_1.Backend.Procesador.ProcAjustes;
import com.Practica_1.Frontend.Frame_principal;

public class Analizador implements Runnable {

    private Frame_principal frame;
    private Conexión_DB conexion;
    private String texto;
    private int velocidad;
    private ProcAjustes procAjustes;

    public Analizador(ProcAjustes procAjustes, Frame_principal frame, String texto, int velocidad) {
        this.procAjustes = procAjustes;
        this.frame = frame;
        this.texto = texto;
        this.velocidad = velocidad;
        this.conexion = this.frame.getConexion();
    }

    @Override
    public void run() {
        texto = texto.replaceAll("\\/\\*.*\\*\\/", texto);
        String[] lineas = texto.split(";");

        for (String linea : lineas) {
            try {
                seleccionarTipo(linea);
            } catch (AnalizadorException e) {
                if (e.getMessage().isEmpty()) {
                    frame.appendTextLog("\n\n -> Error en la linea de comando: " + linea + ".");
                } else {
                    frame.appendTextLog(
                            "\n\n -> Error en la linea de comando: " + linea + e.getMessage() + ".");
                }
            } catch (ErrProcException e) {
                frame.appendTextLog("\n\n -> Error en la linea de comando: " + linea + "\n -> " + e.getMessage() + ".");
            }
            try {
                Thread.sleep(velocidad);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        procAjustes.finalizacion("Se ha ejecutado el archivo de entrada exitosamente.\n\n"
                + " -> Se ha guardado la direccion de salida exitosamente.");
    }

    private void seleccionarTipo(String linea) throws AnalizadorException, ErrProcException {
        String[] partesLinea = linea.split("(\\(|\\))");

        if (partesLinea.length != 2) {
            throw new AnalizadorException();
        }

        switch (partesLinea[0]) {
            case "REGISTRO_EVENTO":
                analizarEvento(partesLinea[1]);
                break;
            case "REGISTRO_PARTICIPANTE":
                analizarParticipante(partesLinea[1]);
                break;
            case "INSCRIPCION":
                analizarInscripcion(partesLinea[1]);
                break;
            case "PAGO":
                analizarPago(partesLinea[1]);
                break;
            case "VALIDAR_INSCRIPCION":
                analizarValidacion(partesLinea[1]);
                break;
            case "REGISTRO_ACTIVIDAD":
                analizarActividad(partesLinea[1]);
                break;
            case "ASISTENCIA":
                analizarAsistencia(partesLinea[1]);
                break;
            case "CERTIFICADO":
                analizarCertificado(partesLinea[1]);
                break;
            case "REPORTE_PARTICIPANTES":
                analizarRepParticipantes(partesLinea[1]);
                break;
            case "REPORTE_ACTIVIDADES":
                analizarRepActividades(partesLinea[1]);
                break;
            case "REPORTE_EVENTOS":
                analizarRepEventos(partesLinea[1]);
                break;
            default:
                throw new AnalizadorException(partesLinea[0]);
        }
    }

    private void analizarEvento(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 7) {

            String codigo = revisarParteTexto(partes[0], "\"EVT-\\d{8}\"", 12);
            LocalDate fecha = revisarParteFecha(partes[1]);
            String tipo = revisarParteTexto(partes[2], "\"[\\w-]+\"", 15);
            String titulo = revisarParteTexto(partes[3], "\".+\"", 50);
            String ubicacion = revisarParteTexto(partes[4], "\".+\"", 150);
            int cupo = revisarParteNumero(partes[5]);
            Double costo = revisarParteDecimal(partes[6]);

            if (cupo == 0) {
                throw new AnalizadorException(partes[5]);
            }

            if (conexion.consultarEvento(codigo)) {
                throw new AnalizadorException("", "El evento que se intento guardar ya existe");
            }

            try {
                Data_Evento data = new Data_Evento(codigo, fecha, tipo, titulo, ubicacion, cupo, costo);
                conexion.guardarEvento(data);
                frame.appendTextLog("\n\n -> Evento guardado Exitosamente.");
            } catch (SelecionTipoException e) {
                throw new AnalizadorException(partes[2]);
            }

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private void analizarParticipante(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 4) {

            String nombre = revisarParteTexto(partes[0], "\".+\"", 45);
            String tipo = revisarParteTexto(partes[1], "\"[\\w-]+\"", 15);
            String institucion = revisarParteTexto(partes[2], "\".+\"", 150);
            String correo = revisarParteCorreo(partes[3].replace("\"", ""));

            if (conexion.consultarParticipante(correo)) {
                throw new AnalizadorException("", "El participante que se intento guardar ya existe");
            }

            try {
                Data_Participante data = new Data_Participante(nombre, tipo, institucion, correo);
                conexion.guardarParticipante(data);
                frame.appendTextLog("\n\n -> Participante guardado Exitosamente.");
            } catch (SelecionTipoException e) {
                throw new AnalizadorException(partes[1]);
            }

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private void analizarInscripcion(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 3) {

            String correo = revisarParteCorreo(partes[0].replace("\"", ""));
            String codigo = revisarParteTexto(partes[1], "\"EVT-\\d{8}\"", 12);
            String tipo = revisarParteTexto(partes[2], "\"[\\w-]+\"", 15);

            if (revisarEventoYParticipante(correo, codigo)) {
                throw new AnalizadorException("",
                        "La inscripción que se intento guardar tiene datos erroneos o inexistentes");
            }

            if (conexion.consultarInscripcion(correo, codigo)) {
                throw new AnalizadorException("", "La inscripción que se intento guardar ya existe");
            }

            if (conexion.revisarCupoInscripcion(codigo, conexion.solicitarEvento(codigo).getCupoEvento())) {
                throw new AnalizadorException("",
                        "No hay cupo disponible para registrar la inscripci+on en este evento.");
            }

            try {
                Data_Inscripcion data = new Data_Inscripcion(correo, codigo, tipo, "", 0, 0);
                conexion.guardarInscripción(data);
                frame.appendTextLog("\n\n -> Inscripción guardada Exitosamente.");
            } catch (SelecionTipoException e) {
                throw new AnalizadorException(partes[2]);
            }

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private void analizarPago(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 4) {

            String correo = revisarParteCorreo(partes[0].replace("\"", ""));
            String codigo = revisarParteTexto(partes[1], "\"EVT-\\d{8}\"", 12);
            String tipo = revisarParteTexto(partes[2], "\"[\\w-]+\"", 15);
            Double monto = revisarParteDecimal(partes[3]);

            if (revisarInscripcion(correo, codigo)) {
                throw new AnalizadorException("",
                        "La inscripción que se intento pagar tiene datos erroneos o inexistentes");
            }

            if (conexion.consultarPago(correo, codigo)) {
                throw new AnalizadorException("", "La inscripción ya se encuentra pagada");
            }

            if (monto < conexion.solicitarEvento(codigo).getCostoinscripcion()) {
                throw new AnalizadorException("", "El monto de pago es insuficiente para la inscripción");
            }

            try {
                Data_Inscripcion data = new Data_Inscripcion(correo, codigo, "", tipo, monto, 0);
                conexion.guardarPago(data);
                frame.appendTextLog("\n\n -> Pago guardado Exitosamente.");
            } catch (SelecionTipoException e) {
                throw new AnalizadorException(partes[2]);
            }

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private void analizarValidacion(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 2) {

            String correo = revisarParteCorreo(partes[0].replace("\"", ""));
            String codigo = revisarParteTexto(partes[1], "\"EVT-\\d{8}\"", 12);

            if (revisarPago(correo, codigo)) {
                throw new AnalizadorException("",
                        "La inscripción que se intento validar tiene datos erroneos o inexistentes");
            }

            if (conexion.consultarValidacion(correo, codigo)) {
                throw new AnalizadorException("", "La inscripción ya se encuentra validada");
            }

            try {
                Data_Inscripcion data = new Data_Inscripcion(correo, codigo, "", "", 0, 1);
                conexion.guardarValidacion(data);
                frame.appendTextLog("\n\n -> Validación guardada Exitosamente.");
            } catch (SelecionTipoException e) {
                throw new AnalizadorException("", e.getMessage());
            }

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private void analizarActividad(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 8) {

            String codigo = revisarParteTexto(partes[0], "\"ACT-\\d{8}\"", 12);
            String codigoEvento = revisarParteTexto(partes[1], "\"EVT-\\d{8}\"", 12);
            String tipo = revisarParteTexto(partes[2], "\"[\\w-]+\"", 10);
            String titulo = revisarParteTexto(partes[3], "\".+\"", 200);
            String correo = revisarParteCorreo(partes[4].replace("\"", ""));
            String horaInicio = revisarParteHora(partes[5].replace("\"", ""));
            String horaFin = revisarParteHora(partes[6].replace("\"", ""));
            int cupo = revisarParteNumero(partes[7]);

            if (revisarEventoYParticipante(correo, codigoEvento)) {
                throw new AnalizadorException("",
                        "La actividad que se intento guardar tiene datos erroneos o inexistentes");
            }

            if (conexion.consultarActividad(codigo)) {
                throw new AnalizadorException("", "La actividad ya se intento guardar ya existe");
            }

            if (conexion.solicitarParticipante(correo).getTipoParticipante().toString().equals("ASISTENTE")) {
                throw new AnalizadorException("", "La actividad no puede ser impartida por un asistente");
            }

            if (horaFin.compareTo(horaInicio) <= 0) {
                throw new AnalizadorException("", "La hora de fin debe ser posterior a la hora de inicio");
            }

            if (cupo == 0) {
                throw new AnalizadorException(partes[7]);
            }

            try {
                Data_Actividad data = new Data_Actividad(codigo, codigoEvento, tipo, titulo, correo, horaInicio,
                        horaFin, cupo);
                conexion.guardarActividad(data);
                frame.appendTextLog("\n\n -> Actividad guardada Exitosamente.");
            } catch (SelecionTipoException e) {
                throw new AnalizadorException(partes[2]);
            }

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private void analizarAsistencia(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 2) {

            String correo = revisarParteCorreo(partes[0].replace("\"", ""));
            String codigo = revisarParteTexto(partes[1], "\"ACT-\\d{8}\"", 12);
            int iD = conexion.solicitarID();

            if (revisarActividad(correo, codigo)) {
                throw new AnalizadorException("",
                        "La actividad que se intento guardar tiene datos erroneos o inexistentes");
            }

            if (conexion.solicitarActividad(codigo).getCorreoImpartidor().equals(correo)) {
                throw new AnalizadorException("",
                        "El participante no puede registrarse a sí mismo como asistente de su propia actividad");
            }

            if (conexion.consultarAsistencia(correo, codigo)) {
                throw new AnalizadorException("", "El participante ya ha registrado asistencia para esta actividad.");
            }

            if (conexion.revisarCupoAsistencia(codigo, conexion.solicitarActividad(codigo).getCupoMaximo())) {
                throw new AnalizadorException("",
                        "No hay cupo disponible para registrar la asistencia en esta actividad.");
            }

            if (iD == 0) {
                throw new AnalizadorException("", "Error al solicitar el ID");
            }

            conexion.guardarAsistencia(iD, correo, codigo);
            frame.appendTextLog("\n\n -> Asistencia guardada Exitosamente.");

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private void analizarCertificado(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 2) {

            String correo = revisarParteCorreo(partes[0].replace("\"", ""));
            String codigo = revisarParteTexto(partes[1], "\"EVT-\\d{8}\"", 12);

            if (revisarValidacion(correo, codigo)) {
                throw new AnalizadorException("",
                        "El certificado que intento crear tiene datos erroneos o inexistentes");
            }

            if (conexion.solicitarAsistencias(correo, codigo) == null) {
                throw new AnalizadorException("", "El participante no ha registrado ningina asistencia.");
            }

            Data_Participante data_Participante = conexion.solicitarParticipante(correo);
            Data_Actividad[] data_Actividad = conexion.solicitarAsistencias(correo, codigo);

            String htmlCertificado = GeneradorHTML.CertificadoHTML(data_Participante, codigo, data_Actividad);

            String nombreCertificado = correo + "_" + codigo;
            nombreCertificado = ConexionArchivo.GenerarNombre(frame.getPathSalida(), nombreCertificado);

            try {
                ConexionArchivo.guardarArchivo(frame.getPathSalida(), htmlCertificado, nombreCertificado);
            } catch (IOException e) {
                throw new AnalizadorException("", "Ha ocurrido un error al guardar el archivo");
            } catch (ArchivoExistenteException e) {
                throw new AnalizadorException("", "Ya existe un archivo con el nombre que se intento guardar");
            }

            frame.appendTextLog("\n\n -> Certificado creado exitosamente.");

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private void analizarRepParticipantes(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 3) {

            String codigo = revisarParteTexto(partes[0], "\"EVT-\\d{8}\"", 12);
            String tipo = revisarParteTexto(partes[1], "\"[\\w-]*\"", 15);
            String institucion = revisarParteTexto(partes[2], "\".*\"", 150).trim();

            if (!conexion.consultarEvento(codigo)) {
                throw new AnalizadorException("",
                        "El reporte de participantes que intento crear tiene datos erroneos o inexistentes");
            }

            if (!tipo.isEmpty()) {
                Data_Participante data = new Data_Participante();

                try {
                    data.setTipoParticipante(tipo);
                } catch (SelecionTipoException e) {
                    throw new AnalizadorException("",
                            "El reporte de participantes que intento crear tiene datos erroneos o inexistentes");
                }

            }

            Data_Participante[] participantes = frame.getConexion().solicitarParticipantes(codigo, tipo, institucion);

            String htmlreporte = GeneradorHTML.ReporteParticipantesHTML(participantes);

            String nombreReporte = "ReporteParticipantes_" + codigo;
            nombreReporte = ConexionArchivo.GenerarNombre(frame.getPathSalida(), nombreReporte);

            try {
                ConexionArchivo.guardarArchivo(frame.getPathSalida(), htmlreporte, nombreReporte);
            } catch (IOException e) {
                throw new AnalizadorException("", "Ha ocurrido un error al guardar el archivo");
            } catch (ArchivoExistenteException e) {
                throw new AnalizadorException("", "Ya existe un archivo con el nombre que se intento guardar");
            }

            frame.appendTextLog("\n\n -> Reporte de participantes creado exitosamente.");

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private void analizarRepActividades(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 3) {

            String codigo = revisarParteTexto(partes[0], "\"EVT-\\d{8}\"", 12);
            String tipo = revisarParteTexto(partes[1], "\"[\\w-]*\"", 15);
            String correo = revisarParteTexto(partes[2], "\".*\"", 255);

            if (correo.isEmpty()) {
                if (!conexion.consultarEvento(codigo)) {
                    throw new AnalizadorException("",
                            "El reporte de actividades que intento crear tiene datos erroneos o inexistentes");
                }
            } else {
                if (revisarEventoYParticipante(correo, codigo)) {
                    throw new AnalizadorException("",
                            "El reporte de actividades que intento crear tiene datos erroneos o inexistentes");
                }
            }

            if (!tipo.isEmpty()) {
                Data_Actividad data = new Data_Actividad();

                try {
                    data.setTipoActividad(tipo);
                } catch (SelecionTipoException e) {
                    throw new AnalizadorException("",
                            "El reporte de actividades que intento crear tiene datos erroneos o inexistentes");
                }

            }

            Data_Actividad[] actividades = frame.getConexion().solicitarActividades(codigo, tipo, correo);

            if (actividades == null) {
                throw new AnalizadorException("", "Error al solicitar las actividades");
            }

            for (Data_Actividad data_Act : actividades) {
                int participantes = frame.getConexion().solicitarCantidadParticipantes(data_Act.getCodigoActividad());

                if (participantes == -1) {
                    throw new AnalizadorException("", "Error al solicitar la cantidad de participantes");
                }

                data_Act.setParticipantes(participantes);
            }

            String htmlreporte = GeneradorHTML.ReporteActividadesHTML(actividades);

            String nombreReporte = "ReporteActividades_" + codigo;
            nombreReporte = ConexionArchivo.GenerarNombre(frame.getPathSalida(), nombreReporte);

            try {
                ConexionArchivo.guardarArchivo(frame.getPathSalida(), htmlreporte, nombreReporte);
            } catch (IOException e) {
                throw new AnalizadorException("", "Ha ocurrido un error al guardar el archivo");
            } catch (ArchivoExistenteException e) {
                throw new AnalizadorException("", "Ya existe un archivo con el nombre que se intento guardar");
            }

            frame.appendTextLog("\n\n -> Reporte de actividades creado exitosamente.");

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private void analizarRepEventos(String parteLinea) throws AnalizadorException, ErrProcException {
        String[] partes = parteLinea.split(",");

        if (partes.length == 3 || partes.length == 5) {

            String tipo = revisarParteTexto(partes[0], "\"[\\w-]*\"", 15);

            if (!tipo.isEmpty()) {
                Data_Evento data = new Data_Evento();

                try {
                    data.setTipoEvento(tipo);
                } catch (SelecionTipoException e) {
                    throw new AnalizadorException("",
                            "El reporte de eventos que intento crear tiene datos erroneos o inexistentes");
                }
            }

            if (!partes[1].matches("\"\"")) {
                LocalDate fechaInicio = null, fechaFin = null;
                try {
                    fechaInicio = revisarParteFecha(partes[1]);
                    if (!partes[2].matches("\"\"")) {
                        fechaFin = revisarParteFecha(partes[2]);
                        if (fechaFin.isBefore(fechaInicio)) {
                            throw new AnalizadorException("",
                                    "El reporte de eventos que intento crear tiene datos erroneos o inexistentes");
                        }
                    } else {
                        throw new AnalizadorException("",
                                "El reporte de eventos que intento crear tiene datos erroneos o inexistentes");
                    }
                } catch (DateTimeException e) {
                    throw new AnalizadorException("",
                            "El reporte de eventos que intento crear tiene datos erroneos o inexistentes");
                }
            } else if (!partes[2].matches("\"\"")) {
                throw new AnalizadorException("",
                        "El reporte de eventos que intento crear tiene datos erroneos o inexistentes");
            }

            int cupoMin = 0;
            int cupoMax = 0;

            if (partes.length == 5) {
                cupoMin = revisarParteNumero(partes[3]);
                cupoMax = revisarParteNumero(partes[4]);

                if ((cupoMax < cupoMin) || (cupoMax == cupoMin && cupoMax == 0)) {
                    throw new AnalizadorException("",
                            "El reporte de eventos que intento crear tiene datos erroneos o inexistentes");
                }
            }

            Data_Evento[] eventos = conexion.solicitarEventos(tipo, partes[1].replace("\"", ""),
                    partes[2].replace("\"", ""), cupoMin, cupoMax);

            String htmlreporte = GeneradorHTML.ReporteEventosInicioHTML();

            for (int i = 0; i < eventos.length; i++) {
                Data_Evento evento = eventos[i];
                String texto = generarTexto(evento, i);
                htmlreporte = htmlreporte + texto;
            }

            htmlreporte = htmlreporte + GeneradorHTML.ReporteEventosFinHTML();

            String nombreReporte = "ReporteEventos";
            nombreReporte = ConexionArchivo.GenerarNombre(frame.getPathSalida(), nombreReporte);

            try {
                ConexionArchivo.guardarArchivo(frame.getPathSalida(), htmlreporte, nombreReporte);
            } catch (IOException e) {
                throw new AnalizadorException("", "Ha ocurrido un error al guardar el archivo");
            } catch (ArchivoExistenteException e) {
                throw new AnalizadorException("", "Ya existe un archivo con el nombre que se intento guardar");
            }

            frame.appendTextLog("\n\n -> Reporte de eventos creado exitosamente.");

        } else {
            throw new AnalizadorException(parteLinea);
        }
    }

    private boolean revisarEventoYParticipante(String correo, String codigo) throws ErrProcException {
        if (!conexion.consultarEvento(codigo)) {
            return true;
        } else if (!conexion.consultarParticipante(correo)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean revisarInscripcion(String correo, String codigo) throws ErrProcException {
        if (revisarEventoYParticipante(correo, codigo)) {
            return true;
        } else if (!conexion.consultarInscripcion(correo, codigo)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean revisarPago(String correo, String codigo) throws ErrProcException {
        if (revisarInscripcion(correo, codigo)) {
            return true;
        } else if (!conexion.consultarPago(correo, codigo)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean revisarValidacion(String correo, String codigo) throws ErrProcException {

        if (revisarPago(correo, codigo)) {
            return true;
        } else if (!conexion.consultarValidacion(correo, codigo)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean revisarActividad(String correo, String codigo) throws ErrProcException {

        if (!conexion.consultarActividad(codigo)) {
            return true;
        } else if (revisarValidacion(correo, conexion.solicitarActividad(codigo).getCodigoEvento())) {
            return true;
        } else {
            return false;
        }
    }

    private String revisarParteTexto(String parte, String regex, int tamaño) throws AnalizadorException {
        if (!parte.matches(regex)) {
            throw new AnalizadorException(parte);
        } else {
            String texto = parte.replace("\"", "");
            if (texto.length() > tamaño) {
                throw new AnalizadorException(parte);
            }
            return texto;
        }
    }

    private String revisarParteCorreo(String parte) throws AnalizadorException {
        if (!parte.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new AnalizadorException(parte);
        } else {
            return parte;
        }
    }

    private LocalDate revisarParteFecha(String parte) throws AnalizadorException {
        if (!parte.matches("\"\\d{2}/\\d{2}/\\d{4}\"")) {
            throw new AnalizadorException(parte);
        } else {
            try {
                String[] fechaParts = parte.replace("\"", "").split("/");
                LocalDate dataFecha = LocalDate.of(
                        Integer.parseInt(fechaParts[2]),
                        Integer.parseInt(fechaParts[1]),
                        Integer.parseInt(fechaParts[0]));
                return dataFecha;
            } catch (DateTimeException e) {
                throw new AnalizadorException(parte);
            }
        }
    }

    private String revisarParteHora(String parte) throws AnalizadorException {
        if (!parte.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new AnalizadorException(parte);
        } else {
            return parte;
        }
    }

    private int revisarParteNumero(String parte) throws AnalizadorException {
        if (!parte.matches("\\d+")) {
            throw new AnalizadorException(parte);
        } else {
            int numero = Integer.parseInt(parte);
            if (numero < 0) {
                throw new AnalizadorException(parte);
            }
            return numero;
        }
    }

    private Double revisarParteDecimal(String parte) throws AnalizadorException {
        if (!parte.matches("\\d+.\\d{2}")) {
            throw new AnalizadorException(parte);
        } else {
            Double cantidad = Double.parseDouble(parte);
            if (cantidad <= 0) {
                throw new AnalizadorException(parte);
            }
            return cantidad;
        }
    }

    private String generarTexto(Data_Evento evento, int numero) throws ErrProcException {
        String texto;

        Data_Participante[] participantes = conexion.solicitarParticipantes(evento.getCodigoEvento(), "",
                "");

        int montoTotal = 0, participantesValidos = 0, participantesNoValidos = 0;

        for (Data_Participante part : participantes) {
            Data_Inscripcion insc = conexion.solicitarInscripcion(part.getCorreoParticipante(),
                    evento.getCodigoEvento());

            montoTotal += insc.getMontoPago();
            part.setMontoPago(insc.getMontoPago());
            if (insc.getTipoPago() == null) {
                part.setMetodoPago("NINGUNO");
            } else {
                part.setMetodoPago(insc.getTipoPago().toString());
            }
            if (insc.getValidacion()) {
                participantesValidos++;
            } else {
                participantesNoValidos++;
            }
        }

        texto = GeneradorHTML.ReporteEventosHTML(numero, evento, participantes, montoTotal, participantesValidos,
                participantesNoValidos);

        return texto;
    }

}
