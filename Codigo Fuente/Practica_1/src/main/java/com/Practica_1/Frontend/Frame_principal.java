package com.Practica_1.Frontend;

import javax.swing.*;

import com.Practica_1.Backend.Conexión_DB.Conexión_DB;
import com.Practica_1.Frontend.MenuItem.*;

import java.awt.*;

public class Frame_principal extends JFrame {

    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JDesktopPane desktop;
    private JTextArea textLOG;

    private String pathSalida;

    private Conexión_DB conexion;

    public Frame_principal() {

        initComponentes();
        conexion = new Conexión_DB(textLOG);

    }

    private void initComponentes() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds((int) (dim.getWidth() * 0.2) / 2, (int) (dim.getHeight() * 0.2) / 2, (int) (dim.getWidth() * 0.8),
                (int) (dim.getHeight() * 0.8));
        setTitle("Registro de Eventos");
        setResizable(false);

        desktop = new JDesktopPane();
        add(desktop, BorderLayout.CENTER);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jM1 = new JMenu("Archivo");
        JMenu jM2 = new JMenu("Acciones");
        JMenu jM3 = new JMenu("Reportes");

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

        textLOG = new JTextArea("\n -> Aplicación Inicializada.");
        textLOG.setEditable(false);
        textLOG.setBackground(Color.BLACK);
        textLOG.setForeground(Color.WHITE);

        jMenuBar.add(jM1);
        jMenuBar.add(jM2);
        jMenuBar.add(jM3);

        jM1.add(new JMI_Ajustes(this));
        jM1.add(new JMI_Salir());

        jM2.add(new JMI_Evento(this));
        jM2.add(new JMI_Participante(this));
        jM2.add(new JMI_Inscripcion(this));
        jM2.add(new JMI_Pago(this));
        jM2.add(new JMI_Validacion(this));
        jM2.add(new JMI_Actividad(this));
        jM2.add(new JMI_Asistencia(this));
        jM2.add(new JMI_Certificado(this));

        jM3.add(new JMI_RepParticipantes(this));
        jM3.add(new JMI_RepActividades(this));
        jM3.add(new JMI_RepEventos(this));

        jScrollPane.setViewportView(textLOG);

        setJMenuBar(jMenuBar);

        desktop.add(jScrollPane);
        jScrollPane.setBounds(0, (int) (getHeight() * 0.678), (int) (getWidth() - 17), (int) (getHeight() * 0.25));

    }

    public Container getDesktop() {
        return desktop;
    }

    public Conexión_DB getConexion() {
        return conexion;
    }

    public String getPathSalida() {
        return pathSalida;
    }

    public void setPathSalida(String pathSalida) {
        this.pathSalida = pathSalida;
    }

    public void appendTextLog(String texto) {
        textLOG.append(texto);
        textLOG.setCaretPosition(textLOG.getDocument().getLength());
        repaint();
        revalidate();
    }

}
