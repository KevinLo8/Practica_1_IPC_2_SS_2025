package com.Practica_1.Backend.Procesador;

import com.Practica_1.Backend.Datos.*;
import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Exception.SelecionTipoException;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Inscripcion;

public class ProcInscripcion {

    private Frame_principal frame;

    public ProcInscripcion(Frame_principal frame) {
        this.frame = frame;
    }

    public void guardarAsistencia(IF_Inscripcion if_Ins, String correo, String codigo, String tipo) throws ErrProcException {

        if (correo.length() > 50 || correo.isEmpty()) {
            throw new ErrProcException("Ingrese un correo electrónico válido");
        } else if (codigo.length() > 7 || codigo.isEmpty()) {
            throw new ErrProcException("Ingrese un código de evento válido");
        } else if (tipo.isEmpty()) {
            throw new ErrProcException("Seleccione un tipo de inscripción valido");
        }

        revisarInscripcion(correo, codigo);

        Data_Inscripcion data_Inscripcion;
        try {
            data_Inscripcion = new Data_Inscripcion(correo, codigo, tipo, "", 0, 0);
        } catch (SelecionTipoException e) {
            throw new ErrProcException(e.getMessage());
        }
        
        frame.getConexion().guardarInscripción(data_Inscripcion);

        if_Ins.invisible();
    }

    private void revisarInscripcion(String correo, String codigo) throws ErrProcException {

        Data_Participante data_par = frame.getConexion().solicitarParticipante(correo);
        Data_Evento data_eve = frame.getConexion().solicitarEvento(codigo);
        Data_Inscripcion data_ins = frame.getConexion().solicitarInscripcion(correo, codigo);

        if (data_par == null) {
            throw new ErrProcException("El participante no está registrado. Por favor, regístrelo primero.");
        } else if (data_eve == null) {
            throw new ErrProcException("El evento no está registrado. Por favor, regístrelo primero.");
        } else if (data_ins != null) {
            throw new ErrProcException("El participante ya está inscrito en este evento.");
        }
        
    }
}
