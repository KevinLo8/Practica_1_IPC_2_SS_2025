package com.Practica_1.Frontend.MenuItem;

import java.awt.event.*;

import javax.swing.*;

public class JMI_Salir extends JMenuItem {
    
    public JMI_Salir() {
        super("Salir");
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
    }

}
