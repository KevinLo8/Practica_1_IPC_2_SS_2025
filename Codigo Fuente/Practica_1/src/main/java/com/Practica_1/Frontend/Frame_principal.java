package com.Practica_1.Frontend;

import javax.swing.*;

import com.Practica_1.Backend.Conexión_DB.Conexión_DB;
import com.Practica_1.Frontend.MenuItem.*;

import java.awt.*;

public class Frame_principal extends JFrame {

    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    private JDesktopPane desktop;
    private JTextArea textLOG;

    private String pathEntrada;
    private String pathSalida;
    private int tiempoProcesado;

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

        textLOG = new JTextArea("\n -> Aplicación Inicializada.\n\n");
        textLOG.setEditable(false);
        textLOG.setBackground(Color.BLACK);
        textLOG.setForeground(Color.WHITE);

        jMenuBar.add(jM1);
        jMenuBar.add(jM2);
        jMenuBar.add(jM3);

        jM1.add(new JMI_Ajustes(this));
        jM1.add(new JMI_Salir());

        jM2.add(new JMI_Evento(this, textLOG));
        jM2.add(new JMI_Participante(this, textLOG));
        jM2.add(new JMI_Inscripcion(this, textLOG));
        jM2.add(new JMI_Pago(this, textLOG));
        jM2.add(new JMI_Validacion(this, textLOG));
        jM2.add(new JMI_Actividad(this, textLOG));
        jM2.add(new JMI_Asistencia(this, textLOG));
        jM2.add(new JMI_Certificado(this));

        jM3.add(new JMI_RepParticipantes(this, textLOG));
        jM3.add(new JMI_RepActividades(this, textLOG));
        jM3.add(new JMI_RepEventos(this, textLOG));

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

    public String getPathEntrada() {
        return pathEntrada;
    }

    public void setPathEntrada(String pathEntrada) {
        this.pathEntrada = pathEntrada;
    }

    public String getPathSalida() {
        return pathSalida;
    }

    public void setPathSalida(String pathSalida) {
        this.pathSalida = pathSalida;
    }

    public int getTiempoProcesado() {
        return tiempoProcesado;
    }

    public void setTiempoProcesado(int tiempoProcesado) {
        this.tiempoProcesado = tiempoProcesado;
    }

}
