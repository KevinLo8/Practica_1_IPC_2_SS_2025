package com.Practica_1.Backend.Datos;

import com.Practica_1.Backend.Exception.SelecionTipoException;

public class Data_Inscripcion {

    public enum TipoInscripcion {
        ASISTENTE,
        CONFERENCISTA,
        TALLERISTA,
        OTRO
    }
        
    public enum TipoPago {
        EFECTIVO, 
        TRANSFERENCIA, 
        TARJETA
    }

    public Data_Inscripcion() {
    }

    public Data_Inscripcion(double montoPago, int validacion) {
        this.montoPago = montoPago;
        this.validacion = validacion == 1;
    }

    public Data_Inscripcion(String correoParticipante, String codigoEvento, String tipoInscripcion,
            String tipoPago, double montoPago, int validacion) throws SelecionTipoException {
        this.correoParticipante = correoParticipante;
        this.codigoEvento = codigoEvento;
        setTipoInscripcion(tipoInscripcion);
        setTipoPago(tipoPago);
        this.montoPago = montoPago;
        this.validacion = validacion == 1;
    }

    private String correoParticipante;
    private String codigoEvento;
    private TipoInscripcion tipoInscripcion;
    private TipoPago tipoPago;
    private double montoPago;
    private boolean validacion;


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
    public TipoPago getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(String tipoPagoString) throws SelecionTipoException {
        switch (tipoPagoString) {
            case "EFECTIVO":
                this.tipoPago = TipoPago.EFECTIVO;
                break;
            case "TRANSFERENCIA":
                this.tipoPago = TipoPago.TRANSFERENCIA;
                break;
            case "TARJETA":
                this.tipoPago = TipoPago.TARJETA;
                break;
            default:
                throw new SelecionTipoException("Tipo de pago no válido: " + tipoPagoString);
        }
    }
    public double getMontoPago() {
        return montoPago;
    }
    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }
    public boolean getValidacion() {
        return validacion;
    }

}
