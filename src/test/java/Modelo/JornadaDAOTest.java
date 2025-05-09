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
 * Clase de pruebas unitarias para la clase JornadaDAO.
 * Esta clase utiliza Mockito para simular las interacciones con la base de datos
 * y verificar el comportamiento de los métodos de JornadaDAO en diferentes escenarios.
 */
public class JornadaDAOTest {
    /**
     * Verifica que el método existeJornada() retorne true cuando la jornada existe en la base de datos.
     * Simula una consulta SQL que devuelve un resultado indicando que la jornada existe.
     *
     * @throws Exception Si ocurre un error al simular la interacción con la base de datos.
     */
    @Test
    void existeJornada_returnsTrueWhenJornadaExists() throws Exception {
        ResultSet mockRs = mock(ResultSet.class);
        when(mockRs.next()).thenReturn(true);
        when(mockRs.getInt(1)).thenReturn(1);

        PreparedStatement mockStmt = mock(PreparedStatement.class);
        when(mockStmt.executeQuery()).thenReturn(mockRs);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStmt);

        when(BaseDatos.getCon()).thenReturn(mockConnection);

        assertTrue(JornadaDAO.existeJornada("1"));
    }
    /**
     * Verifica que el método existeJornada() retorne false cuando la jornada no existe en la base de datos.
     * Simula una consulta SQL que devuelve un resultado indicando que la jornada no existe.
     *
     * @throws Exception Si ocurre un error al simular la interacción con la base de datos.
     */
    @Test
    void existeJornada_returnsFalseWhenJornadaDoesNotExist() throws Exception {
        ResultSet mockRs = mock(ResultSet.class);
        when(mockRs.next()).thenReturn(true);
        when(mockRs.getInt(1)).thenReturn(0);

        PreparedStatement mockStmt = mock(PreparedStatement.class);
        when(mockStmt.executeQuery()).thenReturn(mockRs);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStmt);

        when(BaseDatos.getCon()).thenReturn(mockConnection);

        assertFalse(JornadaDAO.existeJornada("2"));
    }
    /**
     * Verifica que el método existeJornada() retorne false cuando ocurre una SQLException
     * al intentar realizar la consulta en la base de datos.
     *
     * @throws Exception Si ocurre un error al simular la interacción con la base de datos.
     */
    @Test
    void existeJornada_returnsFalseWhenSQLExceptionOccurs() throws Exception {
        Connection mockConnection = mock(Connection.class);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException());

        when(BaseDatos.getCon()).thenReturn(mockConnection);

        assertFalse(JornadaDAO.existeJornada("3"));
    }
}