package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_RegistrarEvento;

public class JMI_Evento extends JMenuItem {

    private IF_RegistrarEvento if_registrarEvento;

    public JMI_Evento(Frame_principal frame) {
        super("Registrar Evento");

        if_registrarEvento = new IF_RegistrarEvento(frame);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_registrarEvento.hacerVisible();
            }
        });
    }

}
