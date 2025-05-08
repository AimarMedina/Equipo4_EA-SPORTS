package Excepciones;

/**
 * Excepción personalizada que se lanza cuando se selecciona una opción incorrecta.
 */
public class OpcionIncorrectaException extends Exception {

    /**
     * Constructor predeterminado de la excepción.
     */
    public OpcionIncorrectaException() {
        super();
    }

    /**
     * Constructor de la excepción que recibe un mensaje descriptivo.
     *
     * @param mensaje Mensaje que describe el motivo de la excepción.
     */
    public OpcionIncorrectaException(String mensaje) {
        super(mensaje);
    }
}