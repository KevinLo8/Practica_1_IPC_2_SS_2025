package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcCertificado;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Certificado extends IF_Padre {

    private JTextField txf1, txf2;
    private ProcCertificado pc;

    public IF_Certificado(Frame_principal frame) {
        super(frame, "Crear Certificado", 400, 225);
        initComponentes();
        pc = new ProcCertificado(frame);
    }

    private void initComponentes() {

        JLabel lbl1 = new JLabel("Ingrese el correo electrónico del participante");
        JLabel lbl2 = new JLabel("Ingrese el código del evento");

        JButton btn1 = new JButton("Crear Certificado");

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
                btnCrearActionPerformer();
            }

        });

    }

    private void btnCrearActionPerformer() {

        try {
            pc.crearCertificado(this, txf1.getText(), txf2.getText());
        } catch (ErrProcException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            frame.appendTextLog("\n\n -> Error al crear el certificado.");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        txf2.setText("");
    }

    public void invisible() {
        hacerInvisible("\n\n -> Certificado creado exitosamente.");
    }

}
