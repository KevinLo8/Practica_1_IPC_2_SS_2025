package com.Practica_1.Backend.Datos;

import java.time.LocalDate;

import com.Practica_1.Backend.Exception.SelecionTipoException;

public class Data_Evento {

    private String codigoEvento;
    private LocalDate fechaEvento;
    private TipoEvento tipoEvento;
    private String tituloEvento;
    private String ubicacionEvento;
    private int cupoEvento;
    private Double costoinscripcion;

    public enum TipoEvento {
        CHARLA, CONGRESO, TALLER, DEBATE
    }

    public Data_Evento() {
    }

    public Data_Evento(String codigo, LocalDate fecha, String tipo, String titulo, String ubicacion, int cupo,
            Double costo) throws SelecionTipoException {

        codigoEvento = codigo;
        fechaEvento = fecha;
        setTipoEvento(tipo);
        tituloEvento = titulo;
        ubicacionEvento = ubicacion;
        cupoEvento = cupo;
        costoinscripcion = costo;
        
    }

    public String getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(String codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) throws SelecionTipoException {
        switch (tipoEvento) {
            case "CHARLA":
                this.tipoEvento = TipoEvento.CHARLA;
                break;
            case "CONGRESO":
                this.tipoEvento = TipoEvento.CONGRESO;
                break;
            case "TALLER":
                this.tipoEvento = TipoEvento.TALLER;
                break;
            case "DEBATE":
                this.tipoEvento = TipoEvento.DEBATE;
                break;
            default:
                throw new SelecionTipoException("Tipo de evento no válido: " + tipoEvento);
        }
    }

    public String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public String getUbicacionEvento() {
        return ubicacionEvento;
    }

    public void setUbicacionEvento(String ubicacionEvento) {
        this.ubicacionEvento = ubicacionEvento;
    }

    public int getCupoEvento() {
        return cupoEvento;
    }

    public void setCupoEvento(int cupoEvento) {
        this.cupoEvento = cupoEvento;
    }

    public Double getCostoinscripcion() {
        return costoinscripcion;
    }

    public void setCostoinscripcion(Double costoinscripcion) {
        this.costoinscripcion = costoinscripcion;
    }

}
