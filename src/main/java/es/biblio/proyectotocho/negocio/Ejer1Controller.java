// Paquete de la capa de negocio (lógica) del proyecto.
package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.persistencia.ProductCategory; 
import es.biblio.proyectotocho.persistencia.ProductCategoryDAO;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import es.biblio.proyectotocho.presentacion.ejercicio1.VentanaEj1;
import javax.swing.*;

// Controlador del ejercicio 1, que se encarga de coordinar la lógica entre la vista de la presentación (VentanaEj1) y el DAO (ProductCategoryDAO).
public class Ejer1Controller {

    private VentanaEj1 ventana; // Propiedad referente a la ventana gráfica asociada a este controlador.
    private ProductCategoryDAO dao; // Propiedad referente al Objeto DAO para trabajar con categorías de producto en la base de datos.

    // Contructor del controlador:
    public Ejer1Controller() {
        // Crea la ventana del ejercicio 1 y le pasa este controlador,
        // para que la vista pueda llamar a métodos del controlador (por ejemplo, al pulsar un botón).
        ventana = new VentanaEj1(this); 
       
        dao = new ProductCategoryDAO(); // Crea el DAO que se usará para acceder a las categorías en la base de datos.
    }

    // Método de negocio que se encarga de crear una nueva categoría de producto
    // a partir del nombre que introduce el usuario en la interfaz gráfica.
    public void crearCategoriaProducto(String nombreCategoria) {
        try {
            // Primero se comprueba si ya existe una categoría con ese nombre en la base de datos.
            ProductCategory categoriaExistente = dao.findByName(nombreCategoria);
            
            // Si la categoría ya existe, se muestra un aviso al usuario y se sale del método sin insertar nada.
            if (categoriaExistente != null) {
                UtilidadesVista.mostrarWarning(ventana);
                return;
            }

            // Si no existe, se crea un nuevo objeto ProductCategory con el nombre indicado.
            ProductCategory nuevaCategoria = new ProductCategory(nombreCategoria);

            // Se inserta la nueva categoría en la base de datos usando el DAO.
            dao.insert(nuevaCategoria);

            // Se muestra un mensaje de éxito al usuario.
            UtilidadesVista.mostrarExito(ventana);

            // Se limpia el campo de texto de la ventana para dejarlo listo para otra entrada.
            ventana.lamina.getTxtNombre().setText("");

        } catch (DAOException ex) { // Si ocurre algún error en la capa de acceso a datos (DAO), se captura la excepción y se informa al usuario.

            // Muestra un cuadro de diálogo con un mensaje de error.
            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al insertar la categoría"
            );

            // Imprime el mensaje de error en la consola (útil para depuración).
            System.out.println(ex.getMessage());
        }
    }
}
