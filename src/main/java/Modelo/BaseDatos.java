package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase BaseDatos que gestiona la conexión con una base de datos Oracle.
 * Proporciona métodos para abrir, cerrar y obtener la conexión.
 */
public class BaseDatos
{
    //Abrir y cerrar la conexión con la base de datos.

    static Connection con;

    /**
     * Abre la conexión con la base de datos Oracle.
     *
     * @throws Exception Si ocurre un error al cargar el driver o al establecer la conexión.
     */
    public static void abrirConexion() throws Exception
    {
        Class.forName("oracle.jdbc.OracleDriver");
        String user = "eqdaw04"; //poner user y contra de la bd de oracle
        String password = "eqdaw04";
        String url ="jdbc:oracle:thin:@SrvOracle:1521:orcl";
        con = DriverManager.getConnection(url, user, password);
    }

    /**
     * Obtiene la conexión actual con la base de datos.
     *
     * @return La conexión activa con la base de datos.
     * @throws Exception Si la conexión no está inicializada.
     */
    public static Connection getCon() throws Exception
    {
        return con;
    }

    /**
     * Cierra la conexión con la base de datos.
     *
     * @throws Exception Si ocurre un error al cerrar la conexión.
     */
    public static void cerrarConexion() throws Exception
    {
        con.close();
    }
}