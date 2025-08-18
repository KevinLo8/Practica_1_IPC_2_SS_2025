package com.Practica_1.Backend.Procesador;

import com.Practica_1.Backend.Datos.Data_Actividad;
import com.Practica_1.Backend.Exception.*;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Actividad;

public class ProcActividad {

    private Frame_principal frame;

    public ProcActividad(Frame_principal frame) {
        this.frame = frame;
    }

    public void guardarActividad(IF_Actividad if_Act, String codigo, String codigoEvento, String tipo, String titulo,
            String correoImpartidor, String horaInicio, String horaFin, String cupoMaximo) throws ErrProcException {

        if (!codigo.matches("ACT-\\d{8}")) {
            throw new ErrProcException("Ingrese un código de actividad válido");
        } else if (!codigoEvento.matches("EVT-\\d{8}")) {
            throw new ErrProcException("Ingrese un código de evento válido");
        } else if (tipo.isEmpty()) {
            throw new ErrProcException("Seleccione un tipo de actividad");
        } else if (titulo.length() > 200) {
            throw new ErrProcException("Ingrese un título de actividad válido");
        } else if (!correoImpartidor.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || correoImpartidor.length() > 255) {
            throw new ErrProcException("Ingrese un correo electrónico válido");
        } else if (!horaInicio.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new ErrProcException("Ingrese una hora de inicio válida (HH:MM)");
        } else if (!horaFin.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new ErrProcException("Ingrese una hora de fin válida (HH:MM)");
        } else if (!cupoMaximo.matches("\\d+")) {
            throw new ErrProcException("Ingrese un cupo máximo válido");
        }

        int dataCupo = Integer.parseInt(cupoMaximo);

        revisarActividad(codigo, correoImpartidor, codigoEvento, horaInicio, horaFin);

        Data_Actividad data_Actividad;
        try {
            data_Actividad = new Data_Actividad(codigo, codigoEvento, tipo, titulo, correoImpartidor, horaInicio, horaFin, dataCupo);
        } catch (SelecionTipoException e) {
            throw new ErrProcException(e.getMessage());
        }

        frame.getConexion().guardarActividad(data_Actividad);

        if_Act.invisible();
    }

    private void revisarActividad(String codigo, String correo, String codigoEvento, String horaInicio, String horaFin) throws ErrProcException {

        if (!frame.getConexion().consultarEvento(codigoEvento)) {
            throw new ErrProcException("No existe el evento ingresado. Por favor registrar el evento primero.");
        } else if (frame.getConexion().consultarParticipante(correo)) {
            throw new ErrProcException("El participante no está registrado. Por favor, regístrelo primero.");
        } else if (frame.getConexion().consultarActividad(codigo)) {
            throw new ErrProcException("Ya existe una actividad con el código ingresado.");
        } else if (frame.getConexion().solicitarParticipante(correo).getTipoParticipante().toString().equals("ASISTENTE")) {
            throw new ErrProcException("La actividad no puede ser impartida por un asistente");
        } else if (horaFin.compareTo(horaInicio) <= 0) {
            throw new ErrProcException("La hora de fin debe ser posterior a la hora de inicio.");
        }

    }
}
