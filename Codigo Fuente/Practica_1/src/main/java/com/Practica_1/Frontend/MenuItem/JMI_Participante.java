package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Participante;

public class JMI_Participante extends JMenuItem {

    private IF_Participante if_participante;

    public JMI_Participante(Frame_principal frame) {
        super("Registrar Participante");

        if_participante = new IF_Participante(frame);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_participante.hacerVisible();
            }
        });
    }

}
