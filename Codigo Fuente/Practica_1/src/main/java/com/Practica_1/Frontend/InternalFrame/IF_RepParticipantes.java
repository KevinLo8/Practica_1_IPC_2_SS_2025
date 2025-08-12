package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcRepParticipantes;
import com.Practica_1.Frontend.Frame_principal;

public class IF_RepParticipantes extends IF_Padre {

    private JTextField txf1, txf3;
    private JComboBox<String> jCB2;
    private JLabel lblf1, lblf2, lblf3;
    private ProcRepParticipantes prp;

    public IF_RepParticipantes(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Crear Reporte De Participantes", 400, 425, 4);

        prp = new ProcRepParticipantes(frame);

        initComponentes();

    }

    private void initComponentes() {

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();
        JPanel pnl4 = new JPanel();

        lblf1 = new JLabel("Ingrese el código del evento");
        lblf2 = new JLabel("Seleccione el tipo de participante (Opcional)");
        lblf3 = new JLabel("Ingrese la institución del participante (Opcional)");

        JButton btn1 = new JButton("Crear Reporte");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf3 = new JTextField();
        txf3.setPreferredSize(new Dimension(300, 25));

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

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(jCB2, BorderLayout.SOUTH);

        pnl3.add(lblf3, BorderLayout.NORTH);
        pnl3.add(txf3, BorderLayout.SOUTH);

        pnl4.add(btn1);

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
            
            prp.crearReporte(this, txf1.getText().trim(), tipo, txf3.getText().trim());

        } catch (ErrProcException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            textLOG.append(" -> Error al crear el reporte de participantes\n\n");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        jCB2.setSelectedIndex(-1);
        txf3.setText("");
    }

    public void invisible(){
        hacerInvisible(" -> Reporte de participantes creado exitosamente.\n\n");
    }
}
