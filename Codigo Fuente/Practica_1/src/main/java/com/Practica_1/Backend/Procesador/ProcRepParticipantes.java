package com.Practica_1.Backend.Procesador;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.Practica_1.Backend.ConexiónArchivo.ConexionArchivo;
import com.Practica_1.Backend.Datos.Data_Participante;
import com.Practica_1.Backend.Datos.Data_Participante.TipoParticipante;
import com.Practica_1.Backend.Exception.ArchivoExistenteException;
import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.GeneradorHTML.GeneradorHTML;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_RepParticipantes;

public class ProcRepParticipantes {

    private Frame_principal frame;

    public ProcRepParticipantes(Frame_principal frame) {

        this.frame = frame;

    }

    public void crearReporte(IF_RepParticipantes if_RepPar, String evento, String tipoPar, String institucionPar) throws ErrProcException {

        if (!evento.matches("EVT-\\d{8}")) {
            throw new ErrProcException("Ingrese un código de evento válido");
        } else if (!frame.getConexion().consultarEvento(evento)) {
            throw new ErrProcException("El evento no está registrado. Por favor, regístrelo primero.");
        } else if (revisarTipo(tipoPar)) {
            throw new ErrProcException("Seleccione un tipo de participante valido");
        } else if (institucionPar.trim().length() > 150) {
            throw new ErrProcException("Ingrese un nombre de intitucion que sea valido.");
        }

        Data_Participante[] participantes = frame.getConexion().solicitarParticipantes(evento, tipoPar, institucionPar);

        String htmlreporte = GeneradorHTML.ReporteParticipantesHTML(participantes);

        String nombreReporte = "ReporteParticipantes_" + evento;
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

        if_RepPar.invisible();
    }

    private boolean revisarTipo(String tipo) {

        TipoParticipante[] tipos = Data_Participante.TipoParticipante.values();

        if (tipo.isEmpty()) {
            return false;
        }

        for (TipoParticipante tipoParticipante : tipos) {
            if (tipo.equals(tipoParticipante.toString())) {
                return false;
            }
        }

        return true;
    }
}
