package com.Practica_1.Backend.Procesador;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.Practica_1.Backend.ConexiónArchivo.ConexionArchivo;
import com.Practica_1.Backend.Datos.Data_Actividad;
import com.Practica_1.Backend.Datos.Data_Actividad.TipoActividad;
import com.Practica_1.Backend.Exception.*;
import com.Practica_1.Backend.GeneradorHTML.GeneradorHTML;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_RepActividades;

public class ProcRepActividades {

    private Frame_principal frame;

    public ProcRepActividades(Frame_principal frame) {

        this.frame = frame;

    }

    public void crearReporte(IF_RepActividades if_RepAct, String evento, String tipoAct, String correo) throws ErrProcException {

        if (!evento.matches("EVT-\\d{8}")) {
            throw new ErrProcException("Ingrese un código de evento válido");
        } else if (!frame.getConexion().consultarEvento(evento)) {
            throw new ErrProcException("El evento no está registrado. Por favor, regístrelo primero.");
        } else if (revisarTipo(tipoAct)) {
            throw new ErrProcException("Seleccione un tipo de actividad valido");
        } else if (correo.length() > 50) {
            throw new ErrProcException("Ingrese un correo electrónico válido");
        } else if (!correo.isEmpty()) {
            if (!frame.getConexion().consultarParticipante(correo)) {
                throw new ErrProcException("El participante no está registrado. Por favor, regístrelo primero.");
            }
        }

        Data_Actividad[] actividades = frame.getConexion().solicitarActividades(evento, tipoAct, correo);

        if (actividades == null) {
            throw new ErrProcException("Error al solicitar las actividades");
        }

        for (Data_Actividad data_Act : actividades) {
            int participantes = frame.getConexion().solicitarCantidadParticipantes(data_Act.getCodigoActividad());

            if (participantes == -1) {
                throw new ErrProcException("Error al solicitar la cantidad de participantes");
            }

            data_Act.setParticipantes(participantes);
        }

        String htmlreporte = GeneradorHTML.ReporteActividadesHTML(actividades);

        String nombreReporte = "ReporteActividades_" + evento;
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

        if_RepAct.invisible();
    }

    private boolean revisarTipo(String tipo) {

        TipoActividad[] tipos = Data_Actividad.TipoActividad.values();

        if (tipo.isEmpty()) {
            return false;
        }

        for (TipoActividad tipoAc : tipos) {
            if (tipo.equals(tipoAc.toString())) {
                return false;
            }
        }

        return true;
    }
}
