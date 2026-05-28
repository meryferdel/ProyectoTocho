package es.biblio.proyectotocho.presentacion;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UtilidadesVista {
    
    
    public static void mostrarWarning (JFrame ventanita) {
        JOptionPane.showMessageDialog(
                ventanita,
                "Ya existe");
    }
    
    public static void mostrarVacio (JFrame ventanita) {
        JOptionPane.showMessageDialog(
                ventanita,
                "No puede estar vacio");
    }
    
    public static void mostrarExito (JFrame ventanita) {
        JOptionPane.showMessageDialog(
                ventanita,
                "Añadido correctamente");
    }
    
    public static void mostrarWarningMensaje (JFrame ventanita, String mensaje) {
        JOptionPane.showMessageDialog(
                ventanita,
                mensaje);
    }
    
    public static void mostrarVacioMensaje (JFrame ventanita, String mensaje) {
        JOptionPane.showMessageDialog(
                ventanita,
                mensaje);
    }
    
    public static void mostrarExitoMensaje (JFrame ventanita, String mensaje) {
        JOptionPane.showMessageDialog(
                ventanita,
                mensaje);
    }
    
    public static void mostrarError (JFrame ventanita, String mensaje) {
        JOptionPane.showMessageDialog(
                ventanita,
                mensaje);
    }
}
