package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcParticipante;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Participante extends IF_Padre {

    private Frame_principal frame;
    private JTextField txf1, txf3, txf4;
    private JComboBox<String> jCB2;
    private JLabel lblf1, lblf2, lblf3, lblf4;
    private ProcParticipante pp;

    public IF_Participante(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Registrar Participante Nuevo", 400, 425, 5);
        initComponentes();
        pp = new ProcParticipante(frame);
    }

    private void initComponentes() {

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();
        JPanel pnl4 = new JPanel();
        JPanel pnl5 = new JPanel();

        lblf1 = new JLabel("Ingrese el nombre completo del participante");
        lblf2 = new JLabel("Seleccione el tipo de participante");
        lblf3 = new JLabel("Ingrese la institución del participante");
        lblf4 = new JLabel("Ingrese el correo electrónico del participante");

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

        add(pnl1);
        add(pnl2);
        add(pnl3);
        add(pnl4);
        add(pnl5);

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(jCB2, BorderLayout.SOUTH);

        pnl3.add(lblf3, BorderLayout.NORTH);
        pnl3.add(txf3, BorderLayout.SOUTH);

        pnl4.add(lblf4, BorderLayout.NORTH);
        pnl4.add(txf4, BorderLayout.SOUTH);

        pnl5.add(btn1);

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
