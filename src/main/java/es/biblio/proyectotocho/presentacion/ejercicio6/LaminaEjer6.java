// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion.ejercicio6;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.negocio.Ejer6Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lámina gráfica del Ejercicio 6.
// Permite introducir dos IDs de almacén: uno a cerrar y otro que recibirá el inventario.
public class LaminaEjer6 extends JPanel {

    private JTextField txtCerrado; // Campo donde el usuario introduce el ID del almacén que se va a cerrar.
    private JTextField txtTraspaso; // Campo donde el usuario introduce el ID del almacén que recibirá el inventario.
    private JButton btnCerrarTraspasar; // Botón que ejecuta la operación de cierre y traspaso.
    private JFrame ventana; // Referencia a la ventana padre para mostrar mensajes emergentes.
    private Ejer6Controller controlador; // Controlador que gestiona la lógica del ejercicio 6.

    // Constructor de la lámina. Recibe la ventana padre y el controlador.
    public LaminaEjer6(JFrame ventanaPadre, Ejer6Controller controlador) {

        this.ventana = ventanaPadre; // Guarda la ventana para mostrar mensajes emergentes.
        this.controlador = controlador; // Guarda el controlador para ejecutar la operación de cierre y traspaso.

        // Establece un color de fondo suave.
        setBackground(new Color(220, 220, 220));

        // Usa GridBagLayout para colocar los componentes con flexibilidad.
        setLayout(new GridBagLayout());

        // Objeto que define la posición y comportamiento de cada componente.
        GridBagConstraints gbc = new GridBagConstraints();

        // Márgenes entre componentes.
        gbc.insets = new Insets(10, 10, 10, 10);

        
        // ---------- CAMPO DE ID DEL ALMACÉN A CERRAR ---------- 
        // ------------------------------------------------------
        // Etiqueta que indica el ID del almacén que se va a cerrar.
        JLabel lblCierre = new JLabel("ID almacén a cerrar:");

        // Posición de la etiqueta: columna 0, fila 0.
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblCierre, gbc); // Añade la etiqueta a la lámina.

        // Campo donde el usuario escribe el ID del almacén a cerrar.
        txtCerrado = new JTextField(15);
        gbc.gridx = 1;
        add(txtCerrado, gbc); // Añade el campo de texto.

        
        // ---------- CAMPO DE ID DEL ALMACÉN DE DESTINO -----------
        // ---------------------------------------------------------
        // Etiqueta que indica el ID del almacén que recibirá el inventario.
        JLabel lblTraspaso = new JLabel("ID almacén destino:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblTraspaso, gbc); // Añade la etiqueta.

        // Campo donde el usuario escribe el ID del almacén destino.
        txtTraspaso = new JTextField(15);
        gbc.gridx = 1;
        add(txtTraspaso, gbc); // Añade el campo de texto.

        
        // ------- BOTÓN DE CERRAR Y TRASPASAR EL INVENTARIO ---------
        // -----------------------------------------------------------
        // Botón que ejecuta la operación de cierre y traspaso.
        btnCerrarTraspasar = new JButton("Cerrar y traspasar inventario");
        btnCerrarTraspasar.setBackground(new Color(245, 245, 245)); // Color claro para el botón.
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // El botón ocupa las dos columnas.
        add(btnCerrarTraspasar, gbc); // Añade el botón a la lámina.

        // Asigna el listener que gestionará el clic del botón.
        btnCerrarTraspasar.addActionListener(new ManejadorTraspaso());
    }

    // Getters para acceder a los campos desde la ventana o el controlador.
    public JTextField getTxtCerrado() {
        return txtCerrado;
    }

    public JTextField getTxtTraspaso() {
        return txtTraspaso;
    }

    public JButton getBtnCerrarTraspasar() {
        return btnCerrarTraspasar;
    }

    // Clase interna que gestiona el evento del botón "Cerrar y traspasar inventario".
    private class ManejadorTraspaso implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Obtiene el ID del almacén a cerrar.
            String origen = txtCerrado.getText().trim();

            // Obtiene el ID del almacén destino.
            String destino = txtTraspaso.getText().trim();

            // Valida ambos campos que deben estar rellenos.
            if (origen.isEmpty() || destino.isEmpty()) {
                JOptionPane.showMessageDialog(
                        ventana,
                        "Debes rellenar ambos campos"
                );
                return;
            }

            // Valida ambos IDs deben ser numéricos.
            if (!isNumber(origen) || !isNumber(destino)) {
                JOptionPane.showMessageDialog(
                        ventana,
                        "Los IDs deben ser numéricos"
                );
                return;
            }

            // Valida que los almacenes deben ser distintos.
            if (origen.equals(destino)) {
                JOptionPane.showMessageDialog(
                        ventana,
                        "Los almacenes deben ser distintos"
                );
                return;
            }

            // Intenta ejecutar la operación de cierre y traspaso.
            try {
                controlador.cerrarYTraspasar(Integer.parseInt(origen), Integer.parseInt(destino));

            } catch (Exception ex) {
                // Muestra un mensaje genérico de error.
                JOptionPane.showMessageDialog(
                        ventana,
                        "Error en la operación"
                );

                // Imprime el mensaje de error en consola para depuración.
                System.out.println(ex.getMessage());
            }
        }
    }

    // Método auxiliar que comprueba si una cadena puede convertirse a número entero.
    private boolean isNumber(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}