package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcInscripcion;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Inscripcion extends IF_Padre {

    private JTextField txf1, txf2;
    private JComboBox<String> jCB3;
    private ProcInscripcion pi;

    public IF_Inscripcion(Frame_principal frame) {
        super(frame, "Inscribir Participante En Evento", 400, 290);
        initComponentes();
        pi = new ProcInscripcion(frame);
    }

    private void initComponentes() {

        JLabel lbl1 = new JLabel("Ingrese el correo del participante");
        JLabel lbl2 = new JLabel("Ingrese el código del evento");
        JLabel lbl3 = new JLabel("Seleccione el tipo de inscripción");

        JButton btn1 = new JButton("Guardar Inscripción");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));

        jCB3 = new JComboBox<>();
        jCB3.setPreferredSize(new Dimension(300, 25));
        jCB3.setBackground(Color.WHITE);

        jCB3.addItem("ASISTENTE");
        jCB3.addItem("CONFERENCISTA");
        jCB3.addItem("TALLERISTA");
        jCB3.addItem("OTRO");

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

            pi.guardarAsistencia(this, txf1.getText(), txf2.getText(), tipoString);

        } catch (ErrProcException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            frame.appendTextLog("\n\n -> Error al guardar la inscripción.");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        txf2.setText("");
        jCB3.setSelectedIndex(-1);
    }

    public void invisible() {
        hacerInvisible("\n\n -> Inscripción registrada exitosamente.");
    }

}
