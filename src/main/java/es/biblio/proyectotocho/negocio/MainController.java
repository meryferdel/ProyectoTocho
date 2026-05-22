/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.presentacion.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Alberto
 */
public class MainController {

    private VentanaBienvenida ventana;

    public MainController() {

        ventana = new VentanaBienvenida();

        JButton[] botones = ventana.lamina.getBotones();

        botones[0].addActionListener(new EventoBoton1());
        botones[1].addActionListener(new EventoBoton2());
        botones[2].addActionListener(new EventoBoton3());
        botones[3].addActionListener(new EventoBoton4());
        botones[4].addActionListener(new EventoBoton5());
        botones[5].addActionListener(new EventoBoton6());
    }


    // EJERCICIO 1
    private class EventoBoton1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            new Ejer1Controller();
        }
    }

    // EJERCICIO 2
    private class EventoBoton2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            new Ejer2Controller();

        }
    }

    // EJERCICIO 3
    private class EventoBoton3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            new Ejer2Controller();

        }
    }

    // EJERCICIO 4
    private class EventoBoton4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            

        }
    }

    // EJERCICIO 5
    private class EventoBoton5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Abrir ventana Aplicar descuento"
            );

        }
    }

    // EJERCICIO 6
    private class EventoBoton6 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Abrir ventana Traspaso y cierre"
            );

        }
    }

}
