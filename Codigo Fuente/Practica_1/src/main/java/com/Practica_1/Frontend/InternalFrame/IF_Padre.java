package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;

import javax.swing.*;

import com.Practica_1.Frontend.Frame_principal;

public class IF_Padre extends JInternalFrame {

    protected Frame_principal frame;
    protected JTextArea textLOG;
    protected int ancho, alto;

    public IF_Padre(Frame_principal frame, JTextArea textLOG, String titulo, int ancho, int alto) {

        super(titulo, false, true, false, false);
        this.frame = frame;
        this.textLOG = textLOG;
        this.ancho = ancho;
        this.alto = alto;

        frame.getDesktop().add(this);

        initComponentes();
    }

    private void initComponentes() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(new Dimension(ancho, alto));
    }

    protected void hacerVisible() {
        setLocation((frame.getWidth() - ancho) / 2, (frame.getHeight() - alto) / 2);
        setVisible(true);
    }

    protected void hacerInvisible(String mensaje) {
        setVisible(false);
        textLOG.append(mensaje);
    }

}
