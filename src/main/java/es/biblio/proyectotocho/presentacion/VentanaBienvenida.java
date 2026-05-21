/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.biblio.proyectotocho.presentacion;

import javax.swing.*;
import presentacion.bienvenida.LaminaBienvenida;

/**
 *
 * @author Alberto
 */
public class VentanaBienvenida extends JFrame{
    public final int ALTO = 600;
    public final int ANCHO = 400;
    public LaminaBienvenida lamina;

    public VentanaBienvenida() {
        setTitle("Proyecto Dual en el centro");
        setSize(ALTO, ANCHO);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lamina = new LaminaBienvenida();
        add(lamina);      
        setVisible(true);
    }
    
    
}
