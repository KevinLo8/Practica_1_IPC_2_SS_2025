package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Listener.FocLisDinero;
import com.Practica_1.Backend.Procesador.ProcPago;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Pago extends IF_Padre {

    private JTextField txf1, txf2, txf4;
    private JComboBox<String> jCB3;
    private ProcPago pp;

    public IF_Pago(Frame_principal frame) {
        super(frame, "Registrar Pago Participante", 400, 360);
        initComponentes();
        pp = new ProcPago(frame);
    }

    private void initComponentes() {

        JLabel lbl1 = new JLabel("Ingrese el correo electrónico del participante");
        JLabel lbl2 = new JLabel("Ingrese el codigo de evento");
        JLabel lbl3 = new JLabel("Seleccione el tipo de pago");
        JLabel lbl4 = new JLabel("Ingrese el monto del pago");

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
                        .addComponent(txf2)
                        .addGap(20)
                        .addComponent(lbl3)
                        .addGap(5)
                        .addComponent(jCB3)
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
            frame.appendTextLog("\n\n -> Error al guardar el pago.");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        txf2.setText("");
        jCB3.setSelectedIndex(-1);
        txf4.setText("0.00");
    }

    public void invisible() {
        hacerInvisible("\n\n -> Pago registrado exitosamente.");
    }

}
