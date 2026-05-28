// Paquete de la capa de negocio (lógica) del proyecto.
package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.persistencia.*;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import es.biblio.proyectotocho.presentacion.ejercicio3.LaminaEjer3;
import es.biblio.proyectotocho.presentacion.ejercicio3.VentanaEjer3;
import javax.swing.*;
import java.util.List;

// Controlador del ejercicio 3, que se encarga de coordinar la lógica entre la vista de la presentación (VentanaEj3) y el DAO (RegionDAO, CountryDAO, LocationDAO y WarehouseDAO).
public class Ejer3Controller {

    public VentanaEjer3 ventana; // Referencia a la ventana principal del ejercicio 3.
    // DAOs necesarios para acceder a regiones, países, ubicaciones y almacenes en la base de datos.
    private RegionDAO regionDAO;
    private CountryDAO countryDAO;
    private LocationDAO locationDAO;
    private WarehouseDAO warehouseDAO;

    // Contructor del controlador del ejercicio 3: 
    public Ejer3Controller() {
        // Crea la ventana del ejercicio 3 y le pasa este controlador,
        // para que la vista pueda llamar a métodos del controlador (por ejemplo, al pulsar un botón).
        ventana = new VentanaEjer3(this);

        // Inicializa los DAOs que se usarán para consultar e insertar datos.
        regionDAO = new RegionDAO();
        countryDAO = new CountryDAO();
        locationDAO = new LocationDAO();
        warehouseDAO = new WarehouseDAO();

        // Carga las regiones desde la base de datos y las muestra en el combo correspondiente.
        cargarRegiones();
    }

    // Método que carga todas las regiones y las añade al combo de regiones de la interfaz.
    public void cargarRegiones() {
        try {
            // Obtiene todas las regiones desde la base de datos y las guarda en la lista regiones.
            List<Region> regiones = regionDAO.findAll();

            // Recorre la lista de regiones y las añade al combo de la ventana.
            for (Region region : regiones) {
                ventana.getLamina()
                        .getComboRegiones()
                        .addItem(region);
            }

        } catch (DAOException e) {
            // Si ocurre un error al acceder a la base de datos, se muestra un mensaje al usuario.
            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al cargar regiones"
            );
        }
    }

    // Método que carga los países pertenecientes a una región seleccionada y los añade al combo de países.
    public void cargarPaises(Region region) {
        try {
            // Obtiene la lista de países filtrados por una región seleccionada.
            List<Country> paises = countryDAO.findByRegion(region.getRegionId());

            // Añade cada país al combo de países.
            for (Country country : paises) {
                ventana.getLamina()
                        .getComboPaises()
                        .addItem(country);
            }

            // Habilita o activa el componente del combo de países para que el usuario pueda seleccionarlos.
            ventana.getLamina()
                    .getComboPaises()
                    .setEnabled(true);

        } catch (DAOException e) {
            // Si ocurre un error al cargar los países, se informa al usuario.
            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al cargar países"
            );
        }
    }

    // Método que carga las ubicaciones pertenecientes a un país seleccionado y las añade al combo de ubicaciones.
    public void cargarUbicaciones(Country pais) {

        try {
            // Obtiene la lista de ubicaciones filtradas por el país seleccionado.
            List<Location> localizaciones = locationDAO.findByCountry(pais.getCountryId());

            // Añade cada ubicación al combo de ubicaciones.
            for (Location localizacion : localizaciones) {
                ventana.getLamina().getComboUbicaciones().addItem(localizacion);
            }
            
            // Habilita o activa el componente del combo de ubicaciones para que el usuario pueda seleccionarlas.
            ventana.getLamina().getComboUbicaciones().setEnabled(true);

        } catch (DAOException e) {
            // Si ocurre un error al cargar las ubicaciones, se informa al usuario.
            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al cargar ubicaciones"
            );
        }
    }

    // Método de negocio que se encarga de crear un nuevo almacén a partir del nombre y la ubicación seleccionada por el usuario.
    public void crearAlmacen(String nombre, Location ubicacion) {
        try {
            // Crea un nuevo objeto Warehouse (Almacen) vacío.
            Warehouse almacen = new Warehouse();

            // Asigna el nombre del almacén.
            almacen.setWarehouseName(nombre);

            // Asigna la ubicación usando el id de la localización seleccionada.
            almacen.setLocationId(ubicacion.getLocationId());

            // Inserta el nuevo almacén en la base de datos.
            warehouseDAO.insert(almacen);

            // Muestra un mensaje de éxito al usuario.
            UtilidadesVista.mostrarExitoMensaje(ventana, "Almacen creado correctamente");

            // Limpia el campo de texto del nombre en la interfaz gráfica.
            ventana.getLamina().getTxtNombre().setText("");

        } catch (DAOException ex) {
            // Si ocurre un error al crear el almacén en la base de datos, se informa al usuario por ventana.
            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al crear el almacén"
            );

            // Imprime el mensaje de error en la consola para facilitar la depuración.
            System.out.println(ex.getMessage());
        }
    }
}
