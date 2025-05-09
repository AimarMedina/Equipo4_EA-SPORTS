package Modelo;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Clase de pruebas unitarias para la clase JugadorDAO.
 * Esta clase utiliza Mockito para simular las interacciones con la base de datos
 * y verificar el comportamiento del método equiposConCantidadValidaDeJugadores()
 * en diferentes escenarios.
 */
public class JugadorDAOTest {

    /**
     * Verifica que el método equiposConCantidadValidaDeJugadores() retorne true
     * cuando todos los equipos tienen una cantidad válida de jugadores (entre 2 y 6).
     *
     * @throws Exception Si ocurre un error al simular la interacción con la base de datos.
     */
    @Test
    void equiposConCantidadValidaDeJugadores_returnsTrueWhenValid() throws Exception {
        ResultSet mockRs = mock(ResultSet.class);
        when(mockRs.next()).thenReturn(true, false);
        when(mockRs.getInt("cantidad")).thenReturn(4);

        PreparedStatement mockStmt = mock(PreparedStatement.class);
        when(mockStmt.executeQuery()).thenReturn(mockRs);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStmt);

        when(BaseDatos.getCon()).thenReturn(mockConnection);

        assertTrue(JugadorDAO.equiposConCantidadValidaDeJugadores());
    }
    /**
     * Verifica que el método equiposConCantidadValidaDeJugadores() retorne false
     * cuando al menos un equipo tiene una cantidad inválida de jugadores (menos de 2 o más de 6).
     *
     * @throws Exception Si ocurre un error al simular la interacción con la base de datos.
     */
    @Test
    void equiposConCantidadValidaDeJugadores_returnsFalseWhenInvalid() throws Exception {
        ResultSet mockRs = mock(ResultSet.class);
        when(mockRs.next()).thenReturn(true, false);
        when(mockRs.getInt("cantidad")).thenReturn(7);

        PreparedStatement mockStmt = mock(PreparedStatement.class);
        when(mockStmt.executeQuery()).thenReturn(mockRs);

        Connection mockConnection = mock(Connection.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStmt);

        when(BaseDatos.getCon()).thenReturn(mockConnection);

        assertFalse(JugadorDAO.equiposConCantidadValidaDeJugadores());
    }
}