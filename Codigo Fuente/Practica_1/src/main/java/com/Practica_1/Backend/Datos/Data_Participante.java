package com.Practica_1.Backend.Datos;

import com.Practica_1.Backend.Exception.SelecionTipoException;

public class Data_Participante {

    private String nombreParticipante;
    private TipoParticipante tipoParticipante;
    private String institucionParticipante;
    private String correoParticipante;
    private Boolean asistenciaValidada;
    
    public enum TipoParticipante {
        ESTUDIANTE,
        PROFESIONAL,
        INVITADO
    }

    public Data_Participante() {
    }

    public Data_Participante(String nombre, String tipo, String intitucion, String correo) {
        try {
            nombreParticipante = nombre;
            setTipoParticipante(tipo);
            institucionParticipante = intitucion;
            correoParticipante = correo;
        } catch (SelecionTipoException e) {
            e.printStackTrace();
        }
    }

    public Data_Participante(String nombre, String tipo, String intitucion, String correo, boolean validacion) {
        try {
            nombreParticipante = nombre;
            setTipoParticipante(tipo);
            institucionParticipante = intitucion;
            correoParticipante = correo;
            asistenciaValidada = validacion;
        } catch (SelecionTipoException e) {
            e.printStackTrace();
        }
    }

    public String getNombreParticipante() {
        return nombreParticipante;
    }
    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
    }
    public TipoParticipante getTipoParticipante() {
        return tipoParticipante;
    }
    public void setTipoParticipante(String tipoParticipante) throws SelecionTipoException {
        switch (tipoParticipante) {
            case "ESTUDIANTE":
                this.tipoParticipante = TipoParticipante.ESTUDIANTE;
                break;
            case "PROFESIONAL":
                this.tipoParticipante = TipoParticipante.PROFESIONAL;
                break;
            case "INVITADO":
                this.tipoParticipante = TipoParticipante.INVITADO;
                break;
            default:
                throw new SelecionTipoException("Tipo de participante no válido: " + tipoParticipante);
        }
    }
    public String getInstitucionParticipante() {
        return institucionParticipante;
    }
    public void setInstitucionParticipante(String institucionParticipante) {
        this.institucionParticipante = institucionParticipante;
    }
    public String getCorreoParticipante() {
        return correoParticipante;
    }
    public void setCorreoParticipante(String correoParticipante) {
        this.correoParticipante = correoParticipante;
    }
    public Boolean getAsistenciaValidada() {
        return asistenciaValidada;
    }
    public void setAsistenciaValidada(Boolean asistenciaValidada) {
        this.asistenciaValidada = asistenciaValidada;
    }

}
