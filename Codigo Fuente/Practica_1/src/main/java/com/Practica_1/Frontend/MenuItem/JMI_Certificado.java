package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.JMenuItem;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Certificado;

public class JMI_Certificado extends JMenuItem {

    private IF_Certificado if_certificado;

    public JMI_Certificado(Frame_principal frame) {
        super("Crear Certificado");

        if_certificado = new IF_Certificado(frame);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_certificado.hacerVisible();
            }
        });
    }

}
