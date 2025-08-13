package com.Practica_1.Backend.Procesador;

import com.Practica_1.Backend.Datos.Data_Participante;
import com.Practica_1.Backend.Exception.*;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Participante;

public class ProcParticipante {

    private Frame_principal frame;

    public ProcParticipante(Frame_principal frame) {
        this.frame = frame;
    }

    public void guardarAsistencia(IF_Participante if_Eve, String nombre, String tipo, String institucion, String correo) throws ErrProcException {

        if (nombre.length() > 45 || nombre.isEmpty()) {
            throw new ErrProcException("Ingrese un nombre valido");
        } else if (tipo.isEmpty()) {
            throw new ErrProcException("Seleccione un tipo de participante valido");
        } else if (institucion.length() > 150 || institucion.isEmpty()) {
            throw new ErrProcException("Ingrese una institución valida");
        } else if (correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || correo.length() > 255) {
            throw new ErrProcException("Ingrese un correo electrónico valido");
        }

        if (frame.getConexion().consultarParticipante(correo)) {
            throw new ErrProcException("Ya existe un participante registrado con este correo.");
        }

        Data_Participante data_Participante;
        try {
            data_Participante = new Data_Participante(nombre, tipo, institucion, correo);
        } catch (SelecionTipoException e) {
            throw new ErrProcException(e.getMessage());
        }

        frame.getConexion().guardarParticipante(data_Participante);

        if_Eve.invisible();
    }

}
