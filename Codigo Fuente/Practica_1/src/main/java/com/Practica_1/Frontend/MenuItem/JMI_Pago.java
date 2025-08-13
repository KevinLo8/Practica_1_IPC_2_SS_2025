package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import com.Practica_1.Frontend.Frame_principal;
import com.Practica_1.Frontend.InternalFrame.IF_Pago;

public class JMI_Pago extends JMenuItem {

    private IF_Pago if_pago;

    public JMI_Pago(Frame_principal frame, JTextArea textLOG) {
        super("Registrar Pago");

        if_pago = new IF_Pago(frame, textLOG);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if_pago.visible();
            }
        });
    }

}
