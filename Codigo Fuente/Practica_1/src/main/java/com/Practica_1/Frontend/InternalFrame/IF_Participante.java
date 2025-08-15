package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcParticipante;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Participante extends IF_Padre {

    private JTextField txf1, txf3, txf4;
    private JComboBox<String> jCB2;
    private ProcParticipante pp;

    public IF_Participante(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Registrar Participante Nuevo", 400, 370);
        initComponentes();
        pp = new ProcParticipante(frame);
    }

    private void initComponentes() {

        JLabel lbl1 = new JLabel("Ingrese el nombre completo del participante");
        JLabel lbl2 = new JLabel("Seleccione el tipo de participante");
        JLabel lbl3 = new JLabel("Ingrese la institución del participante");
        JLabel lbl4 = new JLabel("Ingrese el correo electrónico del participante");

        JButton btn1 = new JButton("Guardar Participante");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf3 = new JTextField();
        txf3.setPreferredSize(new Dimension(300, 25));
        txf4 = new JTextField();
        txf4.setPreferredSize(new Dimension(300, 25));

        jCB2 = new JComboBox<>();
        jCB2.setPreferredSize(new Dimension(300, 25));
        jCB2.setBackground(Color.WHITE);

        jCB2.addItem("ESTUDIANTE");
        jCB2.addItem("PROFESIONAL");
        jCB2.addItem("INVITADO");

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
                                .addComponent(lbl4)
                                .addComponent(txf4)
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
                        .addComponent(lbl4)
                        .addGap(5)
                        .addComponent(txf4)
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

            if (jCB2.getSelectedIndex() != -1) {
                tipoString = jCB2.getSelectedItem().toString();
            }

            pp.guardarAsistencia(this, txf1.getText(), tipoString, txf3.getText(), txf4.getText());

        } catch (ErrProcException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            textLOG.append(" -> Error al guardar el participante.\n\n");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        jCB2.setSelectedIndex(-1);
        txf3.setText("");
        txf4.setText("");
    }

    public void invisible() {
        hacerInvisible(" -> Participante registrado exitosamente.\n\n");
    }

}
