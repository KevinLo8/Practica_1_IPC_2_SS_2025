package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_RepActividades;

public class JMI_RepActividades extends JMenuItem {

    private IF_RepActividades if_RepActividades;

    public JMI_RepActividades(Frame_principal frame) {
        super("Reporte Actividades");

        if_RepActividades = new IF_RepActividades(frame);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_RepActividades.visible();
            }
        });
    }

}
