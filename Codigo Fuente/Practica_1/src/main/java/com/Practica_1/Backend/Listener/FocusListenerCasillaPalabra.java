package com.Practica_1.Backend.Listener;

import java.awt.event.*;

import javax.swing.*;

public class FocusListenerCasillaPalabra implements FocusListener {

    private JLabel lbl;

    public FocusListenerCasillaPalabra(JLabel lbl) {
        this.lbl = lbl;
    }

    @Override
    public void focusGained(FocusEvent e) {
        lbl.setText(" ");
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

}
