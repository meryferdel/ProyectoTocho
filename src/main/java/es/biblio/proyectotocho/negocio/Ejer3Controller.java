package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.persistencia.*;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import es.biblio.proyectotocho.presentacion.ejercicio3.LaminaEjer3;
import es.biblio.proyectotocho.presentacion.ejercicio3.VentanaEjer3;

import javax.swing.*;
import java.util.List;

public class Ejer3Controller {

    public VentanaEjer3 ventana;

    private RegionDAO regionDAO;
    private CountryDAO countryDAO;
    private LocationDAO locationDAO;
    private WarehouseDAO warehouseDAO;

    public Ejer3Controller() {
        regionDAO = new RegionDAO();
        countryDAO = new CountryDAO();
        locationDAO = new LocationDAO();
        warehouseDAO = new WarehouseDAO();
        
        ventana = new VentanaEjer3(this);
        
        cargarRegiones();
    }

    public void cargarRegiones() {

        try {

            List<Region> regiones = regionDAO.findAll();

            for (Region region : regiones) {

                ventana.getLamina()
                        .getComboRegiones()
                        .addItem(region);
            }

        } catch (DAOException e) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al cargar regiones"
            );
        }
    }

    public void cargarPaises(Region region) {

        try {
            java.util.List<Country> paises
                    = countryDAO.findByRegion(
                            region.getRegionId());

            for (Country country : paises) {
                ventana.getLamina()
                        .getComboPaises()
                        .addItem(country);
            }

            ventana.getLamina()
                    .getComboPaises()
                    .setEnabled(true);

        } catch (DAOException e) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al cargar países"
            );
        }
    }

    public void cargarUbicaciones(Country pais) {

        try {
            java.util.List<Location> locations
                    = locationDAO.findByCountry(
                            pais.getCountryId()
                    );

            for (Location location : locations) {
                ventana.getLamina().getComboUbicaciones().addItem(location);
            }

            ventana.getLamina().getComboUbicaciones().setEnabled(true);

        } catch (DAOException e) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al cargar ubicaciones"
            );
        }
    }

    public void crearAlmacen(String nombre, Location ubicacion) {

        try {
            // CREAR ALMACÉN
            Warehouse warehouse = new Warehouse();

            warehouse.setWarehouseName(nombre);

            warehouse.setLocationId(
                    ubicacion.getLocationId()
            );

            warehouseDAO.insert(warehouse);

            UtilidadesVista.mostrarExitoMensaje(ventana, "Almacen creado correctamente");

            // LIMPIAR
            ventana.getLamina().getTxtNombre().setText("");

        } catch (DAOException ex) {

            JOptionPane.showMessageDialog(
                    ventana,
                    "Error al crear almacén"
            );

            System.out.println(ex.getMessage());
        }

    }

}
