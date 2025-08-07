package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;
import java.time.*;

import javax.swing.*;

import com.Practica_1.Backend.Datos.Data_Evento;
import com.Practica_1.Backend.Exception.SelecionTipoException;
import com.Practica_1.Backend.Listener.*;
import com.Practica_1.Frontend.Frame_principal;

public class IF_RegistrarEvento extends JInternalFrame {

    private Frame_principal frame;
    private JTextField txf1, txf2, txf4, txf5, txf6;
    private JComboBox<String> jCB3;
    private JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
    private JLabel lblf1, lblf2, lblf3, lblf4 , lblf5, lblf6;

    public IF_RegistrarEvento(Frame_principal frame) {
        super("Registrar Evento Nuevo", false, true, false, false);
        this.frame = frame;

        setSize(new Dimension(400, 575));
        setLayout(new GridLayout(7, 1, 0, 5));

        frame.getDesktop().add(this);

        initComponentes();
        
    }

    private void initComponentes(){

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();
        JPanel pnl4 = new JPanel();
        JPanel pnl5 = new JPanel();
        JPanel pnl6 = new JPanel();
        JPanel pnl7 = new JPanel();

        lblf1 = new JLabel("Ingrese el código del evento");
        lblf2 = new JLabel("Ingrese la fecha del evento");
        lblf3 = new JLabel("Seleccione el tipo del evento");
        lblf4 = new JLabel("Ingrese el título del evento");
        lblf5 = new JLabel("Ingrese la ubicación del evento");
        lblf6 = new JLabel("Ingrese el cupo máximo del evento");

        lbl1 = new JLabel(" ");
        lbl2 = new JLabel(" ");
        lbl3 = new JLabel(" ");
        lbl4 = new JLabel(" ");
        lbl5 = new JLabel(" ");
        lbl6 = new JLabel(" ");

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

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1);
        pnl1.add(lbl1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(txf2);
        pnl2.add(lbl2, BorderLayout.SOUTH);

        pnl3.add(lblf3, BorderLayout.NORTH);
        pnl3.add(jCB3);
        pnl3.add(lbl3, BorderLayout.SOUTH);

        pnl4.add(lblf4, BorderLayout.NORTH);
        pnl4.add(txf4);
        pnl4.add(lbl4, BorderLayout.SOUTH);

        pnl5.add(lblf5, BorderLayout.NORTH);
        pnl5.add(txf5);
        pnl5.add(lbl5, BorderLayout.SOUTH);

        pnl6.add(lblf6, BorderLayout.NORTH);
        pnl6.add(txf6);
        pnl6.add(lbl6, BorderLayout.SOUTH);

        pnl7.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnCrearActionPerformer();
            }
            
        });
        txf1.addFocusListener(new FocLisTexto(lbl1));
        txf2.addFocusListener(new FocLisTexto(lbl2));
        jCB3.addFocusListener(new FocLisTexto(lbl3));
        txf4.addFocusListener(new FocLisTexto(lbl4));
        txf5.addFocusListener(new FocLisTexto(lbl5));
        txf6.addFocusListener(new FocLisTexto(lbl6));

    }

    private void btnCrearActionPerformer(){

        Data_Evento data = new Data_Evento();
        int completo = 0;

        if (chequearCampo(txf1, 8)) {
            data.setCodigoEvento(txf1.getText());
            completo++;
        } else {
            lbl1.setText("Ingrese un código valido");
        }
        
        if (txf2.getText().matches("\\d{2}/\\d{2}/\\d{4}")) {
            try {
                String[] fechaParts = txf2.getText().split("/");
                LocalDate fecha;
                fecha = LocalDate.of(
                        Integer.parseInt(fechaParts[2]),
                        Integer.parseInt(fechaParts[1]),
                        Integer.parseInt(fechaParts[0])
                    );
                data.setFechaEvento(fecha);
                completo++;
            } catch (DateTimeException e) {
                lbl2.setText("Ingrese una fecha valida");
                e.printStackTrace();
            }
        } else {
            lbl2.setText("Ingrese una fecha valida");
        }

        if (jCB3.getSelectedIndex() != -1) {
            try {
                data.setTipoEvento(jCB3.getSelectedItem().toString());
                completo++;
            } catch (SelecionTipoException e) {
                lbl3.setText("Seleccione un tipo de evento valido");
            }
        } else {
            lbl3.setText("Seleccione un tipo de evento");
        }

        if (chequearCampo(txf4, 51)) {
            data.setTituloEvento(txf4.getText());
            completo++;
        } else {
            lbl4.setText("Ingrese un título valido");
        }

        if (chequearCampo(txf5, 151)) {
            data.setUbicacionEvento(txf5.getText());
            completo++;
        } else {
            lbl5.setText("Ingrese una ubicación valida");
        }

        if (txf6.getText().matches("\\d+")) {
            data.setCupoEvento(Integer.parseInt(txf6.getText()));
            completo++;
        } else {
            lbl6.setText("Ingrese un cupo máximo valido");
        }

        if (completo == 6) {

            frame.getConexion().guardarEvento(data);

            setVisible(false);
        }
    }

    private boolean chequearCampo(JTextField campo, int tamaño) {
        return (campo.getText().length() < tamaño && !campo.getText().isEmpty());
    }

    public void hacerVisible() {

        setLocation((frame.getWidth() - 400) / 2, (frame.getHeight() - 575) / 2);
        setVisible(true);
        txf1.setText("");
        txf2.setText("");
        jCB3.setSelectedIndex(-1);
        txf4.setText("");
        txf5.setText("");
        txf6.setText("");

    }

}
