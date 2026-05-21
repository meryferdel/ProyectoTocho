package es.biblio.proyectotocho.persistencia;

import es.biblio.proyectotocho.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Product.
 * Implementa las operaciones CRUD sobre la tabla products.
 */
public class ProductDAO {

    private static final String SQL_INSERT =
            "INSERT INTO products (product_name, description, standard_cost, list_price, category_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE products SET product_name = ?, description = ?, standard_cost = ?, " +
            "list_price = ?, category_id = ? WHERE product_id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM products WHERE product_id = ?";

    private static final String SQL_FIND_BY_ID =
            "SELECT product_id, product_name, description, standard_cost, list_price, category_id " +
            "FROM products WHERE product_id = ?";

    private static final String SQL_FIND_ALL =
            "SELECT product_id, product_name, description, standard_cost, list_price, category_id " +
            "FROM products";

    public void insert(Product product) throws DAOException {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getStandardCost());
            ps.setDouble(4, product.getListPrice());
            ps.setInt(5, product.getCategoryId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al insertar el producto.", e);
        }
    }

    public void update(Product product) throws DAOException {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getStandardCost());
            ps.setDouble(4, product.getListPrice());
            ps.setInt(5, product.getCategoryId());
            ps.setInt(6, product.getProductId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al actualizar el producto.", e);
        }
    }

    public void delete(int productId) throws DAOException {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {

            ps.setInt(1, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al eliminar el producto.", e);
        }
    }

    public Product findById(int productId) throws DAOException {
        Product product = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setProductName(rs.getString("product_name"));
                    product.setDescription(rs.getString("description"));
                    product.setStandardCost(rs.getDouble("standard_cost"));
                    product.setListPrice(rs.getDouble("list_price"));
                    product.setCategoryId(rs.getInt("category_id"));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al buscar el producto por ID.", e);
        }

        return product;
    }

    public List<Product> findAll() throws DAOException {
        List<Product> products = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                product.setStandardCost(rs.getDouble("standard_cost"));
                product.setListPrice(rs.getDouble("list_price"));
                product.setCategoryId(rs.getInt("category_id"));
                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("Error al listar todos los productos.", e);
        }

        return products;
    }
}

