package com.Practica_1.Backend.Procesador;

import com.Practica_1.Backend.Datos.*;
import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Asistencia;

public class ProcAsistencia {

    private Frame_principal frame;

    public ProcAsistencia(Frame_principal frame) {
        this.frame = frame;
    }

    public void guardarAsistencia(IF_Asistencia if_Asi, String correo, String codigo) throws ErrProcException {

        if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || correo.length() > 255) {
            throw new ErrProcException("Ingrese un correo electrónico válido");
        } else if (!codigo.matches("EVT-\\d{8}")) {
            throw new ErrProcException("Ingrese un código de actividad válido");
        }

        revisarAsistencia(correo, codigo);

        int iD = frame.getConexion().solicitarID();

        if (iD == 0) {
            throw new ErrProcException("Error al solicitar el ID");
        }
        
        frame.getConexion().guardarAsistencia(iD, correo, codigo);

        if_Asi.invisible();
    }

    private void revisarAsistencia(String correo, String codigo) throws ErrProcException {

        Data_Inscripcion data_ins = frame.getConexion().solicitarAsiInscripcion(correo, codigo);
        Data_Actividad data_act = frame.getConexion().solicitarAsiActividad(codigo);

        if (!frame.getConexion().consultarParticipante(correo)) {
            throw new ErrProcException("El participante no está registrado. Por favor, regístrelo primero.");
        } else if (data_ins == null) {
            throw new ErrProcException("El participante no está inscrito en el evento. Por favor, inscribir al participante primero.");
        } else if (data_ins.getMontoPago() == 0.00) {
            throw new ErrProcException("El participante no ha realizado el pago de inscripción. Por favor, realizar el pago primero.");
        } else if (data_ins.getValidacion() == false) {
            throw new ErrProcException("La inscripción del participante no ha sido validada. Por favor, validar la inscripción primero.");
        } else if (data_act == null) {
            throw new ErrProcException("La actividad con el código proporcionado no existe.");
        } else if (data_act.getCorreoImpartidor().equals(correo)) {
            throw new ErrProcException("El participante no puede registrarse a sí mismo como asistente de su propia actividad.");
        } else if (frame.getConexion().revisarAsistencia(correo, codigo)) {
            throw new ErrProcException("El participante ya ha registrado asistencia para esta actividad.");
        } else if (frame.getConexion().revisarCupoAsistencia(codigo, data_act.getCupoMaximo())) {
            throw new ErrProcException("No hay cupo disponible para registrar asistencia en esta actividad.");
        }
        
    }
}
