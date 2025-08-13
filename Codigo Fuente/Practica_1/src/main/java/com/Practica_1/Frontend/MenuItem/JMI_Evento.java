package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Evento;

public class JMI_Evento extends JMenuItem {

    private IF_Evento if_Evento;

    public JMI_Evento(Frame_principal frame, JTextArea textLOG) {
        super("Registrar Evento");

        if_Evento = new IF_Evento(frame, textLOG);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_Evento.visible();
            }
        });
    }

}
