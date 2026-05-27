package es.biblio.proyectotocho.persistencia;

import es.biblio.proyectotocho.Excepciones.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Country. Implementa las operaciones CRUD sobre la tabla
 * countries.
 */
public class CountryDAO {

    private static final String SQL_INSERT
            = "INSERT INTO countries (country_id, country_name, region_id) VALUES (?, ?, ?)";

    private static final String SQL_UPDATE
            = "UPDATE countries SET country_name = ?, region_id = ? WHERE country_id = ?";

    private static final String SQL_DELETE
            = "DELETE FROM countries WHERE country_id = ?";

    private static final String SQL_FIND_BY_ID
            = "SELECT country_id, country_name, region_id FROM countries WHERE country_id = ?";

    private static final String SQL_FIND_ALL
            = "SELECT country_id, country_name, region_id FROM countries";
    
    private static final String SQL_FIND_BY_REGION
            = "SELECT country_id, country_name, region_id FROM countries WHERE region_id = ?";

    public void insert(Country country) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setString(1, country.getCountryId());
            ps.setString(2, country.getCountryName());
            ps.setInt(3, country.getRegionId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al insertar el país.", e);
        }
    }

    public void update(Country country) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, country.getCountryName());
            ps.setInt(2, country.getRegionId());
            ps.setString(3, country.getCountryId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al actualizar el país.", e);
        }
    }

    public void delete(String countryId) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setString(1, countryId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al eliminar el país.", e);
        }
    }

    public Country findById(String countryId) throws DAOException {
        Country country = null;

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setString(1, countryId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    country = new Country();
                    country.setCountryId(rs.getString("country_id"));
                    country.setCountryName(rs.getString("country_name"));
                    country.setRegionId(rs.getInt("region_id"));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al buscar el país por ID.", e);
        }

        return country;
    }

    public List<Country> findAll() throws DAOException {
        List<Country> countries = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Country country = new Country();
                country.setCountryId(rs.getString("country_id"));
                country.setCountryName(rs.getString("country_name"));
                country.setRegionId(rs.getInt("region_id"));
                countries.add(country);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al listar todos los países.", e);
        }

        return countries;
    }

    public List<Country> findByRegion(int regionId) throws DAOException {
        List<Country> paises = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_REGION)) {

            ps.setInt(1, regionId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Country country = new Country();
                    country.setCountryId(rs.getString("country_id"));
                    country.setCountryName(rs.getString("country_name"));
                    country.setRegionId(rs.getInt("region_id"));
                    paises.add(country);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al buscar países por región.", e);
        }

        return paises;
    }

}
