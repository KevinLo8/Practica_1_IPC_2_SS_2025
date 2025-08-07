package com.Practica_1.Backend.Datos;

import com.Practica_1.Backend.Exception.SelecionTipoException;

public class Data_Actividad {

    public enum TipoActividad {
        CHARLA,
        TALLER,
        DEBATE,
        OTRA
    }

    public String codigoActividad;
    public String codigoEvento;
    public TipoActividad tipoActividad;
    public String tituloActividad;
    public String correoImpartidor;
    public String horaInicio;
    public String horaFin;
    public int cupoMaximo;

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

}
