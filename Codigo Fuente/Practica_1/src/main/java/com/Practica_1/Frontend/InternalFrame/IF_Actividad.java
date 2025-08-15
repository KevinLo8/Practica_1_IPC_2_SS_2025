package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcActividad;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Actividad extends IF_Padre {

    private JTextField txf1, txf2, txf4, txf5, txf6, txf7, txf8;
    private JComboBox<String> jCB3;
    private ProcActividad pa;

    public IF_Actividad(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Registrar Actividad", 400, 630);
        initComponentes();
        pa = new ProcActividad(frame);
    }

    private void initComponentes() {

        JLabel lbl1 = new JLabel("Ingrese el código de actividad");
        JLabel lbl2 = new JLabel("Ingrese el código de evento");
        JLabel lbl3 = new JLabel("Seleccione el tipo de actividad");
        JLabel lbl4 = new JLabel("Ingrese el título de la actividad");
        JLabel lbl5 = new JLabel("Ingrese el correo electrónico del impartidor");
        JLabel lbl6 = new JLabel("Ingrese la hora de inicio (HH:MM)");
        JLabel lbl7 = new JLabel("Ingrese la hora de fin (HH:MM)");
        JLabel lbl8 = new JLabel("Ingrese el cupo máximo de participantes");

        jCB3 = new JComboBox<String>();
        jCB3.setPreferredSize(new Dimension(300, 25));
        jCB3.setBackground(Color.WHITE);

        jCB3.addItem("CHARLA");
        jCB3.addItem("TALLER");
        jCB3.addItem("DEBATE");
        jCB3.addItem("OTRA");

        JButton btn1 = new JButton("Registrar Actividad");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));
        txf4 = new JTextField();
        txf4.setPreferredSize(new Dimension(300, 25));
        txf5 = new JTextField();
        txf5.setPreferredSize(new Dimension(300, 25));
        txf6 = new JTextField();
        txf6.setPreferredSize(new Dimension(300, 25));
        txf7 = new JTextField();
        txf7.setPreferredSize(new Dimension(300, 25));
        txf8 = new JTextField();
        txf8.setPreferredSize(new Dimension(300, 25));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lbl1)
                                .addComponent(txf1)
                                .addComponent(lbl2)
                                .addComponent(txf2)
                                .addComponent(lbl3)
                                .addComponent(jCB3)
                                .addComponent(lbl4)
                                .addComponent(txf4)
                                .addComponent(lbl5)
                                .addComponent(txf5)
                                .addComponent(lbl6)
                                .addComponent(txf6)
                                .addComponent(lbl7)
                                .addComponent(txf7)
                                .addComponent(lbl8)
                                .addComponent(txf8)
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
                        .addComponent(txf2)
                        .addGap(20)
                        .addComponent(lbl3)
                        .addGap(5)
                        .addComponent(jCB3)
                        .addGap(20)
                        .addComponent(lbl4)
                        .addGap(5)
                        .addComponent(txf4)
                        .addGap(20)
                        .addComponent(lbl5)
                        .addGap(5)
                        .addComponent(txf5)
                        .addGap(20)
                        .addComponent(lbl6)
                        .addGap(5)
                        .addComponent(txf6)
                        .addGap(20)
                        .addComponent(lbl7)
                        .addGap(5)
                        .addComponent(txf7)
                        .addGap(20)
                        .addComponent(lbl8)
                        .addGap(5)
                        .addComponent(txf8)
                        .addGap(20)
                        .addComponent(btn1)
                        .addContainerGap(20, Short.MAX_VALUE));

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuardarActionPerformer();
            }

        });

    }

    private void btnGuardarActionPerformer() {

        try {
            String tipoString = "";

            if (jCB3.getSelectedIndex() != -1) {
                tipoString = jCB3.getSelectedItem().toString();
            }

            pa.guardarActividad(this, txf1.getText(), txf2.getText(), tipoString, txf4.getText(), txf5.getText(),
                    txf6.getText(), txf7.getText(), txf8.getText());

        } catch (ErrProcException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            textLOG.append(" -> Error al guardar la actividad.\n\n");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        txf2.setText("");
        jCB3.setSelectedIndex(-1);
        txf4.setText("");
        txf5.setText("");
        txf6.setText("");
        txf7.setText("");
        txf8.setText("0");
    }

    public void invisible() {
        hacerInvisible(" -> Actividad registrada exitosamente.\n\n");
    }

}
