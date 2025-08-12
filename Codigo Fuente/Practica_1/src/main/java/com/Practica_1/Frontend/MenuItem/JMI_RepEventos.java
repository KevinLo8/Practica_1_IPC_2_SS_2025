package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_RepEventos;

public class JMI_RepEventos extends JMenuItem {

    private IF_RepEventos if_RepEventos;

    public JMI_RepEventos(Frame_principal frame, JTextArea textLOG) {
        super("Reporte Eventos");

        if_RepEventos = new IF_RepEventos(frame, textLOG);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_RepEventos.visible();
            }
        });
    }

}
