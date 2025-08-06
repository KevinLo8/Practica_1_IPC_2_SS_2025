package com.Practica_1.Backend.Listener;

import java.awt.event.*;
import java.text.*;
import java.util.Locale;

import javax.swing.*;

public class FocLisDinero implements FocusListener {

    private JTextField txf;
    private JLabel lbl;

    public FocLisDinero(JTextField txf, JLabel lbl) {
        this.txf = txf;
        this.lbl = lbl;
    }

    @Override
    public void focusGained(FocusEvent e) {
        lbl.setText(" ");
    }

    @Override
    public void focusLost(FocusEvent e) {

        if (txf.getText().length() > 0) {
            try {

                DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.GERMAN);
                dfs.setDecimalSeparator('.');
                DecimalFormat df = new DecimalFormat("#.00",dfs);
                Float numero = Float.valueOf(df.format(Float.parseFloat(txf.getText())));

                txf.setText(numero.toString());

            } catch (NumberFormatException ex) {
                txf.setText("");
                lbl.setText("Ingrese un número valido");
            }      
        }

    }

}
