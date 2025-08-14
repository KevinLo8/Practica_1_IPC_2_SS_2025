package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcCertificado;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Certificado extends IF_Padre {

    private Frame_principal frame;
    private JTextField txf1, txf2;
    private JLabel lblf1, lblf2;
    private ProcCertificado pc;

    public IF_Certificado(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Crear Certificado", 400, 250, 3);
        initComponentes();
        pc = new ProcCertificado(frame);
    }

    private void initComponentes() {

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();

        lblf1 = new JLabel("Ingrese el correo electrónico del participante");
        lblf2 = new JLabel("Ingrese el código del evento");

        JButton btn1 = new JButton("Crear Certificado");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));

        add(pnl1);
        add(pnl2);
        add(pnl3);

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(txf2, BorderLayout.SOUTH);

        pnl3.add(btn1);

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
            textLOG.append(" -> Error al crear el certificado.\n\n");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        txf2.setText("");
    }

    public void invisible(){
        hacerInvisible(" -> Certificado creado exitosamente.\n\n");
    }

}
