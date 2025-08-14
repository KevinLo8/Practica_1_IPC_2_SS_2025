package com.Practica_1.Backend.Procesador;

import java.time.DateTimeException;
import java.time.LocalDate;

import com.Practica_1.Backend.Datos.Data_Evento;
import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Exception.SelecionTipoException;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Evento;

public class ProcEvento {

    private Frame_principal frame;

    public ProcEvento(Frame_principal frame) {
        this.frame = frame;
    }

    public void guardarAsistencia(IF_Evento if_Eve, String codigo, String fecha, String tipo, String titulo,
            String ubicacion, String cupo, String costo) throws ErrProcException {

        if (!codigo.matches("EVT-\\d{8}")) {
            throw new ErrProcException("Ingrese un código de evento válido");
        } else if (fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            throw new ErrProcException("Ingrese una fecha del evento valida");
        } else if (tipo.isEmpty()) {
            throw new ErrProcException("Seleccione un tipo de evento valido");
        } else if (titulo.length() > 50 || titulo.isEmpty()) {
            throw new ErrProcException("Ingrese una titulo de evento valido");
        } else if (ubicacion.length() > 150 || ubicacion.isEmpty()) {
            throw new ErrProcException("Ingrese una ubicación valida");
        } else if (cupo.matches("\\d+")) {
            throw new ErrProcException("Ingrese un cupo máximo valido");
        } else if (cupo.matches("\\d+.\\d{2}")) {
            throw new ErrProcException("Ingrese un costo de inscripción valido");
        }

        LocalDate dataFecha;
        int dataCupo;
        Double dataCosto;

        try {
            String[] fechaParts = fecha.split("/");
            dataFecha = LocalDate.of(
                    Integer.parseInt(fechaParts[2]),
                    Integer.parseInt(fechaParts[1]),
                    Integer.parseInt(fechaParts[0]));
        } catch (DateTimeException e) {
            throw new ErrProcException("Ingrese una fecha valida");
        }

        dataCupo = Integer.parseInt(cupo);
        if (dataCupo <= 0) {
            throw new ErrProcException("Ingrese un cupo mayor a 0");
        }

        dataCosto = Double.parseDouble(costo);
        if (dataCosto <= 0) {
            throw new ErrProcException("Ingrese un costo de inscripción mayor a 0");
        }

        Data_Evento data_Evento;
        try {
            data_Evento = new Data_Evento(codigo, dataFecha, tipo, titulo, ubicacion, dataCupo, dataCosto);
        } catch (SelecionTipoException e) {
            throw new ErrProcException(e.getMessage());
        }

        frame.getConexion().guardarEvento(data_Evento);

        if_Eve.invisible();
    }

}
