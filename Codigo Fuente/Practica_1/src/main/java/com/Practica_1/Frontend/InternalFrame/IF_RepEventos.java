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
    private ProcRepEventos pre;

    public IF_RepEventos(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Crear Reporte De Eventos", 400, 450, 6);

        pre = new ProcRepEventos(frame);

        initComponentes();

    }

    private void initComponentes() {

        JButton btn1 = new JButton("Crear Reporte");

        JLabel lbl1 = new JLabel("Seleccione el tipo de evento");
        JLabel lbl2 = new JLabel("RANGO DE FECHA");
        JLabel lbl3 = new JLabel("Ingrese la fecha inicial");
        JLabel lbl4 = new JLabel("Ingrese la fecha final");
        JLabel lbl5 = new JLabel("RANGO DE CUPO");
        JLabel lbl6 = new JLabel("Ingrese el cupo minimo");
        JLabel lbl7 = new JLabel("Ingrese el cupo maximo");

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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lbl1)
                                .addComponent(jCB1)
                                .addComponent(lbl2)
                                .addComponent(lbl3)
                                .addComponent(txf2)
                                .addComponent(lbl4)
                                .addComponent(txf3)
                                .addComponent(lbl5)
                                .addComponent(lbl6)
                                .addComponent(txf4)
                                .addComponent(lbl7)
                                .addComponent(txf5)
                                .addComponent(btn1))
                        .addContainerGap(20, Short.MAX_VALUE));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(lbl1)
                        .addGap(5)
                        .addComponent(jCB1)
                        .addGap(20)
                        .addComponent(lbl2)
                        .addGap(5)
                        .addComponent(lbl3)
                        .addGap(5)
                        .addComponent(txf2)
                        .addGap(5)
                        .addComponent(lbl4)
                        .addGap(5)
                        .addComponent(txf3)
                        .addGap(20)
                        .addComponent(lbl5)
                        .addGap(5)
                        .addComponent(lbl6)
                        .addGap(5)
                        .addComponent(txf4)
                        .addGap(5)
                        .addComponent(lbl7)
                        .addGap(5)
                        .addComponent(txf5)
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
            if (jCB1.getSelectedIndex() == -1) {
                tipo = "";
            } else {
                tipo = jCB1.getSelectedItem().toString();
            }

            pre.crearReporte(this, tipo, txf2.getText().trim(), txf3.getText().trim(), txf4.getText().trim(),
                    txf5.getText().trim());

        } catch (ErrProcException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            textLOG.append(" -> Error al crear el reporte de eventos.\n\n");
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

    public void invisible() {
        hacerInvisible(" -> Reporte de eventos creado exitosamente.\n\n");
    }
}
