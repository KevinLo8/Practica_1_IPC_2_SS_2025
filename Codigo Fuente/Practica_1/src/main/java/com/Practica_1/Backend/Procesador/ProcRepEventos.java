package com.Practica_1.Backend.Procesador;

import java.io.IOException;
import java.time.*;

import javax.swing.JOptionPane;

import com.Practica_1.Backend.ConexiónArchivo.ConexionArchivo;
import com.Practica_1.Backend.Datos.*;
import com.Practica_1.Backend.Datos.Data_Evento.TipoEvento;
import com.Practica_1.Backend.Exception.ArchivoExistenteException;
import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.GeneradorHTML.GeneradorHTML;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_RepEventos;

public class ProcRepEventos {

    private Frame_principal frame;

    public ProcRepEventos(Frame_principal frame) {

        this.frame = frame;

    }

    public void crearReporte(IF_RepEventos if_RepEve, String tipo, String fechaInicio, String fechaFin, String cupoMin,
            String cupoMax) throws ErrProcException {

        LocalDate dataFechaInicio, dataFechaFin;
        int dataCupoMin = -1, dataCupoMax = -1;

        int cantidadData = 0;

        if (!tipo.isEmpty()) {
            if (revisarTipo(tipo)) {
                throw new ErrProcException("Seleccione un tipo de evento valido");
            }
            cantidadData++;
        }

        if (revisarFechas(fechaInicio, fechaFin)) {
            dataFechaInicio = convertirFecha(fechaInicio);
            dataFechaFin = convertirFecha(fechaFin);

            if (dataFechaFin.isBefore(dataFechaInicio)) {
                throw new ErrProcException("La fecha final tiene que ser después de la fecha inicial");
            }

            cantidadData++;
        }

        if (revisarCupos(cupoMin, cupoMax)) {
            dataCupoMin = Integer.parseInt(cupoMin);
            dataCupoMax = Integer.parseInt(cupoMax);

            if (dataCupoMax < dataCupoMin) {
                throw new ErrProcException("El cupo maximo debe ser mayor al cupo minimo");
            }

            cantidadData++;
        }

        if (cantidadData == 0) {
            throw new ErrProcException("Se necesita que se rellene por lo menos 1 de los datos requeridos");
        }

        Data_Evento[] eventos = frame.getConexion().solicitarEventos(tipo, fechaInicio, fechaFin, dataCupoMin,
                dataCupoMax);

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
            JOptionPane.showMessageDialog(frame,
                    "Ha ocurrido un error al guardar el archivo.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArchivoExistenteException e) {
            JOptionPane.showMessageDialog(frame,
                    "Ya existe un archivo con el nombre que se intento guardar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        if_RepEve.invisible();
    }

    private boolean revisarTipo(String tipo) {

        TipoEvento[] tipos = Data_Evento.TipoEvento.values();

        if (tipo.isEmpty()) {
            return false;
        }

        for (TipoEvento tipoEvento : tipos) {
            if (tipo.equals(tipoEvento.toString())) {
                return false;
            }
        }

        return true;
    }

    private boolean revisarFechas(String fechaInicio, String fechaFin) throws ErrProcException {

        if (!fechaInicio.isEmpty()) {
            if (fechaInicio.matches("\\d{2}/\\d{2}/\\d{4}")) {

                try {
                    convertirFecha(fechaInicio);
                } catch (DateTimeException e) {
                    throw new ErrProcException("Ingrese una fecha inicial valida");
                }

                if (!fechaFin.isEmpty()) {
                    if (fechaInicio.matches("\\d{2}/\\d{2}/\\d{4}")) {

                        try {
                            convertirFecha(fechaFin);
                        } catch (DateTimeException e) {
                            throw new ErrProcException("Ingrese una fecha final valida");
                        }

                        return true;

                    } else {
                        throw new ErrProcException("Ingrese una fecha final en el formato requerido");
                    }
                } else {
                    throw new ErrProcException("Ingrese una fecha final para el rango de fecha");
                }
            } else {
                throw new ErrProcException("Ingrese una fecha inicial en el formato requerido");
            }
        } else if (!fechaFin.isEmpty()) {
            throw new ErrProcException("Ingrese una fecha inicial para el rango de fecha");
        }

        return false;

    }

    private boolean revisarCupos(String cupoMin, String cupoMax) throws ErrProcException {

        if (!cupoMin.isEmpty()) {
            try {
                Integer.parseInt(cupoMin);
            } catch (NumberFormatException e) {
                throw new ErrProcException("Ingrese un cupo minimo valido");
            }
            if (!cupoMax.isEmpty()) {
                try {
                    Integer.parseInt(cupoMax);
                } catch (NumberFormatException e) {
                    throw new ErrProcException("Ingrese un cupo maximo valido");
                }

                return true;

            } else {
                throw new ErrProcException("Ingrese un cupo maximo para el rango de cupo");
            }

        } else if (!cupoMax.isEmpty()) {
            throw new ErrProcException("Ingrese un cupo minimo para el rango de cupo");
        }

        return false;

    }

    private LocalDate convertirFecha(String fechaIn) throws DateTimeException {
        String[] fechaParts = fechaIn.split("/");
        LocalDate fecha;
        fecha = LocalDate.of(
                Integer.parseInt(fechaParts[2]),
                Integer.parseInt(fechaParts[1]),
                Integer.parseInt(fechaParts[0]));
        return fecha;
    }

    private String generarTexto(Data_Evento evento, int numero) {
        String texto;

        Data_Participante[] participantes = frame.getConexion().solicitarParticipantes(evento.getCodigoEvento(), "",
                "");

        int montoTotal = 0, participantesValidos = 0, participantesNoValidos = 0;

        for (Data_Participante part : participantes) {
            Data_Inscripcion insc = frame.getConexion().solicitarInscripcion(part.getCorreoParticipante(),
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
