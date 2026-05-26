package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.Excepciones.DAOException;
import es.biblio.proyectotocho.persistencia.ProductCategory;
import es.biblio.proyectotocho.persistencia.ProductCategoryDAO;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import es.biblio.proyectotocho.presentacion.ejercicio1.VentanaEj1;
import javax.swing.*;

public class Ejer1Controller {
    private VentanaEj1 ventana;
    private ProductCategoryDAO dao;

    public Ejer1Controller() {

        ventana = new VentanaEj1(this);

        dao = new ProductCategoryDAO();


    }
    
    public void crearCategoriaProducto(String nombreCategoria) {
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