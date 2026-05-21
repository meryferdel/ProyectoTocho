/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.biblio.proyectotocho.presentacion;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario25
 */
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
}
