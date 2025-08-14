package com.Practica_1.Backend.Procesador;

import com.Practica_1.Backend.Datos.Data_Evento;
import com.Practica_1.Backend.Datos.Data_Inscripcion;
import com.Practica_1.Backend.Datos.Data_Participante;
import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Exception.SelecionTipoException;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Pago;

public class ProcPago {

    private Frame_principal frame;

    public ProcPago(Frame_principal frame) {
        this.frame = frame;
    }

    public void guardarPago(IF_Pago if_Pag, String correo, String codigo, String tipo, String monto) throws ErrProcException {

        if (correo.length() > 50 || correo.isEmpty()) {
            throw new ErrProcException("Ingrese un correo electrónico válido");
        } else if (!codigo.matches("EVT-\\d{8}")) {
            throw new ErrProcException("Ingrese un código de evento válido");
        } else if (tipo.isEmpty()) {
            throw new ErrProcException("Seleccione un tipo de pago valido");
        } else if (monto.matches("\\d+.\\d{2}")) {
            throw new ErrProcException("Ingrese un monto de pago valido");
        }

        Double dataMonto;

        dataMonto = Double.parseDouble(monto);
        if (dataMonto <= 0) {
            throw new ErrProcException("Ingrese un monto de pago mayor a 0");
        }


        revisarPago(correo, codigo, dataMonto);

        Data_Inscripcion data_Inscripcion;
        try {
            data_Inscripcion = new Data_Inscripcion(correo, codigo, "", tipo, dataMonto, 0);
        } catch (SelecionTipoException e) {
            throw new ErrProcException(e.getMessage());
        }
        
        frame.getConexion().guardarInscripción(data_Inscripcion);

        if_Pag.invisible();
    }

    private void revisarPago(String correo, String codigo, Double dataMonto) throws ErrProcException {

        Data_Participante data_par = frame.getConexion().solicitarParticipante(correo);
        Data_Evento data_eve = frame.getConexion().solicitarEvento(codigo);
        Data_Inscripcion data_ins = frame.getConexion().solicitarInscripcion(correo, codigo);

        if (data_par == null) {
            throw new ErrProcException("El participante no está registrado. Por favor, regístrelo primero.");
        } else if (data_eve == null) {
            throw new ErrProcException("El evento no está registrado. Por favor, regístrelo primero.");
        } else if (data_ins == null) {
            throw new ErrProcException("El participante no está inscrito en el evento. Por favor, incribalo primero.");
        } else if (data_ins.getTipoPago() != null) {
            throw new ErrProcException("El participante ya pago la inscripción.");
        }else if (dataMonto < data_eve.getCostoinscripcion()) {
            throw new ErrProcException("El monto de pago es insuficiente para la inscripción.");
        }
        
    }
}
