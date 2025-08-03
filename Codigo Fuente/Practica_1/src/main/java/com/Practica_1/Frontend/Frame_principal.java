package com.Practica_1.Frontend;

import javax.swing.*;

import com.Practica_1.Backend.Conexión_DB.Conexión_DB;
import com.Practica_1.Frontend.MenuItem.*;

import java.awt.*;

public class Frame_principal extends JFrame {

    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JDesktopPane desktop;
    private JTextArea jTextArea;

    private Conexión_DB conexion;

    public Frame_principal(){

        initComponentes();
        conexion = new Conexión_DB(jTextArea);

    }

    private void initComponentes(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds((int)(dim.getWidth() * 0.2) / 2, (int)(dim.getHeight() * 0.2) / 2, (int)(dim.getWidth() * 0.8), (int)(dim.getHeight() * 0.8));
        setTitle("Registro de Trajetas");
        setResizable(false);

        desktop = new JDesktopPane();
        add(desktop, BorderLayout.CENTER);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jM1 = new JMenu("Archivo");
        JMenu jM2 = new JMenu("Acciones");
        JMenu jM3 = new JMenu("Reportes");

        jMenuBar.add(jM1);
        jMenuBar.add(jM2);
        jMenuBar.add(jM3);

        JMI_Ajustes itemA1 = new JMI_Ajustes(this);
        JMI_Salir itemA2 = new JMI_Salir();

        jM1.add(itemA1);
        jM1.add(itemA2);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

        jTextArea = new JTextArea("\n -> Aplicación Inicializada.\n\n");
        jTextArea.setEditable(false);
        jTextArea.setBackground(Color.BLACK);
        jTextArea.setForeground(Color.WHITE);

        jScrollPane.setViewportView(jTextArea);

        setJMenuBar(jMenuBar);

        desktop.add(jScrollPane);
        jScrollPane.setBounds((int)(getWidth() * 0.1), (int)(getHeight() * 0.5), (int)(getWidth() * 0.8), (int)(getHeight() * 0.3));

    }

    public Container getDesktop() {
        return desktop;
    }
 
}
