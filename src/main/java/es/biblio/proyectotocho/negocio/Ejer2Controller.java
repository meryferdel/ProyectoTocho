// Paquete de la capa de negocio (lógica) del proyecto.
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

// Controlador del ejercicio 2, que se encarga de coordinar la lógica entre la vista de la presentación (VentanaEj2) y el DAO (ProductDAO y ProductCategoryDAO).
public class Ejer2Controller {

    private VentanaEj2 ventana; // Propiedad referente a la ventana gráfica asociada a este controlador.
    private ProductDAO productDAO; // DAO para trabajar con los productos en la base de datos.
    private ProductCategoryDAO categoryDAO; // DAO para trabajar con categorías de producto en la base de datos.

    // Contructor del controlador del ejercicio 2:
    public Ejer2Controller() {
        // Crea la ventana del ejercicio 2 y le pasa este controlador,
        // para que la vista pueda llamar a métodos del controlador (por ejemplo, al pulsar un botón).
        ventana = new VentanaEj2(this);

        // Inicializa los DAOs que se usarán para acceder a la base de datos.
        productDAO = new ProductDAO();
        categoryDAO = new ProductCategoryDAO();

        // Carga las categorías desde la base de datos y las muestra en el combo de la ventana.
        cargarCategorias();
    }

    // Método privado que se encarga de obtener todas las categorías de producto y añadirlas al comboBox de la interfaz gráfica.
    private void cargarCategorias() {

        try {
            // Obtiene la lista de todas las categorías desde la base de datos.
            List<ProductCategory> categorias = categoryDAO.findAll();

            // Recorre la lista de categorías y las añade al combo de la ventana.
            for (ProductCategory categoria : categorias) {
                ventana.lamina.getComboCategorias().addItem(categoria);
            }

        } catch (DAOException e) {
            // Si ocurre un error al acceder a la base de datos, se muestra un mensaje al usuario:
            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al cargar categorías"
            );
        }
    }

    // Método de negocio que se encarga de crear un nuevo producto
    // a partir de los datos introducidos por el usuario en la interfaz gráfica:
    public void crearNuevoProducto(String nombre, String descripcion, double coste, double precio, int categoryId) {
        try {
            // Crea un nuevo objeto Product con los datos recibidos.
            // El primer parámetro (null) es el id, que se genera automáticamente en la base de datos.
            Product producto = new Product(null, nombre, descripcion, coste, precio, categoryId);

            // Inserta el nuevo producto en la base de datos usando el DAO.
            productDAO.insert(producto);

            // Limpia los campos de texto de la ventana para dejarla lista para otro producto.
            ventana.limpiarCamposTexto();
            
            // Muestra un mensaje de éxito indicando que el producto se ha creado correctamente.
            UtilidadesVista.mostrarExitoMensaje(ventana, "Producto creado correctamente");

        } catch (DAOException ex) {
            // Si ocurre un error al crear el producto en la base de datos, se informa al usuario por ventana.
            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al crear el producto"
            );

            // Imprime el mensaje de error en la consola para facilitar la depuración.
            System.out.println(ex.getMessage());
        }
    }
}
