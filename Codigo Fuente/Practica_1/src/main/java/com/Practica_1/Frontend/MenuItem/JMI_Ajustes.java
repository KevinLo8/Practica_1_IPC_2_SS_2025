package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.JMenuItem;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Ajustes;

public class JMI_Ajustes extends JMenuItem {

    private IF_Ajustes if_Ajustes;

    public JMI_Ajustes(Frame_principal frame) {
        super("Ajustes");

        if_Ajustes = new IF_Ajustes(frame);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_Ajustes.visible();
            }
        });
    }

}
