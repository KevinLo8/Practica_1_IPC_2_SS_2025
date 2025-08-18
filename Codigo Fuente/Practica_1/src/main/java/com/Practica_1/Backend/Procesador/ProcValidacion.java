package com.Practica_1.Backend.Procesador;

import com.Practica_1.Backend.Datos.*;
import com.Practica_1.Backend.Exception.*;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Validacion;

public class ProcValidacion {

    private Frame_principal frame;

    public ProcValidacion(Frame_principal frame) {
        this.frame = frame;
    }

    public void guardarPago(IF_Validacion if_Val, String correo, String codigo) throws ErrProcException {

        if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || correo.length() > 255) {
            throw new ErrProcException("Ingrese un correo electrónico válido");
        } else if (codigo.matches("EVT-\\d{8}")) {
            throw new ErrProcException("Ingrese un código de evento válido");
        }


        revisarPago(correo, codigo);

        Data_Inscripcion data_Inscripcion;
        try {
            data_Inscripcion = new Data_Inscripcion(correo, codigo, "", "", 0, 1);
        } catch (SelecionTipoException e) {
            throw new ErrProcException(e.getMessage());
        }
        
        frame.getConexion().guardarValidacion(data_Inscripcion);

        if_Val.invisible();
    }

    private void revisarPago(String correo, String codigo) throws ErrProcException {

        if (!frame.getConexion().consultarParticipante(correo)) {
            throw new ErrProcException("El participante no está registrado. Por favor, regístrelo primero.");
        } else if (!frame.getConexion().consultarEvento(codigo)) {
            throw new ErrProcException("El evento no está registrado. Por favor, regístrelo primero.");
        } else if (!frame.getConexion().consultarInscripcion(correo, codigo)) {
            throw new ErrProcException("El participante no está inscrito en el evento. Por favor, inscribir al participante primero.");
        } else if (!frame.getConexion().consultarPago(correo, codigo)) {
            throw new ErrProcException("El participante no ha realizado el pago de inscripción. Por favor, realizar el pago primero.");
        } else if (frame.getConexion().consultarValidacion(correo, codigo)) {
            throw new ErrProcException("El participante ya ha validado su inscripción.");
        }
        
    }
}
