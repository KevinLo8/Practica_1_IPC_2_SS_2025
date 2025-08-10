package com.Practica_1.Backend.Datos;

import com.Practica_1.Backend.Exception.SelecionTipoException;

public class Data_Actividad {

    public enum TipoActividad {
        CHARLA,
        TALLER,
        DEBATE,
        OTRA
    }

    public Data_Actividad() {
        // Constructor por defecto
    }

    public Data_Actividad(String correoImpartidor, int cupo) {
        setCorreoImpartidor(correoImpartidor);
        setCupoMaximo(cupo);
    }

    public Data_Actividad(String codigo, String codigoEvento, String tipo, String titulo, String correoImpartidor, String horaInicio, String horaFin, int cupo) {
        try {
            codigoActividad = codigo;
            this.codigoEvento = codigoEvento;
            setTipoActividad(tipo);
            tituloActividad = titulo;
            this.correoImpartidor = correoImpartidor;
            this.horaInicio = horaInicio;
            this.horaFin = horaFin;
            cupoMaximo = cupo;
        } catch (SelecionTipoException e) {
            e.printStackTrace();
        }
    }

    private String codigoActividad;
    private String codigoEvento;
    private TipoActividad tipoActividad;
    private String tituloActividad;
    private String correoImpartidor;
    private String horaInicio;
    private String horaFin;
    private int cupoMaximo;
    private int participantes;

    public String getCodigoActividad() {
        return codigoActividad;
    }
    public void setCodigoActividad(String codigoActividad) {
        this.codigoActividad = codigoActividad;
    }
    public String getCodigoEvento() {
        return codigoEvento;
    }
    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }
    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }
    public void setTipoActividad(String tipoActividad) throws SelecionTipoException {
        switch (tipoActividad) {
            case "CHARLA":
                this.tipoActividad = TipoActividad.CHARLA;
                break;
            case "TALLER":
                this.tipoActividad = TipoActividad.TALLER;
                break; 
            case "DEBATE":
                this.tipoActividad = TipoActividad.DEBATE;
                break;
            case "OTRA":
                this.tipoActividad = TipoActividad.OTRA;
                break;
            default:
                throw new SelecionTipoException("Tipo de actividad no válido: " + tipoActividad);
        }
    }
    public String getTituloActividad() {
        return tituloActividad;
    }
    public void setTituloActividad(String tituloActividad) {
        this.tituloActividad = tituloActividad;
    }
    public String getCorreoImpartidor() {
        return correoImpartidor;
    }
    public void setCorreoImpartidor(String correoImpartidor) {
        this.correoImpartidor = correoImpartidor;
    }
    public String getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    public String getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    public int getCupoMaximo() {
        return cupoMaximo;
    }
    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
    public int getParticipantes() {
        return participantes;
    }
    public void setParticipantes(int participantes) {
        this.participantes = participantes;
    }

}
