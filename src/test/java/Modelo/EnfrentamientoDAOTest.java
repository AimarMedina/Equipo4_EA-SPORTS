package Modelo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Clase de prueba para la clase EnfrentamientoDAO.
 * Esta clase utiliza Mockito para simular las interacciones con la base de datos
 * y verificar el comportamiento de los métodos de EnfrentamientoDAO.
 */
public class EnfrentamientoDAOTest {
    // Mock de la conexión a la base de datos
    private static Connection mockConnection;
    /**
     * Configuración inicial que se ejecuta antes de todas las pruebas.
     * Inicializa el mock de la conexión a la base de datos.
     */
    @BeforeAll
    static void setUp() {
        mockConnection = mock(Connection.class);
    }

    /**
     * Prueba para verificar que el método crearEnfrentamiento() no lanza excepciones
     * cuando se proporcionan parámetros válidos.
     */
    @Test
    void crearEnfrentamiento_noThrowsWithValidParameters() {
        assertDoesNotThrow(() -> EnfrentamientoDAO.crearEnfrentamiento(LocalDate.now(), LocalDate.now(), "Equipo1", "Equipo2", 1));
    }



}