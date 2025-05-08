package Excepciones;

/**
 * Excepción personalizada que se lanza cuando se proporciona una fecha inválida.
 */
public class FechaInvalidaException extends Exception {

    /**
     * Constructor predeterminado de la excepción.
     */
    public FechaInvalidaException() {
        super();
    }

    /**
     * Constructor de la excepción que recibe un mensaje descriptivo.
     *
     * @param mensaje Mensaje que describe el motivo de la excepción.
     */
    public FechaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
