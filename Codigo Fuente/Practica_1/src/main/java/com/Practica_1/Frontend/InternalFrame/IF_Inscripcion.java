package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Datos.Data_Inscripcion;
import com.Practica_1.Backend.Exception.SelecionTipoException;
import com.Practica_1.Backend.Listener.FocLisTexto;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Inscripcion extends JInternalFrame {

    private Frame_principal frame;
    private JTextField txf1, txf2;
    private JComboBox<String> jCB3;
    private JLabel lbl1, lbl2, lbl3;
    private JLabel lblf1, lblf2, lblf3;

    public IF_Inscripcion(Frame_principal frame) {
        super("Inscribir Participante En Evento", false, true, false, false);
        this.frame = frame;

        setSize(new Dimension(400, 425));
        setLayout(new GridLayout(4, 1, 0, 5));

        frame.getDesktop().add(this);

        initComponentes();
        
    }

    private void initComponentes(){

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();
        JPanel pnl4 = new JPanel();

        lblf1 = new JLabel("Ingrese el correo del participante");
        lblf2 = new JLabel("Ingrese el código del evento");
        lblf3 = new JLabel("Seleccione el tipo de inscripción");

        lbl1 = new JLabel(" ");
        lbl2 = new JLabel(" ");
        lbl3 = new JLabel(" ");

        JButton btn1 = new JButton("Guardar Inscripción");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf2 = new JTextField();
        txf2.setPreferredSize(new Dimension(300, 25));

        jCB3 = new JComboBox<>();
        jCB3.setPreferredSize(new Dimension(300, 25));
        jCB3.setBackground(Color.WHITE);

        jCB3.addItem("ASISTENTE");
        jCB3.addItem("CONFERENCISTA");
        jCB3.addItem("TALLERISTA");
        jCB3.addItem("OTRO");

        add(pnl1);
        add(pnl2);
        add(pnl3);
        add(pnl4);

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1);
        pnl1.add(lbl1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(txf2);
        pnl2.add(lbl2, BorderLayout.SOUTH);

        pnl3.add(lblf3, BorderLayout.NORTH);
        pnl3.add(jCB3);
        pnl3.add(lbl3, BorderLayout.SOUTH);

        pnl4.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnCrearActionPerformer();
            }
            
        });
        txf1.addFocusListener(new FocLisTexto(lbl1));
        txf2.addFocusListener(new FocLisTexto(lbl2));
        jCB3.addFocusListener(new FocLisTexto(lbl3));

    }

    private void btnCrearActionPerformer(){

        Data_Inscripcion data = new Data_Inscripcion();
        int completo = 0;

        if (txf1.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
            && chequearCampo(txf1, 51)) {
            data.setCorreoParticipante(txf1.getText());
            completo++;
        } else {
            lbl1.setText("Ingrese un correo electrónico válido");
        }
        
        if (chequearCampo(txf2, 8)) {
            data.setCodigoEvento(txf2.getText());
            completo++;
        } else {
            lbl2.setText("Ingrese un código de evento válido");
        } 

        if (jCB3.getSelectedIndex() != -1) {
            try {
            data.setTipoInscripcion(jCB3.getSelectedItem().toString());
            completo++;
            } catch (SelecionTipoException e) {
                lbl3.setText("Seleccione un tipo de inscripción válido");
            }
        } else {
            lbl3.setText("Seleccione un tipo de inscripción");
        }

        if (completo == 3) {

            if (revisarDatos(data)) {

                frame.getConexion().guardarInscripción(data);
                setVisible(false);

            } 
        }
    }

    private boolean revisarDatos(Data_Inscripcion data) {

        if (!frame.getConexion().consultarParticipante(data.getCorreoParticipante())) {
            JOptionPane.showMessageDialog(frame, "El participante no está registrado. Por favor, regístrelo primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!frame.getConexion().consultarEvento(data.getCodigoEvento())) {
            JOptionPane.showMessageDialog(frame, "El evento no está registrado. Por favor, regístrelo primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!frame.getConexion().consultarInscripcion(data.getCorreoParticipante(), data.getCodigoEvento())) {
            JOptionPane.showMessageDialog(frame, "El participante ya está inscrito en este evento.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }

    }

    private boolean chequearCampo(JTextField campo, int tamaño) {
        return (campo.getText().length() < tamaño && !campo.getText().isEmpty());
    }

    public void hacerVisible() {

        setLocation((frame.getWidth() - 400) / 2, (frame.getHeight() - 425) / 2);
        setVisible(true);
        txf1.setText("");
        txf2.setText("");
        jCB3.setSelectedIndex(-1);

    }

}
