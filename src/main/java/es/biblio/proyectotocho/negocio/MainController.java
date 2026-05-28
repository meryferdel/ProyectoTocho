// Paquete de la capa de negocio (lógica) del proyecto.
package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.presentacion.*;
import java.awt.event.*;
import javax.swing.*;

// Controlador principal del proyecto. 
// Se encarga de gestionar la ventana de bienvenida y de abrir cada uno de los ejercicios
// cuando el usuario pulsa el botón correspondiente.
public class MainController {

    private VentanaBienvenida ventana; // Propiedad referente a la ventana principal del programa.

    // Constructor del controlador principal:
    public MainController() {

        ventana = new VentanaBienvenida(); // Crea la ventana de bienvenida.

        // Obtiene el array de botones que contiene la lámina de la ventana.
        JButton[] botones = ventana.lamina.getBotones();

        // Asocia un ActionListener distinto a cada botón, de forma que cada uno abra el ejercicio correspondiente 
        // con su controlador particular de cada ejercicio.
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

            new Ejer3Controller();

        }
    }

    // EJERCICIO 4
    private class EventoBoton4 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            new Ejer4Controller();

        }
    }

    // EJERCICIO 5
    private class EventoBoton5 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            new Ejer5Controller();

        }
    }

    // EJERCICIO 6
    private class EventoBoton6 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            new Ejer6Controller();

        }
    }
}
