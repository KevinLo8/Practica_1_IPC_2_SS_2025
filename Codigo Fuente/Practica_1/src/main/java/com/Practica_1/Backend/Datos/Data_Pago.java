package com.Practica_1.Backend.Datos;

import com.Practica_1.Backend.Exception.SelecionTipoException;

public class Data_Pago {

    public enum TipoPago {
        EFECTIVO, TRANSFERENCIA, TARJETA
    }

    private String correoParticipante;
    private String codigoEvento;
    private TipoPago tipoPago;
    private double montoPago;

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
    
}
