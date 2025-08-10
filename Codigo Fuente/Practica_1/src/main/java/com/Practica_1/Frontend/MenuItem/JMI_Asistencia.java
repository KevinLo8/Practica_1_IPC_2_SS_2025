package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Asistencia;

public class JMI_Asistencia extends JMenuItem  {

    private IF_Asistencia if_asistencia;

    public JMI_Asistencia(Frame_principal frame, JTextArea textLOG) {
        super("Registrar Asistencia");

        if_asistencia = new IF_Asistencia(frame, textLOG);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_asistencia.visible();
            }
        });
    }

}
