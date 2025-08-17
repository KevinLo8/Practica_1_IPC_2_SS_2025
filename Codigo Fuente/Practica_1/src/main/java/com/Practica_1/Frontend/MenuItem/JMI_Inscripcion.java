package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.JMenuItem;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Inscripcion;

public class JMI_Inscripcion extends JMenuItem {

    private IF_Inscripcion if_inscripcion;

    public JMI_Inscripcion(Frame_principal frame) {
        super("Registrar Inscripción");

        if_inscripcion = new IF_Inscripcion(frame);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_inscripcion.visible();
            }
        });
    }

}
