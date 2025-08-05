package com.Practica_1.Frontend.InternalFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.Practica_1.Backend.Datos.Data_Participante;
import com.Practica_1.Backend.Listener.FocLisTexto;
import com.Practica_1.Frontend.Frame_principal;

public class IF_Participante extends JInternalFrame {

    private Frame_principal frame;
    private JTextField txf1, txf3, txf4;
    private JComboBox<String> jCB2;
    private JLabel lbl1, lbl2, lbl3, lbl4;
    private JLabel lblf1, lblf2, lblf3, lblf4;

    public IF_Participante(Frame_principal frame) {
        super("Registrar Participante Nuevo", false, true, false, false);
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
        JPanel pnl4 = new JPanel();
        JPanel pnl5 = new JPanel();

        lblf1 = new JLabel("Ingrese el nombre completo del participante");
        lblf2 = new JLabel("Seleccione el tipo de participante");
        lblf3 = new JLabel("Ingrese la institución del participante");
        lblf4 = new JLabel("Ingrese el correo electrónico del participante");

        lbl1 = new JLabel(" ");
        lbl2 = new JLabel(" ");
        lbl3 = new JLabel(" ");
        lbl4 = new JLabel(" ");

        JButton btn1 = new JButton("Guardar Participante");

        txf1 = new JTextField();
        txf1.setPreferredSize(new Dimension(300, 25));
        txf3 = new JTextField();
        txf3.setPreferredSize(new Dimension(300, 25));
        txf4 = new JTextField();
        txf4.setPreferredSize(new Dimension(300, 25));

        jCB2 = new JComboBox<>();
        jCB2.setPreferredSize(new Dimension(300, 25));
        jCB2.setBackground(Color.WHITE);

        jCB2.addItem("ESTUDIANTE");
        jCB2.addItem("PROFESIONAL");
        jCB2.addItem("INVITADO");

        add(pnl1);
        add(pnl2);
        add(pnl3);
        add(pnl4);
        add(pnl5);

        pnl1.add(lblf1, BorderLayout.NORTH);
        pnl1.add(txf1);
        pnl1.add(lbl1, BorderLayout.SOUTH);

        pnl2.add(lblf2, BorderLayout.NORTH);
        pnl2.add(jCB2);
        pnl2.add(lbl2, BorderLayout.SOUTH);

        pnl3.add(lblf3, BorderLayout.NORTH);
        pnl3.add(txf3);
        pnl3.add(lbl3, BorderLayout.SOUTH);

        pnl4.add(lblf4, BorderLayout.NORTH);
        pnl4.add(txf4);
        pnl4.add(lbl4, BorderLayout.SOUTH);

        pnl5.add(btn1);

        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnCrearActionPerformer();
            }
            
        });
        txf1.addFocusListener(new FocLisTexto(lbl1));
        jCB2.addFocusListener(new FocLisTexto(lbl2));
        txf3.addFocusListener(new FocLisTexto(lbl3));
        txf4.addFocusListener(new FocLisTexto(lbl4));

    }

    private void btnCrearActionPerformer(){

        Data_Participante data = new Data_Participante();
        int completo = 0;

        if (chequearCampo(txf1, 46)) {
            data.setNombreParticipante(txf1.getText());
            completo++;
        } else {
            lbl1.setText("Ingrese un nombre valido");
        }
        
        if (jCB2.getSelectedIndex() != -1) {
            data.setTipoParticipante(jCB2.getSelectedItem().toString());
            completo++;
        } else {
            lbl2.setText("Seleccione un tipo de participante");
        } 

        if (chequearCampo(txf3, 151)) {
            data.setInstitucionParticipante(txf3.getText());
            completo++;
        } else {
            lbl3.setText("Ingrese una institución valida");
        }

        if (txf4.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
            && chequearCampo(txf4, 51)) {
            data.setCorreoParticipante(txf4.getText());
            completo++;
        } else {
            lbl4.setText("Ingrese un correo electrónico valido");
        }

        if (completo == 4) {

            frame.getConexion().guardarParticipante(data);

            setVisible(false);
        }
    }

    private boolean chequearCampo(JTextField campo, int tamaño) {
        return (campo.getText().length() < tamaño && !campo.getText().isEmpty());
    }

    public void hacerVisible() {

        setLocation((frame.getWidth() - 400) / 2, (frame.getHeight() - 425) / 2);
        setVisible(true);
        txf1.setText("");
        jCB2.setSelectedIndex(-1);
        txf3.setText("");
        txf4.setText("");

    }

}
