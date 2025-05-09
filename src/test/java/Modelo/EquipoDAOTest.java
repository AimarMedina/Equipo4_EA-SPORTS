package Modelo;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Clase de pruebas unitarias para la clase EquipoDAO.
 * Esta clase utiliza Mockito para simular las interacciones con la base de datos
 * y verificar el comportamiento de los métodos de EquipoDAO en diferentes escenarios.
 */
public class EquipoDAOTest {
    /**
     * Verifica que el método hayMasDeDosEquipos() retorne true cuando hay más de dos equipos en la base de datos.
     * Simula una consulta SQL que devuelve un total de 3 equipos.
     *
     * @throws SQLException Si ocurre un error al simular la interacción con la base de datos.
     */
    @Test
    void hayMasDeDosEquipos_returnsTrueWhenMoreThanTwoTeamsExist() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement("SELECT COUNT(*) AS total FROM equipos")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("total")).thenReturn(3);

        BaseDatos.setCon(mockConnection);

        assertTrue(EquipoDAO.hayMasDeDosEquipos());
    }
    /**
     * Verifica que el método hayCantidadParDeEquipos() retorne true cuando la cantidad de equipos es par.
     * Simula una consulta SQL que devuelve un total de 4 equipos.
     *
     * @throws Exception Si ocurre un error al simular la interacción con la base de datos.
     */
    @Test
    void testCantidadParDeEquipos() throws Exception {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStmt = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        when(BaseDatos.getCon()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true);
        when(mockRs.getInt("total")).thenReturn(4); // par

        boolean resultado = EquipoDAO.hayCantidadParDeEquipos();
        assertTrue(resultado);
    }
    /**
     * Verifica que el método hayCantidadParDeEquipos() retorne false cuando la cantidad de equipos es impar.
     * Simula una consulta SQL que devuelve un total de 5 equipos.
     *
     * @throws Exception Si ocurre un error al simular la interacción con la base de datos.
     */
    @Test
    void testCantidadImparDeEquipos() throws Exception {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStmt = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        when(BaseDatos.getCon()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStmt);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true);
        when(mockRs.getInt("total")).thenReturn(5); // impar

        boolean resultado = EquipoDAO.hayCantidadParDeEquipos();
        assertFalse(resultado);
    }
    /**
     * Verifica que el método hayCantidadParDeEquipos() retorne false cuando ocurre un error en la conexión a la base de datos.
     * Simula una excepción lanzada al intentar obtener la conexión.
     *
     * @throws Exception Si ocurre un error al simular la interacción con la base de datos.
     */
    @Test
    void testErrorEnBaseDeDatos() throws Exception {
        when(BaseDatos.getCon()).thenThrow(new RuntimeException("Fallo en la conexión"));

        boolean resultado = EquipoDAO.hayCantidadParDeEquipos();
        assertFalse(resultado); // porque captura la excepción y retorna false
    }
}