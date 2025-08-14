package com.Practica_1.Backend.Procesador;

import java.io.IOException;

import com.Practica_1.Backend.ConexiónArchivo.ConexionArchivo;
import com.Practica_1.Backend.Datos.*;
import com.Practica_1.Backend.Exception.*;
import com.Practica_1.Backend.GeneradorHTML.GeneradorHTML;
import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Certificado;

public class ProcCertificado {

    private Frame_principal frame;

    public ProcCertificado(Frame_principal frame) {
        this.frame = frame;
    }

    public void crearCertificado(IF_Certificado if_Cer, String correo, String codigo) throws ErrProcException {

        if (correo.length() > 50 || correo.isEmpty()) {
            throw new ErrProcException("Ingrese un correo electrónico válido");
        } else if (!codigo.matches("EVT-\\d{8}")) {
            throw new ErrProcException("Ingrese un código de evento válido");
        }

        revisarCertificado(correo, codigo);

        Data_Participante data_Participante = frame.getConexion().solicitarParticipante(correo);
        Data_Actividad[] data_Actividad = frame.getConexion().solicitarAsistencias(correo, codigo);

        String htmlCertificado = GeneradorHTML.CertificadoHTML(data_Participante, codigo, data_Actividad);

        String nombreCertificado = correo + "_" + codigo;
        nombreCertificado = ConexionArchivo.GenerarNombre(frame.getPathSalida(), nombreCertificado);

        try {
            ConexionArchivo.guardarArchivo(frame.getPathSalida(), htmlCertificado, nombreCertificado);
        } catch (IOException e) {
            throw new ErrProcException("Ha ocurrido un error al guardar el archivo.");
        } catch (ArchivoExistenteException e) {
            throw new ErrProcException("Ya existe un archivo con el nombre que se intento guardar.");
        }

        if_Cer.invisible();
    }

    private void revisarCertificado(String correo, String codigo) throws ErrProcException {

        Data_Inscripcion data_ins = frame.getConexion().solicitarInscripcion(correo, codigo);
        Data_Actividad[] data_act = frame.getConexion().solicitarAsistencias(correo, codigo);

        if (!frame.getConexion().consultarParticipante(correo)) {
            throw new ErrProcException("El participante no está registrado. Por favor, regístrelo primero.");
        } else if (!frame.getConexion().consultarEvento(codigo)) {
            throw new ErrProcException("El evento no está registrado. Por favor, regístrelo primero.");
        } else if (data_ins == null) {
            throw new ErrProcException("El participante no está inscrito en el evento. Por favor, inscribir al participante primero.");
        } else if (data_ins.getMontoPago() == 0.00) {
            throw new ErrProcException("El participante no ha realizado el pago de inscripción. Por favor, realizar el pago primero.");
        } else if (data_ins.getValidacion() == false) {
            throw new ErrProcException("La inscripción del participante no ha sido validada. Por favor, validar la inscripción primero.");
        } else if (data_act.length == 0) {
            throw new ErrProcException("El participante no ha registrado ningina asistencia.");
        }

    }
}
