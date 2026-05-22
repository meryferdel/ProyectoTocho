package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.persistencia.Product;
import es.biblio.proyectotocho.persistencia.ProductCategory;
import es.biblio.proyectotocho.persistencia.ProductCategoryDAO;
import es.biblio.proyectotocho.persistencia.ProductDAO;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import es.biblio.proyectotocho.presentacion.ejercicio2.VentanaEj2;

import javax.swing.*;
import java.util.List;

public class Ejer2Controller {

    private VentanaEj2 ventana;
    private ProductDAO productDAO;
    private ProductCategoryDAO categoryDAO;

    public Ejer2Controller() {

        ventana = new VentanaEj2(this);

        productDAO = new ProductDAO();
        categoryDAO = new ProductCategoryDAO();

        cargarCategorias();
    }

    private void cargarCategorias() {

        try {

            List<ProductCategory> categorias
                    = categoryDAO.findAll();

            for (ProductCategory categoria : categorias) {

                ventana.lamina.getComboCategorias()
                        .addItem(categoria);
            }

        } catch (DAOException e) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al cargar categorías"
            );
        }
    }

    public void crearNuevoProducto(String nombre, String descripcion, double coste, double precio, int categoryId) {
        try {
            // CREAR PRODUCTO

            Product producto = new Product(null, nombre, descripcion, coste, precio, categoryId);

            // INSERTAR
            productDAO.insert(producto);

            ventana.limpiarCamposTexto();
            UtilidadesVista.mostrarExitoMensaje(ventana, "Producto creado correctamente");


        } catch (DAOException ex) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al crear el producto"
            );

            System.out.println(ex.getMessage());
        }

    }
}
