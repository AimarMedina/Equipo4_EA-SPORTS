package Excepciones;

/**
 * Excepción personalizada que se lanza cuando el formato de un dato es incorrecto.
 */
public class FormatoIncorrectoException extends Exception {

    /**
     * Constructor de la excepción que recibe un mensaje descriptivo.
     *
     * @param mensaje Mensaje que describe el motivo de la excepción.
     */
    public FormatoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}