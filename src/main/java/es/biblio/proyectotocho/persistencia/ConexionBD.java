package es.biblio.proyectotocho.persistencia;

import java.sql.*;

public final class ConexionBD {

    /**
     * URL de conexión a la base de datos MariaDB.
     * Incluye el host, el puerto y el nombre de la base de datos.
     */
    private static final String URL = "jdbc:mariadb://localhost:3306/company_db";

    /**
     * Usuario de la base de datos.
     */
    private static final String USUARIO = "company";

    /**
     * Contraseña del usuario de la base de datos.
     */
    private static final String CONTRASENA = "company";

    /**
     * Constructor privado para evitar la instanciación de la clase.
     * <p>
     * Esta clase está pensada para ser utilizada únicamente a través de
     * métodos estáticos.
     * </p>
     */
    private ConexionBD() {
        // Evitar instanciación
    }

    /**
     * Obtiene una nueva conexión a la base de datos.
     *
     * @return un objeto {@link Connection} activo contra la base de datos
     *         configurada.
     * @throws SQLException si ocurre algún error al intentar establecer
     *                      la conexión con la base de datos.
     */
    public static Connection getConnection() throws SQLException {
        // Se delega en DriverManager la creación de la conexión.
        // El driver de MariaDB debe estar incluido como dependencia en el pom.xml.
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
