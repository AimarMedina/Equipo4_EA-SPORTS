package Excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta registrar un equipo que ya existe.
 */
public class EquipoExistenteException extends Exception {

    /**
     * Constructor predeterminado de la excepción.
     */
    public EquipoExistenteException() {
        super();
    }

    /**
     * Constructor de la excepción que recibe un mensaje descriptivo.
     *
     * @param mensaje Mensaje que describe el motivo de la excepción.
     */
    public EquipoExistenteException(String mensaje) {
        super(mensaje);
    }
}
