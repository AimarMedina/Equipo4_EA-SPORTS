package Excepciones;
/**
 * Excepción personalizada que se lanza cuando un campo obligatorio no ha sido completado.
 */
public class CampoObligatorioException extends Exception {
    /**
     * Constructor de la excepción que recibe un mensaje descriptivo.
     *
     * @param mensaje Mensaje que describe el motivo de la excepción.
     */
    public CampoObligatorioException(String mensaje) {
        super(mensaje);
    }
}
