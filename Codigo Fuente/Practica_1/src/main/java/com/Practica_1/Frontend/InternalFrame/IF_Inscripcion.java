package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcInscripcion;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Inscripcion extends IF_Padre {

    private Frame_principal frame;
    private JTextField txf1, txf2;
    private JComboBox<String> jCB3;
    private JLabel lblf1, lblf2, lblf3;
    private ProcInscripcion pi;

    public IF_Inscripcion(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Inscribir Participante En Evento", 400, 350, 4);
        initComponentes();
        pi = new ProcInscripcion(frame);
    }

    private void initComponentes() {

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();
        JPanel pnl4 = new JPanel();

        lblf1 = new JLabel("Ingrese el correo del participante");
        lblf2 = new JLabel("Ingrese el código del evento");
        lblf3 = new JLabel("Seleccione el tipo de inscripción");

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

        add(pnl1);
        add(pnl2);
        add(pnl3);
        add(pnl4);

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(txf2, BorderLayout.SOUTH);

        pnl3.add(lblf3, BorderLayout.NORTH);
        pnl3.add(jCB3, BorderLayout.SOUTH);

        pnl4.add(btn1);

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
            textLOG.append(" -> Error al guardar el evento.\n\n");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        txf2.setText("");
        jCB3.setSelectedIndex(-1);
    }

    public void invisible(){
        hacerInvisible(" -> Inscripción registrada exitosamente.\n\n");
    }

}
