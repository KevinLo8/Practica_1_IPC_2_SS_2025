package com.Practica_1.Frontend;

import javax.swing.*;

import java.awt.*;

public class Frame_principal extends JFrame {

    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JDesktopPane desktop;

    public Frame_principal(){

        initComponentes();
    }

    private void initComponentes(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds((int)(dim.getWidth() * 0.2) / 2, (int)(dim.getHeight() * 0.2) / 2, (int)(dim.getWidth() * 0.8), (int)(dim.getHeight() * 0.8));
        setTitle("Registro de Trajetas");

        desktop = new JDesktopPane();
        add(desktop, BorderLayout.CENTER);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jM1 = new JMenu("Archivo");
        JMenu jM2 = new JMenu("Acciones");
        JMenu jM3 = new JMenu("Reportes");

        jMenuBar.add(jM1);
        jMenuBar.add(jM2);
        jMenuBar.add(jM3);

        setJMenuBar(jMenuBar);

    }

}
