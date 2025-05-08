package Excepciones;

/**
 * Excepción personalizada que se lanza cuando no se selecciona una opción obligatoria.
 */
public class OpcionSeleccionarObligatorioException extends Exception {

    /**
     * Constructor predeterminado de la excepción.
     */
    public OpcionSeleccionarObligatorioException() {
        super();
    }

    /**
     * Constructor de la excepción que recibe un mensaje descriptivo.
     *
     * @param mensaje Mensaje que describe el motivo de la excepción.
     */
    public OpcionSeleccionarObligatorioException(String mensaje) {
        super(mensaje);
    }
}