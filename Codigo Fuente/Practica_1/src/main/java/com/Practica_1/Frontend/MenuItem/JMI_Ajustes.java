package com.Practica_1.Frontend.MenuItem;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import com.Practica_1.Backend.Datos.Data_Config;
import com.Practica_1.Frontend.Frame_principal;

public class JMI_Ajustes extends JMenuItem {

    private JTextField txf1, txf2, txf3;
    private JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
    private Data_Config config;
    private Frame_principal frame;
    
    public JMI_Ajustes(Frame_principal frame) {
        super("Ajustes");

        this.frame = frame;

        config = new Data_Config();

        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

               btnAjustesActionPerformer(); 
                        
            }

        });

    }  
    
    private void btnAjustesActionPerformer(){
 
        JInternalFrame iFrame = new JInternalFrame("Ajustes", false, true, false, false);

        JPanel pnl1 = new JPanel(new FlowLayout());
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel(new FlowLayout());
        JPanel pnl4 = new JPanel();

        lbl1 = new JLabel("Dirección de archivo de entrada");
        lbl2 = new JLabel("Velocidad de procesamiento (milisegundos)");
        lbl3 = new JLabel("Dirección de salida de archivos");
        lbl4 = new JLabel(" ");
        lbl5 = new JLabel(" ");
        lbl6 = new JLabel(" ");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(250, 25));
        txf1.setText(config.getArchivoEntrada());
        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));
        txf2.setText(String.valueOf(config.getVelocidadProcesamiento()));
        txf3 = new JTextField();
        txf3.setPreferredSize(new Dimension(250, 25));
        txf3.setText(config.getDirecciónSalida());

        JButton btn1 = new JButton("Seleccionar");
        JButton btn2 = new JButton("Seleccionar");
        JButton btn3 = new JButton("Guardar");

        iFrame.setVisible(true);
        iFrame.setBounds((frame.getDesktop().getWidth() - 400) / 2, (frame.getDesktop().getHeight() - 400) / 2, 400, 400);
        iFrame.setLayout(new GridLayout(4, 1, 0, 5));
        iFrame.setResizable(false);

        frame.getDesktop().add(iFrame);
           
        iFrame.add(pnl1);
        iFrame.add(pnl2);
        iFrame.add(pnl3);
        iFrame.add(pnl4);
        
        pnl1.add(lbl1,BorderLayout.NORTH);
        pnl1.add(txf1);
        pnl1.add(btn1);
        pnl1.add(lbl4,BorderLayout.SOUTH);

        pnl2.add(lbl2,BorderLayout.NORTH);
        pnl2.add(txf2);
        pnl2.add(lbl5,BorderLayout.SOUTH);

        pnl3.add(lbl3,BorderLayout.NORTH);
        pnl3.add(txf3);
        pnl3.add(btn2);
        pnl3.add(lbl6,BorderLayout.SOUTH);

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
                lbl5.setText(" ");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
            
        });

    }

    private void btnSelecionarActionPerformer(int i){

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
                lbl4.setText(" ");
                config.setArchivoEntrada(fileChooser.getSelectedFile().getAbsolutePath());
                txf1.setText(config.getArchivoEntrada());
            } else {
                lbl6.setText(" ");
                config.setDirecciónSalida(fileChooser.getSelectedFile().getAbsolutePath());
                txf3.setText(config.getDirecciónSalida());
            }
        } catch (NullPointerException e) {
            
        }
    }

    private void btnGuardarActionPerformer(){

        File file = new File(txf1.getText());
        if (file.exists()) {
            if (!file.isDirectory()) {
                lbl4.setText("Archivo guardado exitosamente");
            } else {
                lbl4.setText("Archivo seleccionado no valido");
            }
        } else {
            lbl4.setText("Archivo seleccionado no existe");
        }

        try {
            if (Integer.parseInt(txf2.getText()) > 0) {
                lbl5.setText("Velocidad guardada exitosamente");    
            } else {
                lbl5.setText("Ingrese un numero mayor a 0");    
            }
        } catch (NumberFormatException e) {
            lbl5.setText("Velocidad ingresada no valida");
        }

        file = new File(txf3.getText());
        if (file.exists()) {
            if (file.isDirectory()) {
                lbl6.setText("Direccion guardada exitosamente");
            } else {
                lbl6.setText("Direccion ingresada no valida");
            }
        } else {
            lbl6.setText("Direccion seleccionada no existe");
        }

    }
    
}
