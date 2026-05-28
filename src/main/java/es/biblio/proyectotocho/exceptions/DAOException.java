// Paquete donde se agrupan las clases relacionadas con excepciones del proyecto.
package es.biblio.proyectotocho.exceptions;

// DAOException es una excepción personalizada que extiene de Exception,
// que se usará para representar errores específicos de la capa de acceso a datos (DAO):
public class DAOException extends Exception {

    // Constructor vacío que permite lanzar la excepción sin ningún mensaje:
    public DAOException() {
    }

    // Constructor que recibe un mensaje descriptivo del error por el parámetro de entrada y
    // llama al contructor de la clase padre (Exception) con ese mensaje:
    public DAOException(String message) {
        super(message);
    }

    // Constructor que recibe un mensaje y una causa (otra excepción),
    // es útil para encadenar excepciones y no perder el origen del error:
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor que solo recibe la causa, que se usa cuando no necesitamos un mensaje propio,
    // solo propagar la excepción original:
    public DAOException(Throwable cause) {
        super(cause);
    }

    // Constructor más completo:
    // - message: mensaje personalizado de error.
    // - cause: excepción original que causó el error.
    // - enableSuppression: permite o no suprimir la excepción, aunque no se suele usar nunca.
    // - writableStackTrace: indica si se debe generar el stack trace. Este último es como el camino (métodos) que siguió el programa hasta llegar al error.
    // Normalmente se usa en casos muy específicos donde se quiere controlar
    // el comportamiento interno de la excepción.
    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
