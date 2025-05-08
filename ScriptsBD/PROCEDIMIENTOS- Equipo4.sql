
--PROCEDIMIENTOS--
CREATE OR REPLACE PROCEDURE informe_equipos_competicion(
    p_idComp IN NUMBER,
    p_cursor OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_cursor FOR
        SELECT e.nombre AS nombre_equipo,e.fechaFund AS fecha_fundacion,
            COUNT(j.idJugador) AS cantidad_jugadores,
            MAX(j.sueldo) AS sueldo_maximo,
            MIN(j.sueldo) AS sueldo_minimo,
            ROUND(AVG(j.sueldo), 2) AS sueldo_promedio
        FROM equipos e JOIN jugadores j ON e.idEquipo = j.idEquipo
        WHERE 
            e.idEquipo IN (
                SELECT DISTINCT er.idEquipo
                FROM equipoRoles er
                WHERE er.idEquipo IN (
                    SELECT DISTINCT j.idEquipo
                    FROM jornadas jo
                    JOIN enfrentamientos en ON jo.idJor = en.idJornada
                    WHERE jo.idComp = p_idComp
                )
            )
        GROUP BY 
            e.nombre, e.fechaFund;
EXCEPTION
    WHEN OTHERS THEN
        RAISE;  -- Dejar que Java maneje otras excepciones
END;

CREATE OR REPLACE PROCEDURE obtener_jugadores_equipo (
    p_nombre_equipo IN VARCHAR2,
    p_resultado OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN p_resultado FOR
        SELECT j.nombre,j.apellido,j.rol,j.sueldo
        FROM jugadores j
        JOIN equipos e ON j.idEquipo = e.idEquipo
        WHERE UPPER(e.nombre) = UPPER(p_nombre_equipo);

EXCEPTION
    WHEN OTHERS THEN
        RAISE; -- Dejar que Java maneje otras excepciones
END;


