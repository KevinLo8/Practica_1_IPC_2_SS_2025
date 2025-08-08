package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Datos.Data_Inscripcion;
import com.Practica_1.Backend.Listener.FocLisTexto;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Validacion extends JInternalFrame {

    private Frame_principal frame;
    private JTextField txf1, txf2;
    private JLabel lbl1, lbl2;
    private JLabel lblf1, lblf2;

    public IF_Validacion(Frame_principal frame) {
        super("Validar Inscripción De Estudiante", false, true, false, false);
        this.frame = frame;

        setSize(new Dimension(400, 425));
        setLayout(new GridLayout(5, 1, 0, 5));

        frame.getDesktop().add(this);

        initComponentes();
        
    }

    private void initComponentes(){

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel pnl1 = new JPanel();
        JPanel pnl2 = new JPanel();
        JPanel pnl3 = new JPanel();

        lblf1 = new JLabel("Ingrese el correo electrónico del participante");
        lblf2 = new JLabel("Ingrese el código de evento");

        lbl1 = new JLabel(" ");
        lbl2 = new JLabel(" ");

        JButton btn1 = new JButton("Validar Inscripción");

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
                btnCrearActionPerformer();
            }
            
        });
        txf1.addFocusListener(new FocLisTexto(lbl1));
        txf2.addFocusListener(new FocLisTexto(lbl2));

    }

    private void btnCrearActionPerformer(){

        Data_Inscripcion data = new Data_Inscripcion();
        int completo = 0;

        if (txf1.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
            && chequearCampo(txf1, 51)) {
            data.setCorreoParticipante(txf1.getText());
            completo++;
        } else {
            lbl1.setText("Ingrese un correo electrónico valido");
        }
        
        if (chequearCampo(txf2, 8)) {
            data.setCodigoEvento(txf2.getText());
            completo++;
        } else {
            lbl2.setText("Ingrese un código de evento valido");
        } 

        if (completo == 2) {

            if (revisarInscripcion(data)) {

                frame.getConexion().guardarValidacion(data);
                setVisible(false);
                
            }

        }
    }

    private boolean revisarInscripcion(Data_Inscripcion data) {
        if (!frame.getConexion().consultarInscripcion(data.getCorreoParticipante(), data.getCodigoEvento())) {
            JOptionPane.showMessageDialog(frame, "No existe la inscripción ingresada. Por favor registrar la inscripción primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (frame.getConexion().consultarPago(data.getCorreoParticipante(), data.getCodigoEvento())) {
            JOptionPane.showMessageDialog(frame, "No se ha realizado el pago de la inscripción. Por favor registrar el pago primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!frame.getConexion().consultarValidacion(data.getCorreoParticipante(), data.getCodigoEvento())) {
            JOptionPane.showMessageDialog(frame, "Esta Inscripción ya ha sido validada.", "Error", JOptionPane.ERROR_MESSAGE);
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

    }

}
