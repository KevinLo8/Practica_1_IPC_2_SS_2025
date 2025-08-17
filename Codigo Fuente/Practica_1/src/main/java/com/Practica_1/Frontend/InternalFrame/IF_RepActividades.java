package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcRepActividades;
import com.Practica_1.Frontend.Frame_principal;

public class IF_RepActividades extends IF_Padre {

    private JTextField txf1, txf3;
    private JComboBox<String> jCB2;
    private ProcRepActividades pra;

    public IF_RepActividades(Frame_principal frame) {
        super(frame, "Crear Reporte De Actividades", 400, 290);
        initComponentes();
        pra = new ProcRepActividades(frame);
    }

    private void initComponentes() {

        JLabel lbl1 = new JLabel("Ingrese el código del evento");
        JLabel lbl2 = new JLabel("Seleccione el tipo de actividad (Opcional)");
        JLabel lbl3 = new JLabel("Ingrese el correo del encargado (Opcional)");

        JButton btn1 = new JButton("Crear Reporte");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf3 = new JTextField();
        txf3.setPreferredSize(new Dimension(300, 25));

        jCB2 = new JComboBox<>();
        jCB2.setPreferredSize(new Dimension(300, 25));
        jCB2.setBackground(Color.WHITE);

        jCB2.addItem("CHARLA");
        jCB2.addItem("TALLER");
        jCB2.addItem("DEBATE");
        jCB2.addItem("OTRA");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lbl1)
                                .addComponent(txf1)
                                .addComponent(lbl2)
                                .addComponent(jCB2)
                                .addComponent(lbl3)
                                .addComponent(txf3)
                                .addComponent(btn1))
                        .addContainerGap(20, Short.MAX_VALUE));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(lbl1)
                        .addGap(5)
                        .addComponent(txf1)
                        .addGap(20)
                        .addComponent(lbl2)
                        .addGap(5)
                        .addComponent(jCB2)
                        .addGap(20)
                        .addComponent(lbl3)
                        .addGap(5)
                        .addComponent(txf3)
                        .addGap(20)
                        .addComponent(btn1)
                        .addContainerGap(20, Short.MAX_VALUE));

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
            if (jCB2.getSelectedIndex() == -1) {
                tipo = "";
            } else {
                tipo = jCB2.getSelectedItem().toString();
            }

            pra.crearReporte(this, txf1.getText().trim(), tipo, txf3.getText().trim());

        } catch (ErrProcException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            frame.appendTextLog("\n\n -> Error al crear el reporte de actividades.");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        jCB2.setSelectedIndex(-1);
        txf3.setText("");
    }

    public void invisible() {
        hacerInvisible("\n\n -> Reporte de actividades creado exitosamente.");
    }
}
