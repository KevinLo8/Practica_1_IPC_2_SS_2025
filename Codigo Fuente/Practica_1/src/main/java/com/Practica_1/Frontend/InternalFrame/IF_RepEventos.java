package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcRepEventos;
import com.Practica_1.Frontend.Frame_principal;

public class IF_RepEventos extends IF_Padre {

    private JTextField txf2, txf3, txf4, txf5;
    private JComboBox<String> jCB1;
    private JLabel lblf1, lblf2, lblf3, lblf4, lblf5, lblf6, lblf7;
    private ProcRepEventos pre;

    public IF_RepEventos(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Crear Reporte De Eventos", 400, 425, 6);

        pre = new ProcRepEventos(frame);

        initComponentes();

    }

    private void initComponentes() {

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();
        JPanel pnl4 = new JPanel();
        JPanel pnl5 = new JPanel();
        JPanel pnl6 = new JPanel();

        lblf1 = new JLabel("Seleccione el tipo de evento");
        lblf2 = new JLabel("RANGO DE FECHA");
        lblf3 = new JLabel("Ingrese la fecha inicial");
        lblf4 = new JLabel("Ingrese la fecha final");
        lblf5 = new JLabel("RANGO DE CUPO");
        lblf6 = new JLabel("Ingrese el cupo minimo");
        lblf7 = new JLabel("Ingrese el cupo maximo");

        JButton btn1 = new JButton("Crear Reporte");

        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));
        txf3 = new JTextField();
        txf3.setPreferredSize(new Dimension(300, 25));
        txf4 = new JTextField();
        txf4.setPreferredSize(new Dimension(300, 25));
        txf5 = new JTextField();
        txf5.setPreferredSize(new Dimension(300, 25));

        jCB1 = new JComboBox<>();
        jCB1.setPreferredSize(new Dimension(300, 25));
        jCB1.setBackground(Color.WHITE);

        jCB1.addItem("CHARLA");
        jCB1.addItem("CONGRESO");
        jCB1.addItem("TALLER");
        jCB1.addItem("DEBATE");

        add(pnl1);
        add(pnl2);
        add(pnl3);
        add(pnl4);
        add(pnl4);
        add(pnl5);
        add(pnl6);

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(jCB1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(lblf3, BorderLayout.CENTER);
        pnl2.add(txf2, BorderLayout.SOUTH);

        pnl3.add(lblf4, BorderLayout.NORTH);
        pnl3.add(txf3, BorderLayout.SOUTH);

        pnl4.add(lblf5, BorderLayout.NORTH);
        pnl4.add(lblf6, BorderLayout.CENTER);
        pnl4.add(txf4, BorderLayout.SOUTH);

        pnl5.add(lblf7, BorderLayout.NORTH);
        pnl5.add(txf5, BorderLayout.SOUTH);

        pnl6.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnCrearActionPerformer();
            }

        });

    }

    private void btnCrearActionPerformer() {

        try {

            String tipo;
            if (jCB1.getSelectedIndex() == -1) {
                tipo = "";
            } else {
                tipo = jCB1.getSelectedItem().toString();
            }
            
            pre.crearReporte(this, tipo, txf2.getText().trim(), txf3.getText().trim(), txf4.getText().trim(), txf5.getText().trim());

        } catch (ErrProcException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            textLOG.append(" -> Error al crear el reporte de eventos\n\n");
        }

    }

    public void visible() {
        hacerVisible();
        jCB1.setSelectedIndex(-1);
        txf2.setText("");
        txf3.setText("");
        txf4.setText("");
        txf5.setText("");
    }

    public void invisible(){
        hacerInvisible(" -> Reporte de eventos creado exitosamente.\n\n");
    }
}
