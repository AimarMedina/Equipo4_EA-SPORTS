package Excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta registrar un nombre que ya existe.
 */
public class NombreDuplicadoExcepcion extends Exception {

    /**
     * Constructor de la excepción que recibe un mensaje descriptivo.
     *
     * @param mensaje Mensaje que describe el motivo de la excepción.
     */
    public NombreDuplicadoExcepcion(String mensaje) {
        super(mensaje);
    }
}
