package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Actividad;

public class JMI_Actividad extends JMenuItem {
    
    private IF_Actividad if_actividad;

    public JMI_Actividad(Frame_principal frame) {
        super("Registrar Actividad");

        if_actividad = new IF_Actividad(frame);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_actividad.hacerVisible();
            }
        });
    }

}
