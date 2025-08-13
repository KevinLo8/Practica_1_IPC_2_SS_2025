package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Listener.FocLisDinero;
import com.Practica_1.Backend.Procesador.ProcEvento;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Evento extends IF_Padre {

    private Frame_principal frame;
    private JTextField txf1, txf2, txf4, txf5, txf6, txf7;
    private JComboBox<String> jCB3;
    private JLabel lblf1, lblf2, lblf3, lblf4, lblf5, lblf6, lblf7;
    private ProcEvento pe;

    public IF_Evento(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Registrar Evento", 400, 575, 8);
        initComponentes();
        pe = new ProcEvento(frame);
    }

    private void initComponentes() {

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();
        JPanel pnl4 = new JPanel();
        JPanel pnl5 = new JPanel();
        JPanel pnl6 = new JPanel();
        JPanel pnl7 = new JPanel();
        JPanel pnl8 = new JPanel();

        lblf1 = new JLabel("Ingrese el código del evento");
        lblf2 = new JLabel("Ingrese la fecha del evento");
        lblf3 = new JLabel("Seleccione el tipo del evento");
        lblf4 = new JLabel("Ingrese el título del evento");
        lblf5 = new JLabel("Ingrese la ubicación del evento");
        lblf6 = new JLabel("Ingrese el cupo máximo del evento");
        lblf7 = new JLabel("Ingrese el costo de inscripción");

        JButton btn1 = new JButton("Guardar Evento");

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

        jCB3 = new JComboBox<>();
        jCB3.setPreferredSize(new Dimension(300, 25));
        jCB3.setBackground(Color.WHITE);

        jCB3.addItem("CHARLA");
        jCB3.addItem("CONGRESO");
        jCB3.addItem("TALLER");
        jCB3.addItem("DEBATE");

        add(pnl1);
        add(pnl2);
        add(pnl3);
        add(pnl4);
        add(pnl5);
        add(pnl6);
        add(pnl7);
        add(pnl8);

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(txf2, BorderLayout.SOUTH);

        pnl3.add(lblf3, BorderLayout.NORTH);
        pnl3.add(jCB3, BorderLayout.SOUTH);

        pnl4.add(lblf4, BorderLayout.NORTH);
        pnl4.add(txf4, BorderLayout.SOUTH);

        pnl5.add(lblf5, BorderLayout.NORTH);
        pnl5.add(txf5, BorderLayout.SOUTH);

        pnl6.add(lblf6, BorderLayout.NORTH);
        pnl6.add(txf6, BorderLayout.SOUTH);

        pnl7.add(lblf7, BorderLayout.NORTH);
        pnl7.add(txf7, BorderLayout.SOUTH);

        pnl8.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuardarActionPerformer();
            }

        });
        txf7.addFocusListener(new FocLisDinero(txf1));

    }

    private void btnGuardarActionPerformer() {

        try {
            String tipoString = "";

            if (jCB3.getSelectedIndex() != -1) {
                tipoString = jCB3.getSelectedItem().toString();
            }

            pe.guardarAsistencia(this, txf1.getText(), txf2.getText(), tipoString, txf4.getText(), txf5.getText(),
                    txf6.getText(), txf7.getText());

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
        txf4.setText("");
        txf5.setText("");
        txf6.setText("0");
        txf7.setText("0.00");
    }

    public void invisible(){
        hacerInvisible(" -> Reporte de participantes creado exitosamente.\n\n");
    }

}
