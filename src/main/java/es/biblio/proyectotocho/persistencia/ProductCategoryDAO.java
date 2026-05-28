package es.biblio.proyectotocho.persistencia;

import es.biblio.proyectotocho.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad ProductCategory. Implementa las operaciones CRUD sobre la
 * tabla product_categories.
 */
public class ProductCategoryDAO {

    private static final String SQL_INSERT
            = "INSERT INTO product_categories (category_name) VALUES (?)";

    private static final String SQL_UPDATE
            = "UPDATE product_categories SET category_name = ? WHERE category_id = ?";

    private static final String SQL_DELETE
            = "DELETE FROM product_categories WHERE category_id = ?";

    private static final String SQL_FIND_BY_ID
            = "SELECT category_id, category_name FROM product_categories WHERE category_id = ?";

    private static final String SQL_FIND_BY_NAME
            = "SELECT category_id, category_name FROM product_categories WHERE category_name = ?";

    private static final String SQL_FIND_ALL
            = "SELECT category_id, category_name FROM product_categories";

    public void insert(ProductCategory category) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setString(1, category.getCategoryName());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al insertar la categoría de producto.", e);
        }
    }

    public void update(ProductCategory category) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al actualizar la categoría de producto.", e);
        }
    }

    public void delete(int categoryId) throws DAOException {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, categoryId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al eliminar la categoría de producto.", e);
        }
    }

    public ProductCategory findById(int categoryId) throws DAOException {
        ProductCategory category = null;

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, categoryId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    category = new ProductCategory();
                    category.setCategoryId(rs.getInt("category_id"));
                    category.setCategoryName(rs.getString("category_name"));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al buscar la categoría de producto por ID.", e);
        }

        return category;
    }

    public ProductCategory findByName(String categoryName) throws DAOException {
        ProductCategory category = null;

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_NAME)) {

            ps.setString(1, categoryName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    category = new ProductCategory();
                    category.setCategoryId(rs.getInt("category_id"));
                    category.setCategoryName(rs.getString("category_name"));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al buscar la categoría de producto por nombre.", e);
        }

        return category;
    }

    public List<ProductCategory> findAll() throws DAOException {
        List<ProductCategory> categories = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductCategory category = new ProductCategory();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                categories.add(category);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al listar todas las categorías de producto.", e);
        }

        return categories;
    }
}
