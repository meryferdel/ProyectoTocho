package es.biblio.proyectotocho.presentacion.ejercicio6;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario25
 */
public class LaminaEjer6 extends JPanel{
    
    private JTextField txtCerrado;
    private JTextField txtTraspaso;
    private JButton btnCerrarTraspasar;

    public LaminaEjer6() {
        
        setBackground(new Color(220, 220, 220));

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
        
        //ID DE ALMACÉN DE CIERRE
        JLabel lblCierre = new JLabel("ID del almacén de cierre:");
        
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(lblCierre, gbc);

        txtCerrado = new JTextField();

        gbc.gridx = 1;

        add(txtCerrado, gbc);
        
        //ID DE ALMACÉN DE TRASPASO
        JLabel lblTraspaso = new JLabel("ID del almacén a traspasar:");

        gbc.gridx = 0;
        gbc.gridy = 1;

        add(lblTraspaso, gbc);

        txtTraspaso = new JTextField();

        gbc.gridx = 1;

        add(txtTraspaso, gbc);
        
        //BOTÓN CERRAR Y TRASPASAR
        btnCerrarTraspasar = new JButton("Cerrar y traspasar almacén");

        btnCerrarTraspasar.setBackground(new Color(245, 245, 245));

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        add(btnCerrarTraspasar, gbc);
         
        
    }

    public JTextField getTxtCerrado() {
        return txtCerrado;
    }

    public JTextField getTxtTraspaso() {
        return txtTraspaso;
    }

    public JButton getBtnCerrarTraspasar() {
        return btnCerrarTraspasar;
    }
    
    private class manejadorTraspasoCierre implements ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
    }
    
}
