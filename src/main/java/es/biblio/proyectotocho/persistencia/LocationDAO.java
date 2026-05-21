package es.biblio.proyectotocho.persistencia;

import es.biblio.proyectotocho.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Location. Implementa las operaciones CRUD sobre la tabla
 * locations.
 */
public class LocationDAO {

    private static final String SQL_INSERT
            = "INSERT INTO locations (address, postal_code, city, state, country_id) "
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE
            = "UPDATE locations SET address = ?, postal_code = ?, city = ?, state = ?, country_id = ? "
            + "WHERE location_id = ?";

    private static final String SQL_DELETE
            = "DELETE FROM locations WHERE location_id = ?";

    private static final String SQL_FIND_BY_ID
            = "SELECT location_id, address, postal_code, city, state, country_id "
            + "FROM locations WHERE location_id = ?";

    private static final String SQL_FIND_ALL
            = "SELECT location_id, address, postal_code, city, state, country_id FROM locations";

    public void insert(Location location) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setString(1, location.getAddress());
            ps.setString(2, location.getPostalCode());
            ps.setString(3, location.getCity());
            ps.setString(4, location.getState());
            ps.setString(5, location.getCountryId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al insertar la localización.", e);
        }
    }

    public void update(Location location) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, location.getAddress());
            ps.setString(2, location.getPostalCode());
            ps.setString(3, location.getCity());
            ps.setString(4, location.getState());
            ps.setString(5, location.getCountryId());
            ps.setInt(6, location.getLocationId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al actualizar la localización.", e);
        }
    }

    public void delete(int locationId) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, locationId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al eliminar la localización.", e);
        }
    }

    public Location findById(int locationId) throws DAOException {
        Location location = null;

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, locationId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    location = new Location();
                    location.setLocationId(rs.getInt("location_id"));
                    location.setAddress(rs.getString("address"));
                    location.setPostalCode(rs.getString("postal_code"));
                    location.setCity(rs.getString("city"));
                    location.setState(rs.getString("state"));
                    location.setCountryId(rs.getString("country_id"));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al buscar la localización por ID.", e);
        }

        return location;
    }

    public List<Location> findAll() throws DAOException {
        List<Location> locations = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Location location = new Location();
                location.setLocationId(rs.getInt("location_id"));
                location.setAddress(rs.getString("address"));
                location.setPostalCode(rs.getString("postal_code"));
                location.setCity(rs.getString("city"));
                location.setState(rs.getString("state"));
                location.setCountryId(rs.getString("country_id"));
                locations.add(location);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al listar todas las localizaciones.", e);
        }

        return locations;
    }

    public List<Location> findByCountry(String countryId) throws DAOException {
        List<Location> locations = new ArrayList<>();

        String sql = "SELECT location_id, address, postal_code, city, state, country_id "
                + "FROM locations WHERE country_id = ?";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, countryId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Location location = new Location();
                    location.setLocationId(rs.getInt("location_id"));
                    location.setAddress(rs.getString("address"));
                    location.setPostalCode(rs.getString("postal_code"));
                    location.setCity(rs.getString("city"));
                    location.setState(rs.getString("state"));
                    location.setCountryId(rs.getString("country_id"));
                    locations.add(location);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al buscar localizaciones por país.", e);
        }

        return locations;
    }

    
}
