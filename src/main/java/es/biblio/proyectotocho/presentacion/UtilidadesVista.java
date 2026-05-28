// Paquete de la capa de presentación del proyecto.
package es.biblio.proyectotocho.presentacion;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// Clase de utilidades para la vista.
// Contiene métodos estáticos que muestran distintos tipos de mensajes al usuario mediante cuadros de diálogo (JOptionPane). 
// Se utiliza para centralizar la forma en la que se muestran avisos, errores y mensajes de éxito.
public class UtilidadesVista {

    // Muestra un mensaje de advertencia genérico indicando que el elemento ya existe.
    public static void mostrarWarning(JFrame ventanita) {
        JOptionPane.showMessageDialog(
                ventanita,
                "Ya existe"
        );
    }

    // Muestra un mensaje indicando que un campo obligatorio está vacío.
    public static void mostrarVacio(JFrame ventanita) {
        JOptionPane.showMessageDialog(
                ventanita,
                "No puede estar vacio"
        );
    }

    // Muestra un mensaje indicando que la operación se ha realizado correctamente.
    public static void mostrarExito(JFrame ventanita) {
        JOptionPane.showMessageDialog(
                ventanita,
                "Añadido correctamente"
        );
    }

    // Muestra un mensaje de advertencia personalizado.
    public static void mostrarWarningMensaje(JFrame ventanita, String mensaje) {
        JOptionPane.showMessageDialog(
                ventanita,
                mensaje
        );
    }

    // Muestra un mensaje indicando que un campo obligatorio está vacío, con texto personalizado.
    public static void mostrarVacioMensaje(JFrame ventanita, String mensaje) {
        JOptionPane.showMessageDialog(
                ventanita,
                mensaje
        );
    }

    // Muestra un mensaje de éxito personalizado.
    public static void mostrarExitoMensaje(JFrame ventanita, String mensaje) {
        JOptionPane.showMessageDialog(
                ventanita,
                mensaje
        );
    }

    // Muestra un mensaje de error personalizado.
    public static void mostrarError(JFrame ventanita, String mensaje) {
        JOptionPane.showMessageDialog(
                ventanita,
                mensaje
        );
    }
}
