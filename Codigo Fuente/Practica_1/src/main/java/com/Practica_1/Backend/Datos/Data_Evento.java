package com.Practica_1.Backend.Datos;

import java.time.LocalDate;

public class Data_Evento {

    private String codigoEvento;
    private LocalDate fechaEvento;
    private String tipoEvento;
    private String tituloEvento;
    private String ubicacionEvento;
    private int cupoEvento;

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
    public String getTipoEvento() {
        return tipoEvento;
    }
    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
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

}
