package com.Practica_1.Backend.Datos;

import com.Practica_1.Backend.Exception.SelecionTipoException;

public class Data_Inscripcion {

    public enum TipoInscripcion {
        ASISTENTE,
        CONFERENCISTA,
        TALLERISTA,
        OTRO
    }

    public String correoParticipante;
    public String codigoEvento;
    public TipoInscripcion tipoInscripcion;

    public String getCorreoParticipante() {
        return correoParticipante;
    }
    public void setCorreoParticipante(String correoParticipante) {
        this.correoParticipante = correoParticipante;
    }
    public String getCodigoEvento() {
        return codigoEvento;
    }
    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }
    public TipoInscripcion getTipoInscripcion() {
        return tipoInscripcion;
    }
    public void setTipoInscripcion(String tipoInscripcion) throws SelecionTipoException {
        switch (tipoInscripcion) {
            case "ASISTENTE":
                this.tipoInscripcion = TipoInscripcion.ASISTENTE;
                break;
            case "CONFERENCISTA":
                this.tipoInscripcion = TipoInscripcion.CONFERENCISTA;
                break;
            case "TALLERISTA":
                this.tipoInscripcion = TipoInscripcion.TALLERISTA;
                break;
            case "OTRO":
                this.tipoInscripcion = TipoInscripcion.OTRO;
                break;
            default:
                throw new SelecionTipoException("Tipo de inscripción no válido: " + tipoInscripcion);
        }
    }

}
