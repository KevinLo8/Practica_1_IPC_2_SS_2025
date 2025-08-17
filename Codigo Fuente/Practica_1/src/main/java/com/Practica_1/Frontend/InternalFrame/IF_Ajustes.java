package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Exception.ErrProcException;
import com.Practica_1.Backend.Procesador.ProcAjustes;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Ajustes extends IF_Padre {

    private JTextField txf1, txf2, txf3;
    private ProcAjustes pa;

    public IF_Ajustes(Frame_principal frame) {
        super(frame, "Ajustes", 400, 305);
        pa = new ProcAjustes(frame);
        initComponentes();
    }

    private void initComponentes() {

        JLabel lbl1 = new JLabel("Dirección de archivo de entrada");
        JLabel lbl2 = new JLabel("Velocidad de procesamiento (milisegundos)");
        JLabel lbl3 = new JLabel("Dirección de salida de archivos");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(250, 25));
        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));
        txf3 = new JTextField();
        txf3.setPreferredSize(new Dimension(250, 25));

        JButton btn1 = new JButton("Seleccionar");
        JButton btn2 = new JButton("Seleccionar");
        JButton btn3 = new JButton("Guardar");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(lbl1)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(txf1)
                                        .addComponent(btn1))
                                .addComponent(lbl2)
                                .addComponent(txf2)
                                .addComponent(lbl3)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(txf3)
                                        .addComponent(btn2))
                                .addComponent(btn3))
                        .addContainerGap(20, Short.MAX_VALUE));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(lbl1)
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(txf1)
                                .addComponent(btn1))
                        .addGap(20)
                        .addComponent(lbl2)
                        .addGap(5)
                        .addComponent(txf2)
                        .addGap(20)
                        .addComponent(lbl3)
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(txf3)
                                .addComponent(btn2))
                        .addGap(20)
                        .addComponent(btn3)
                        .addContainerGap(20, Short.MAX_VALUE));

        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSelecionarActionPerformer(1);
            }
        });
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSelecionarActionPerformer(2);
            }
        });
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGuardarActionPerformer();
            }
        });

    }

    private void btnSelecionarActionPerformer(int i) {

        JFileChooser fileChooser;
        fileChooser = new JFileChooser();

        if (i == 1) {
            fileChooser.setDialogTitle("Seleccione el archivo de entrada");
        } else {
            fileChooser.setDialogTitle("Seleccione la carpeta de salida");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }

        fileChooser.showOpenDialog(frame);

        try {
            if (i == 1) {
                txf1.setText(fileChooser.getSelectedFile().getAbsolutePath());
            } else {
                txf3.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(frame, "No se selecciono ningun archivo.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    private void btnGuardarActionPerformer() {

        try {

            pa.guardaAjustes(this, txf1.getText(), txf2.getText(), txf3.getText());

        } catch (ErrProcException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            frame.appendTextLog("\n\n -> Error al guardar la actividad.");
        }

    }

    public void visible() {
        hacerVisible();
        txf1.setText("");
        txf2.setText("0");
        txf3.setText("");
    }

    public void invisible(String mensaje) {
        hacerInvisible("\n\n -> " + mensaje);
    }

}
