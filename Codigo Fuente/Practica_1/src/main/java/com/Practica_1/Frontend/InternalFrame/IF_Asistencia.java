package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcAsistencia;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Asistencia extends IF_Padre {

    private JTextField txf1, txf2;
    private ProcAsistencia pa;

    public IF_Asistencia(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Registrar Asistencia", 400, 225);
        initComponentes();
        pa = new ProcAsistencia(frame);
    }

    private void initComponentes() {

        JLabel lbl1 = new JLabel("Ingrese el correo electrónico del participante");
        JLabel lbl2 = new JLabel("Ingrese el código de Actividad");

        JButton btn1 = new JButton("Guardar Asistencia");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));

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

            pa.guardarAsistencia(this, txf1.getText().trim(), txf2.getText().trim());

        } catch (ErrProcException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            textLOG.append(" -> Error al guardar la asistencia.\n\n");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        txf2.setText("");
    }

    public void invisible() {
        hacerInvisible(" -> Asistencia registrada exitosamente.\n\n");
    }

}
