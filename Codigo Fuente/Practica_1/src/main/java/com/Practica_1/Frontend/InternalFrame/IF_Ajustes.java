package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import com.Practica_1.Frontend.Frame_principal;

public class IF_Ajustes extends JInternalFrame {

    private Frame_principal frame;
    private JTextField txf1, txf2, txf3;
    private JLabel lbl1, lbl2, lbl3;
    private JLabel lblf1, lblf2, lblf3;

    public IF_Ajustes(Frame_principal frame) {
        super("Ajustes", false, true, false, false);
        this.frame = frame;

        setSize(new Dimension(400, 400));
        setLayout(new GridLayout(4, 1, 0, 5));

        frame.getDesktop().add(this);

        initComponentes();

    }

    private void initComponentes() {

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel pnl1 = new JPanel(new FlowLayout());
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel(new FlowLayout());
        JPanel pnl4 = new JPanel();

        lblf1 = new JLabel("Dirección de archivo de entrada");
        lblf2 = new JLabel("Velocidad de procesamiento (milisegundos)");
        lblf3 = new JLabel("Dirección de salida de archivos");
        lbl1 = new JLabel(" ");
        lbl2 = new JLabel(" ");
        lbl3 = new JLabel(" ");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(250, 25));
        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));
        txf3 = new JTextField();
        txf3.setPreferredSize(new Dimension(250, 25));

        JButton btn1 = new JButton("Seleccionar");
        JButton btn2 = new JButton("Seleccionar");
        JButton btn3 = new JButton("Guardar");

        add(pnl1);
        add(pnl2);
        add(pnl3);
        add(pnl4);

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1);
        pnl1.add(btn1);
        pnl1.add(lbl1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(txf2);
        pnl2.add(lbl2, BorderLayout.SOUTH);

        pnl3.add(lblf3, BorderLayout.NORTH);
        pnl3.add(txf3);
        pnl3.add(btn2);
        pnl3.add(lbl3, BorderLayout.SOUTH);

        pnl4.add(btn3);

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
        txf2.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                lbl2.setText(" ");
            }

            @Override
            public void focusLost(FocusEvent e) {
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
                lbl1.setText(" ");
                txf1.setText(fileChooser.getSelectedFile().getAbsolutePath());
            } else {
                lbl3.setText(" ");
                txf3.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(frame,
                    "No se selecciono ningun archivo.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void btnGuardarActionPerformer() {

        File file = new File(txf1.getText());
        if (file.exists()) {
            if (!file.isDirectory()) {
                frame.setPathEntrada(txf1.getText());
                lbl1.setText("Archivo guardado exitosamente");
            } else {
                lbl1.setText("Archivo seleccionado no valido");
            }
        } else {
            lbl1.setText("Archivo seleccionado no existe");
        }

        try {
            if (Integer.parseInt(txf2.getText()) > 0) {
                frame.setTiempoProcesado(Integer.parseInt(txf2.getText()));
                lbl2.setText("Velocidad guardada exitosamente");
            } else {
                lbl2.setText("Ingrese un numero mayor a 0");
            }
        } catch (NumberFormatException e) {
            lbl2.setText("Velocidad ingresada no valida");
        }

        file = new File(txf3.getText());
        if (file.exists()) {
            if (file.isDirectory()) {
                frame.setPathSalida(txf3.getText());
                lbl3.setText("Direccion guardada exitosamente");
            } else {
                lbl3.setText("Direccion ingresada no valida");
            }
        } else {
            lbl3.setText("Direccion seleccionada no existe");
        }

        setVisible(false);
    }

    public void hacerVisible() {

        setLocation((frame.getWidth() - 400) / 2, (frame.getHeight() - 400) / 2);
        setVisible(true);
        txf1.setText("");
        txf2.setText("");
        txf3.setText("");

    }

}
