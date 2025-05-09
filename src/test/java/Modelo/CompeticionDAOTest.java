package Modelo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Clase de prueba para la clase CompeticionDAO.
 * Esta clase utiliza Mockito para simular las interacciones con la base de datos
 * y verificar el comportamiento de los métodos de CompeticionDAO.
 */
public class CompeticionDAOTest {

    private static Connection mockConnection;
    private static PreparedStatement mockPreparedStatement;
    private static ResultSet mockResultSet;
    /**
     * Método de configuración inicial que se ejecuta antes de todas las pruebas.
     * Inicializa los objetos simulados (mocks) necesarios para las pruebas.
     */
   @BeforeAll
   static void inicio(){
       mockConnection = mock(Connection.class);
       mockPreparedStatement = mock(PreparedStatement.class);
       mockResultSet = mock(ResultSet.class);
   }
    /**
     * Prueba para verificar que el método verificarCompeticionCreada()
     * retorna un valor mayor a 0 cuando existe al menos una competición en la base de datos.
     *
     * @throws SQLException si ocurre un error al interactuar con los objetos simulados.
     */
    @Test
    void verificarCompeticionCreada_simpleTest() throws SQLException {
        when(mockConnection.prepareStatement("SELECT COUNT(*) cant FROM competiciones")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("cant")).thenReturn(1);

        int result = CompeticionDAO.verificarCompeticionCreada();

        assertTrue(result > 0, "La competición debería existir.");
    }
    /**
     * Método de limpieza que se ejecuta después de todas las pruebas.
     */
    @AfterAll
    static void finalizar(){
       System.out.println("Finalizando pruebas de CompeticionDAOTest");
    }



}