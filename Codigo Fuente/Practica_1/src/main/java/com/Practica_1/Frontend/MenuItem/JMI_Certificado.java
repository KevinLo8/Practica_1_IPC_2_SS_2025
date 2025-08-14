package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Certificado;

public class JMI_Certificado extends JMenuItem {

    private IF_Certificado if_certificado;

    public JMI_Certificado(Frame_principal frame, JTextArea textLOG) {
        super("Crear Certificado");

        if_certificado = new IF_Certificado(frame, textLOG);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_certificado.visible();
            }
        });
    }

}
