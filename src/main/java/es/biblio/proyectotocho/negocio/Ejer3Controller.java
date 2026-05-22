package es.biblio.proyectotocho.negocio;

import es.biblio.proyectotocho.exceptions.DAOException;
import es.biblio.proyectotocho.persistencia.*;
import es.biblio.proyectotocho.presentacion.UtilidadesVista;
import es.biblio.proyectotocho.presentacion.ejercicio3.VentanaEjer3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Ejer3Controller {

    public VentanaEjer3 ventana;

    private RegionDAO regionDAO;
    private CountryDAO countryDAO;
    private LocationDAO locationDAO;
    private WarehouseDAO warehouseDAO;

    public Ejer3Controller() {

        ventana = new VentanaEjer3(this);

        regionDAO = new RegionDAO();
        countryDAO = new CountryDAO();
        locationDAO = new LocationDAO();
        warehouseDAO = new WarehouseDAO();

        cargarRegiones();

        ventana.getLamina().getComboRegiones()
                .addActionListener(new EventoComboRegiones());

        ventana.getLamina().getComboPaises()
                .addActionListener(new EventoComboPaises());

        ventana.getLamina().getBtnCrear()
                .addActionListener(new EventoCrearAlmacen());
    }

    private void cargarRegiones() {

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

    // ==========================
    // EVENTO REGIONES
    // ==========================

    private class EventoComboRegiones implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                ventana.getLamina()
                        .getComboPaises()
                        .removeAllItems();

                Region region =
                        (Region) ventana.getLamina()
                                .getComboRegiones()
                                .getSelectedItem();

                if (region != null) {

                    List<Country> paises =
                            countryDAO.findByRegion(
                                    region.getRegionId()
                            );

                    for (Country country : paises) {

                        ventana.getLamina()
                                .getComboPaises()
                                .addItem(country);
                    }

                    ventana.getLamina()
                            .getComboPaises()
                            .setEnabled(true);
                }

            } catch (DAOException ex) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Error al cargar países"
                );
            }
        }
    }

    // ==========================
    // EVENTO PAÍSES
    // ==========================

    private class EventoComboPaises implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                ventana.getLamina()
                        .getComboUbicaciones()
                        .removeAllItems();

                Country country =
                        (Country) ventana.getLamina()
                                .getComboPaises()
                                .getSelectedItem();

                if (country != null) {

                    List<Location> locations =
                            locationDAO.findByCountry(
                                    country.getCountryId()
                            );

                    for (Location location : locations) {

                        ventana.getLamina()
                                .getComboUbicaciones()
                                .addItem(location);
                    }

                    ventana.getLamina()
                            .getComboUbicaciones()
                            .setEnabled(true);
                }

            } catch (DAOException ex) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Error al cargar ubicaciones"
                );
            }
        }
    }

    // ==========================
    // CREAR ALMACÉN
    // ==========================

    private class EventoCrearAlmacen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                String nombre =
                        ventana.getLamina()
                                .getTxtNombre()
                                .getText()
                                .trim();

                Region region =
                        (Region) ventana.getLamina()
                                .getComboRegiones()
                                .getSelectedItem();

                Country country =
                        (Country) ventana.getLamina()
                                .getComboPaises()
                                .getSelectedItem();

                Location location =
                        (Location) ventana.getLamina()
                                .getComboUbicaciones()
                                .getSelectedItem();

                // VALIDACIONES

                if (nombre.isEmpty()
                        || region == null
                        || country == null
                        || location == null) {

                    UtilidadesVista.mostrarVacioMensaje(ventana, "No puede haber un campo vacío");

                    return;
                }

                // CREAR ALMACÉN

                Warehouse warehouse = new Warehouse();

                warehouse.setWarehouseName(nombre);

                warehouse.setLocationId(
                        location.getLocationId()
                );

                warehouseDAO.insert(warehouse);

                UtilidadesVista.mostrarExitoMensaje(ventana, "Almacen creado correctamente");

                // LIMPIAR

                ventana.getLamina()
                        .getTxtNombre()
                        .setText("");

            } catch (DAOException ex) {

                JOptionPane.showMessageDialog(
                        ventana,
                        "Error al crear almacén"
                );

                System.out.println(ex.getMessage());
            }
        }
    }
}