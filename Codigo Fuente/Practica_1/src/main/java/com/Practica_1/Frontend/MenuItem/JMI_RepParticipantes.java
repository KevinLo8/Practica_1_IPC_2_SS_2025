package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_RepParticipantes;

public class JMI_RepParticipantes extends JMenuItem {

    private IF_RepParticipantes if_RepParticipantes;

    public JMI_RepParticipantes(Frame_principal frame, JTextArea textLOG) {
        super("Reporte Participantes");

        if_RepParticipantes = new IF_RepParticipantes(frame, textLOG);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_RepParticipantes.visible();
            }
        });
    }

}
