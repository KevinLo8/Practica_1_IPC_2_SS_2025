package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Listener.FocLisDinero;
import com.Practica_1.Backend.Procesador.ProcPago;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Pago extends IF_Padre {

    private Frame_principal frame;
    private JTextField txf1, txf2, txf4;
    private JComboBox<String> jCB3;
    private JLabel lblf1, lblf2, lblf3, lblf4;
    private ProcPago pp;

    public IF_Pago(Frame_principal frame, JTextArea textLOG) {
        super(frame, textLOG, "Registrar Pago Participante", 400, 400, 5);
        initComponentes();
        pp = new ProcPago(frame);
    }

    private void initComponentes() {

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();
        JPanel pnl4 = new JPanel();
        JPanel pnl5 = new JPanel();

        lblf1 = new JLabel("Ingrese el correo electrónico del participante");
        lblf2 = new JLabel("Ingrese el codigo de evento");
        lblf3 = new JLabel("Seleccione el tipo de pago");
        lblf4 = new JLabel("Ingrese el monto del pago");

        JButton btn1 = new JButton("Guardar Pago");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));
        txf4 = new JTextField();
        txf4.setPreferredSize(new Dimension(300, 25));

        jCB3 = new JComboBox<>();
        jCB3.setPreferredSize(new Dimension(300, 25));
        jCB3.setBackground(Color.WHITE);

        jCB3.addItem("EFECTIVO");
        jCB3.addItem("TRANSFERENCIA");
        jCB3.addItem("TARJETA");

        add(pnl1);
        add(pnl2);
        add(pnl3);
        add(pnl4);
        add(pnl5);

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(txf2, BorderLayout.SOUTH);

        pnl3.add(lblf3, BorderLayout.NORTH);
        pnl3.add(jCB3, BorderLayout.SOUTH);

        pnl4.add(lblf4, BorderLayout.NORTH);
        pnl4.add(txf4, BorderLayout.SOUTH);

        pnl5.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnGuardarActionPerformer();
            }

        });
        txf4.addFocusListener(new FocLisDinero(txf4));

    }

    private void btnGuardarActionPerformer() {

        try {
            String tipoString = "";

            if (jCB3.getSelectedIndex() != -1) {
                tipoString = jCB3.getSelectedItem().toString();
            }

            pp.guardarPago(this, txf1.getText(), txf2.getText(), tipoString, txf4.getText());

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
    }

    public void invisible(){
        hacerInvisible(" -> Inscripción registrada exitosamente.\n\n");
    }

}
