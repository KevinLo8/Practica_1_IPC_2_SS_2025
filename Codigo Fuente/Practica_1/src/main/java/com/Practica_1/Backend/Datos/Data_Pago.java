package com.Practica_1.Backend.Datos;

import com.Practica_1.Backend.Exception.TipoPagoException;

public class Data_Pago {

    public enum TipoPago {
        EFECTIVO, TRANSFERENCIA, TARJETA
    }

    private String correoParticipante;
    private String numeroEvento;
    private TipoPago tipoPago;
    private double montoPago;

    public String getCorreoParticipante() {
        return correoParticipante;
    }
    public void setCorreoParticipante(String correoParticipante) {
        this.correoParticipante = correoParticipante;
    }
    public String getNumeroEvento() {
        return numeroEvento;
    }
    public void setNumeroEvento(String nombreEvento) {
        this.numeroEvento = nombreEvento;
    }
    public TipoPago getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(String tipoPagoString) throws TipoPagoException {
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
                throw new TipoPagoException("Tipo de pago no válido: " + tipoPagoString);
        }
    }
    public double getMontoPago() {
        return montoPago;
    }
    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }
    
}
