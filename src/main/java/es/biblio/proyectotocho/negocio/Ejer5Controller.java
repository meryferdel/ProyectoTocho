// Paquete de la capa de negocio (lógica) del proyecto.
package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.persistencia.ProductCategory;
import es.biblio.proyectotocho.persistencia.ProductCategoryDAO;
import es.biblio.proyectotocho.persistencia.ProductDAO;
import es.biblio.proyectotocho.presentacion.ejercicio5.VentanaEjer5;
import java.util.List;
import javax.swing.JOptionPane;

// Controlador del ejercicio 5, encargado de coordinar la lógica entre la vista (VentanaEjer5)
// y los DAOs (ProductCategoryDAO y ProductDAO) que acceden a la base de datos.
public class Ejer5Controller {
    
    private VentanaEjer5 ventana; // Propiedad referente a la ventana gráfica asociada a este ejercicio.
    private ProductCategoryDAO productCategoryDAO; // DAO para trabajar con las categorías de producto.
    private ProductDAO productDAO; // DAO para trabajar con los productos.
    
    // Constructor del controlador del ejercicio 5:
    public Ejer5Controller() {
        // Crea la ventana del ejercicio 5 y le pasa este controlador,
        // para que la vista pueda llamar a sus métodos (por ejemplo, al pulsar un botón).
        ventana = new VentanaEjer5(this);
        
        // Inicializa los DAOs necesarios para acceder a la base de datos.
        productCategoryDAO = new ProductCategoryDAO();
        productDAO = new ProductDAO();

        // Llamamos al método que hace la carga de todas las categorías que se encuentren en la tabla ProductCategory.
        cargarCategorias();
    }
    
    // Método privado que obtiene todas las categorías de producto desde la base de datos
    // y las añade al comboBox de la interfaz gráfica.
    private void cargarCategorias() {
        try {
            // Obtiene la lista completa de categorías.
            List<ProductCategory> categorias = productCategoryDAO.findAll();
            
            // Recorre la lista y añade cada categoría al comboBox.
            for (ProductCategory categoria : categorias) {
                ventana.lamina.getComboCategorias().addItem(categoria);
            }
        } catch (DAOException e) {
            // Si ocurre un error al cargar las categorías, se muestra un mensaje al usuario.
            JOptionPane.showMessageDialog(ventana, "Error al cargar categorías");
        }
    }
    
    // Método de negocio que se encarga de aplicar un descuento a todos los productos
    // pertenecientes a la categoría seleccionada por el usuario.
    public void aplicarDescuentoCategoria() {

        //La categoría seleccionada, la guardamos en la variable categoría de tipo ProductCategory.
        ProductCategory categoria = (ProductCategory) ventana.lamina.getComboCategorias().getSelectedItem();

        // La Ventana llama a la Lámina, que a su vez la Lámina llama a su método 
        // para obtener el texto del JTextField borrando los posibles espacios en blancos que se hayan añadido.
        String texto = ventana.lamina.getTxtDescuento().getText().trim();

        // Validamos que la categoría no sea nula o esté vacía.
        if (categoria == null) {
            JOptionPane.showMessageDialog(ventana, "Debe seleccionar categoría");
            return;
        }

        // Validamos que el texto no sea nulo o vacío.
        if (texto == null || texto.isEmpty()) {
            JOptionPane.showMessageDialog(ventana, "Debe escribir un porcentaje");
            return;
        }

        // Si lo que se introduce en el JTextField no es un número va a saltar un aviso en la ventana para informar al usuario.
        if (!isNumber(texto)) {
            JOptionPane.showMessageDialog(ventana, "Porcentaje invalido, debe ser en formato numérico");
            return;
        }

        // Introducimos el texto a la variable porcentaje de tipo doble.
        double porcentaje = Double.parseDouble(texto);

        // Validamos que ese porcentaje esté comprendido entre 1 y 100.
        if (porcentaje < 1 || porcentaje > 100) {
            JOptionPane.showMessageDialog(ventana, "El descuento debe estar comprendido entre 1 y 100");
            return;
        }
        
        try {
            // Usando el método de aplicarDescuentoCategoria de la tabla productDAO, 
            // le pasamos por los parámetros de entrada el ID de la categoría seleccionada y el porcentaje que haya introducido el usuario.
            // Y va a devolver el nº de filas como productos hayan sido modificados mostrados en ventana al usuario.
            int filas = productDAO.aplicarDescuentoCategoria(categoria.getCategoryId(), porcentaje);
            
            // Muestra un mensaje indicando cuántos productos han sido actualizados.
            JOptionPane.showMessageDialog(ventana, "Descuento aplicado correctamente a " + filas + " productos");
        } catch (DAOException e) {
            // Si ocurre un error al aplicar el descuento, se informa al usuario.
            JOptionPane.showMessageDialog(ventana, "Error al aplicar descuento a los productos de la categoría seleccionada");
        }
    }

    // Método que se va a usar para saber si el texto que introduce por el JtextField es número o es una cadena de texto.
    private boolean isNumber(String cadena) {
        try {
            double numero = Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
