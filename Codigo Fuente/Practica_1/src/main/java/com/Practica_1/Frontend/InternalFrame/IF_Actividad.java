package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Datos.*;
import com.Practica_1.Backend.Exception.SelecionTipoException;
import com.Practica_1.Backend.Listener.FocLisTexto;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Actividad extends JInternalFrame {

    private Frame_principal frame;
    private JTextField txf1, txf2, txf4, txf5, txf6, txf7, txf8;
    private JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
    private JComboBox<String> jCB3;
    private JLabel lblf1, lblf2, lblf3, lblf4, lblf5, lblf6, lblf7, lblf8;

    public IF_Actividad(Frame_principal frame) {
        super("Registrar Actividad", false, true, false, false);
        this.frame = frame;

        setSize(new Dimension(400, 725));
        setLayout(new GridLayout(9, 1, 0, 5));

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
        JPanel pnl8 = new JPanel();
        JPanel pnl9 = new JPanel();

        lblf1 = new JLabel("Ingrese el código de actividad");
        lblf2 = new JLabel("Ingrese el código de evento");
        lblf3 = new JLabel("Seleccione el tipo de actividad");
        lblf4 = new JLabel("Ingrese el título de la actividad");
        lblf5 = new JLabel("Ingrese el correo electrónico del impartidor");
        lblf6 = new JLabel("Ingrese la hora de inicio (HH:MM)");
        lblf7 = new JLabel("Ingrese la hora de fin (HH:MM)");
        lblf8 = new JLabel("Ingrese el cupo máximo de participantes");


        lbl1 = new JLabel(" ");
        lbl2 = new JLabel(" ");
        lbl3 = new JLabel(" ");
        lbl4 = new JLabel(" ");
        lbl5 = new JLabel(" ");
        lbl6 = new JLabel(" ");
        lbl7 = new JLabel(" ");
        lbl8 = new JLabel(" ");

        jCB3 = new JComboBox<String>();
        jCB3.setPreferredSize(new Dimension(300, 25));
        jCB3.setBackground(Color.WHITE);

        jCB3.addItem("CHARLA");
        jCB3.addItem("TALLER");
        jCB3.addItem("DEBATE");
        jCB3.addItem("OTRA");

        JButton btn1 = new JButton("Registrar Actividad");

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
        txf8 = new JTextField();
        txf8.setPreferredSize(new Dimension(300, 25));

        add(pnl1);
        add(pnl2);
        add(pnl3);
        add(pnl4);
        add(pnl5);
        add(pnl6);
        add(pnl7);
        add(pnl8);
        add(pnl9);

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

        pnl7.add(lblf7, BorderLayout.NORTH);
        pnl7.add(txf7);
        pnl7.add(lbl7, BorderLayout.SOUTH);

        pnl8.add(lblf8, BorderLayout.NORTH);
        pnl8.add(txf8);
        pnl8.add(lbl8, BorderLayout.SOUTH);

        pnl9.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnCrearActionPerformer();
            }
            
        });
        txf1.addFocusListener(new FocLisTexto(lbl1));
        txf2.addFocusListener(new FocLisTexto(lbl2));
        txf4.addFocusListener(new FocLisTexto(lbl4));
        txf5.addFocusListener(new FocLisTexto(lbl5));
        txf6.addFocusListener(new FocLisTexto(lbl6));
        txf7.addFocusListener(new FocLisTexto(lbl7));
        txf8.addFocusListener(new FocLisTexto(lbl8));

    }

    private void btnCrearActionPerformer(){

        Data_Actividad data = new Data_Actividad();
        int completo = 0;

        if (chequearCampo(txf1, 8)) {
            data.setCodigoActividad(txf1.getText());
            completo++;
        } else {
            lbl1.setText("Ingrese un código de actividad válido");
        }
        
        if (chequearCampo(txf2, 8)) {
            data.setCodigoEvento(txf2.getText());
            completo++;
        } else {
            lbl2.setText("Imgrese un código de evento válido");
        } 

        if (jCB3.getSelectedIndex() != -1) {
            try {
                data.setTipoActividad(jCB3.getSelectedItem().toString());
                completo++;
            } catch (SelecionTipoException e) {
                lbl3.setText("Seleccione un tipo de actividad válido");
            }
        } else {
            lbl3.setText("Seleccione un tipo de actividad");
        }

        if (chequearCampo(txf4, 201)) {
            data.setTituloActividad(txf4.getText());
            completo++;
        } else {
            lbl4.setText("Ingrese un título de actividad válido");
        }

        if (txf5.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
            && chequearCampo(txf5, 51)) {
            data.setCorreoImpartidor(txf5.getText());
            completo++;
        } else {
            lbl5.setText("Ingrese un correo electrónico válido");
        }

        if (txf6.getText().matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$")
            && chequearCampo(txf6, 6)) {
            data.setHoraInicio(txf6.getText());
            completo++;
        } else {
            lbl6.setText("Ingrese una hora de inicio válida (HH:MM)");
        }

        if (txf7.getText().matches("^([01][0-9]|2[0-3]):[0-5][0-9]$")
            && chequearCampo(txf7, 6)) {
            data.setHoraFin(txf7.getText());
            completo++;
        } else {
            lbl7.setText("Ingrese una hora de fin válida (HH:MM)");
        }

        if (txf8.getText().matches("\\d+")) {
            data.setCupoMaximo(Integer.parseInt(txf8.getText()));
            completo++;
        } else {
            lbl8.setText("Ingrese un cupo máximo válido");
        }

        if (completo == 8) {

            if (revisarActividad(data)) {

                frame.getConexion().guardarActividad(data);
                setVisible(false);
                
            }

        }
    }

    private boolean revisarActividad(Data_Actividad data) {
        
        if (frame.getConexion().consultarActividad(data.getCodigoActividad())) {
            JOptionPane.showMessageDialog(frame, "Ya existe una actividad con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!frame.getConexion().consultarEvento(data.getCodigoEvento())) {
            JOptionPane.showMessageDialog(frame, "No existe el evento ingresado. Por favor registrar el evento primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (revisarImpartidor(data)) {
            if (data.getHoraFin().compareTo(data.getHoraInicio()) <= 0) {
                JOptionPane.showMessageDialog(frame, "La hora de fin debe ser posterior a la hora de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }   
    

    private boolean revisarImpartidor(Data_Actividad data) {

        Data_Inscripcion data_ins = frame.getConexion().solicitarInscripcion(data.getCorreoImpartidor(),
                data.getCodigoEvento());

        if (!frame.getConexion().consultarParticipante(data.getCorreoImpartidor())) {
            JOptionPane.showMessageDialog(frame, "El correo electrónico del impartidor no está registrado. Por favor registrar el participante primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (data_ins == null) {
            JOptionPane.showMessageDialog(frame, "El impartidor no está inscrito en el evento. Por favor inscribir al participante primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (data_ins.getValidacion() == false) {
            JOptionPane.showMessageDialog(frame, "El impartidor no está validado para el evento. Por favor validar al participante primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!frame.getConexion().revisarTipoInscripcion(data.getCorreoImpartidor(), data.getCodigoEvento())) {
            JOptionPane.showMessageDialog(frame, "El impartidor esta incrito como asistente, no puede impartir actividades.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    private boolean chequearCampo(JTextField campo, int tamaño) {
        return (campo.getText().length() < tamaño && !campo.getText().isEmpty());
    }

    public void hacerVisible() {

        setLocation((frame.getWidth() - 400) / 2, (frame.getHeight() - 725) / 2);
        setVisible(true);
        txf1.setText("");
        txf2.setText("");
        jCB3.setSelectedIndex(-1);
        txf4.setText("");
        txf5.setText("");
        txf6.setText("");
        txf7.setText("");
        txf8.setText("");

    }

}
