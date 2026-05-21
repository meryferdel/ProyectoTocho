package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.Excepciones.DAOException;
import es.biblio.proyectotocho.persistencia.ProductCategory;
import es.biblio.proyectotocho.persistencia.ProductCategoryDAO;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import es.biblio.proyectotocho.presentacion.ejercicio1.VentanaEj1;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejer1Controller {
    private VentanaEj1 ventana;
    private ProductCategoryDAO dao;

    public Ejer1Controller() {

        ventana = new VentanaEj1();

        dao = new ProductCategoryDAO();

        ventana.lamina.getBtnCrear().addActionListener(
                new EventoCrearCategoria()
        );
    }

    private class EventoCrearCategoria implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String nombreCategoria =
                    ventana.lamina.getTxtNombre().getText().trim();

            if (nombreCategoria.isEmpty()) {

                UtilidadesVista.mostrarVacio(ventana);

                return;
            }

            try {

                // COMPROBAR SI YA EXISTE
                ProductCategory categoriaExistente =
                        dao.findByName(nombreCategoria);

                if (categoriaExistente != null) {

                    UtilidadesVista.mostrarWarning(ventana);

                    return;
                }

                // CREAR OBJETO
                ProductCategory nuevaCategoria =
                        new ProductCategory(nombreCategoria);

                // INSERTAR
                dao.insert(nuevaCategoria);

                UtilidadesVista.mostrarExito(ventana);

                // LIMPIAR TEXTFIELD
                ventana.lamina.getTxtNombre().setText("");

            } catch (DAOException ex) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Error al insertar la categoría"
                );

                System.out.println(ex.getMessage());
            }
        }
    }
}