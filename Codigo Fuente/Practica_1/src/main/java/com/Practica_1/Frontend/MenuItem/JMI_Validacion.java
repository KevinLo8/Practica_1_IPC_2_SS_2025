package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Validacion;

public class JMI_Validacion extends JMenuItem {

    private IF_Validacion if_validacion;

    public JMI_Validacion(Frame_principal frame, JTextArea textLOG) {
        super("Validar Inscripción");

        if_validacion = new IF_Validacion(frame, textLOG);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_validacion.visible();
            }
        });
    }

}
