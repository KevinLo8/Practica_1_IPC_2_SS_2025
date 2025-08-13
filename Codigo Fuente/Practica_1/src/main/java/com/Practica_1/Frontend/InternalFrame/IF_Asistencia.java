package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Listener.FocLisTexto;
import com.Practica_1.Backend.Procesador.ProcAsistencia;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Asistencia extends IF_Padre {

    private JTextField txf1, txf2;
    private JLabel lbl1, lbl2;
    private JLabel lblf1, lblf2;
    private ProcAsistencia pa;

    public IF_Asistencia(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Registrar Asistencia", 400, 250, 3);

        pa = new ProcAsistencia(frame);

        initComponentes();

    }

    private void initComponentes() {

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();

        lblf1 = new JLabel("Ingrese el correo electrónico del participante");
        lblf2 = new JLabel("Ingrese el código de Actividad");

        lbl1 = new JLabel(" ");
        lbl2 = new JLabel(" ");

        JButton btn1 = new JButton("Guardar Asistencia");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));

        add(pnl1);
        add(pnl2);
        add(pnl3);

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1);
        pnl1.add(lbl1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(txf2);
        pnl2.add(lbl2, BorderLayout.SOUTH);

        pnl3.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuardarActionPerformer();
            }

        });
        txf1.addFocusListener(new FocLisTexto(lbl1));
        txf2.addFocusListener(new FocLisTexto(lbl2));

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

    public void invisible(){
        hacerInvisible(" -> Asistencia registrada exitosamente.\n\n");
    }

}
