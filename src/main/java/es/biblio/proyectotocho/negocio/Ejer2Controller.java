package es.biblio.proyectotocho.negocio.ejercicio2;

import es.biblio.proyectotocho.Excepciones.DAOException;
import es.biblio.proyectotocho.persistencia.Product;
import es.biblio.proyectotocho.persistencia.ProductCategory;
import es.biblio.proyectotocho.persistencia.ProductCategoryDAO;
import es.biblio.proyectotocho.persistencia.ProductDAO;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import es.biblio.proyectotocho.presentacion.ejercicio2.VentanaEj2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ejer2Controller {

    private VentanaEj2 ventana;
    private ProductDAO productDAO;
    private ProductCategoryDAO categoryDAO;

    public Ejer2Controller() {

        ventana = new VentanaEj2();

        productDAO = new ProductDAO();
        categoryDAO = new ProductCategoryDAO();

        cargarCategorias();

        ventana.lamina.getBtnCrear().addActionListener(
                new EventoCrearProducto()
        );
    }

    private void cargarCategorias() {

        try {

            List<ProductCategory> categorias =
                    categoryDAO.findAll();

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

    private class EventoCrearProducto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                String nombre =
                        ventana.lamina.getTxtNombre()
                                .getText().trim();

                String descripcion =
                        ventana.lamina.getTxtDescripcion()
                                .getText().trim();

                String costeTexto =
                        ventana.lamina.getTxtCoste()
                                .getText().trim();

                String precioTexto =
                        ventana.lamina.getTxtPrecio()
                                .getText().trim();

                ProductCategory categoria =
                        (ProductCategory)
                                ventana.lamina
                                        .getComboCategorias()
                                        .getSelectedItem();

                // VALIDACIONES

                if (nombre.isEmpty() ||
                        descripcion.isEmpty() ||
                        costeTexto.isEmpty() ||
                        precioTexto.isEmpty() ||
                        categoria == null) {

                    UtilidadesVista.mostrarVacio(ventana);

                    return;
                }

                double coste =
                        Double.parseDouble(costeTexto);

                double precio =
                        Double.parseDouble(precioTexto);

                // CREAR PRODUCTO

                Product producto = new Product();

                producto.setProductName(nombre);
                producto.setDescription(descripcion);
                producto.setStandardCost(coste);
                producto.setListPrice(precio);
                producto.setCategoryId(
                        categoria.getCategoryId()
                );

                // INSERTAR

                productDAO.insert(producto);

                UtilidadesVista.mostrarExitoMensaje(ventana, "Producto creado correctamente");

                // LIMPIAR CAMPOS

                ventana.lamina.getTxtNombre().setText("");
                ventana.lamina.getTxtDescripcion().setText("");
                ventana.lamina.getTxtCoste().setText("");
                ventana.lamina.getTxtPrecio().setText("");

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Coste y precio deben ser numéricos"
                );

            } catch (DAOException ex) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Error al crear el producto"
                );

                System.out.println(ex.getMessage());
            }
        }
    }
}