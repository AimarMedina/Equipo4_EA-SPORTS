--Script para probar Procedimientos--
SET SERVEROUTPUT ON;

/*Procedimiento informe_equipos_competicion:*/

    /*En Java: Se emplea en EquipoDAO para generar un informe de los equipos que participan en una competición específica.*/

-- Este procedimiento recibe un ID de competici�n y devuelve un cursor con la informaci�n de los equipos
DECLARE
    v_cursor SYS_REFCURSOR;
    v_nombre_equipo equipos.nombre%TYPE;
    v_fecha_fundacion equipos.fechaFund%TYPE;
    v_cantidad_jugadores NUMBER;
    v_sueldo_maximo NUMBER;
    v_sueldo_minimo NUMBER;
    v_sueldo_promedio NUMBER;
BEGIN
    -- Llamar al procedimiento con un ID de competici�n, por ejemplo el 1
    informe_equipos_competicion(1, v_cursor);

    -- Recorremos el cursor
    LOOP
        FETCH v_cursor INTO 
            v_nombre_equipo, 
            v_fecha_fundacion, 
            v_cantidad_jugadores, 
            v_sueldo_maximo, 
            v_sueldo_minimo, 
            v_sueldo_promedio;
        EXIT WHEN v_cursor%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('Equipo: ' || v_nombre_equipo);
        DBMS_OUTPUT.PUT_LINE('Fecha Fundaci�n: ' || TO_CHAR(v_fecha_fundacion, 'DD/MM/YYYY'));
        DBMS_OUTPUT.PUT_LINE('Cantidad Jugadores: ' || v_cantidad_jugadores);
        DBMS_OUTPUT.PUT_LINE('Sueldo M�ximo: ' || v_sueldo_maximo);
        DBMS_OUTPUT.PUT_LINE('Sueldo M�nimo: ' || v_sueldo_minimo);
        DBMS_OUTPUT.PUT_LINE('Sueldo Promedio: ' || v_sueldo_promedio);
        DBMS_OUTPUT.PUT_LINE('-----------------------------');
    END LOOP;

    CLOSE v_cursor;
END;

/*Procedimiento obtener_jugadores_equipo*/

    /*En Java: Se emplea en JugadorDAO para generar un informe de los jugadores que pertenecen a un equipo específico.*/

-- Este procedimiento recibe el nombre de un equipo y devuelve un cursor con la informaci�n de los jugadores
DECLARE
    v_cursor SYS_REFCURSOR;
    v_nombre jugadores.nombre%TYPE;
    v_apellido jugadores.apellido%TYPE;
    v_rol jugadores.rol%TYPE;
    v_sueldo jugadores.sueldo%TYPE;
BEGIN
    -- Llamar al procedimiento con el nombre del equipo
    obtener_jugadores_equipo('NombreDelEquipo', v_cursor);

    -- Recorremos el cursor
    LOOP
        FETCH v_cursor INTO v_nombre, v_apellido, v_rol, v_sueldo;
        EXIT WHEN v_cursor%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_nombre);
        DBMS_OUTPUT.PUT_LINE('Apellido: ' || v_apellido);
        DBMS_OUTPUT.PUT_LINE('Rol: ' || v_rol);
        DBMS_OUTPUT.PUT_LINE('Sueldo: ' || v_sueldo);
        DBMS_OUTPUT.PUT_LINE('-----------------------------');
    END LOOP;

    CLOSE v_cursor;
END;