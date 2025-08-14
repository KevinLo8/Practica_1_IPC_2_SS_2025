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

        if (correo.length() > 50 || correo.isEmpty()) {
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

        Data_Participante data_par = frame.getConexion().solicitarParticipante(correo);
        Data_Evento data_eve = frame.getConexion().solicitarEvento(codigo);
        Data_Inscripcion data_ins = frame.getConexion().solicitarInscripcion(correo, codigo);

        if (data_par == null) {
            throw new ErrProcException("El participante no está registrado. Por favor, regístrelo primero.");
        } else if (data_eve == null) {
            throw new ErrProcException("El evento no está registrado. Por favor, regístrelo primero.");
        } else if (data_ins == null) {
            throw new ErrProcException("El participante no está inscrito en el evento. Por favor, incribalo primero.");
        } else if (data_ins.getTipoInscripcion() == null) {
            throw new ErrProcException("El participante no a pagado su inscripción. Por favor, realize el pago primero.");
        } else if (data_ins.getValidacion()) {
            throw new ErrProcException("El participante ya ha validado su inscripción.");
        }
        
    }
}
