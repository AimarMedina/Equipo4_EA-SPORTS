CREATE OR REPLACE TRIGGER noMasUnaCompeticion
BEFORE INSERT ON competiciones
DECLARE
    v_numComp NUMBER;
    e_demasiadasComp exception;
BEGIN
    SELECT COUNT(*) INTO v_numComp
    FROM competiciones;
    
    IF v_numComp = 1 THEN
        RAISE e_demasiadasComp;
    END IF;
EXCEPTION
    WHEN e_demasiadasComp THEN
        RAISE_APPLICATION_ERROR(-20001,'Error: No se pueden crear más competiciones por que ya hay una en marcha.');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004,'Error desconocido: ' || SQLERRM);
END;


/*TRIGGER PARA COMPROBAR RESTRICCIONES AL CERRAR COMPETICION
  En el caso de que se quiera cerrar la competicion con 
    menos de 2 jugadores salta una excepcion y no deja continuar.
  En el caso de que se quiera cerrar la competicion 
    con un numero impar de equipos salta una excepcion y no deja continuar.
  En el caso de que se quiera cerrar la competicion con 
    menos de 2 jugadores por equipo salta una excepcion y no deja continuar
*/
CREATE OR REPLACE TRIGGER cerrarCompeticion
BEFORE UPDATE OF estado ON competiciones
FOR EACH ROW
DECLARE
    CURSOR c_jugadoresEquipo IS
        SELECT e.nombre, COUNT(j.idJugador) AS num_jugadores
        FROM equipos e
        LEFT JOIN jugadores j ON e.idEquipo = j.idEquipo
        GROUP BY e.nombre;

    v_numEquipos NUMBER;
    v_numJugadoresEquipo NUMBER;
    v_nombreEquipo equipos.nombre%type;
    e_minEquipos exception;
    e_numEquiposImpar exception;
    e_numJugadoresEquipo exception;
BEGIN

    IF :NEW.estado = 'cerrado' THEN
    
        SELECT COUNT(*) INTO v_numEquipos
        FROM equipos;
        
        IF v_numEquipos < 2 THEN
            RAISE e_minEquipos;
        END IF;
        
        IF MOD(v_numEquipos,2) != 0 THEN
            RAISE e_numEquiposImpar;
        END IF;
        
        OPEN c_jugadoresEquipo;
        LOOP
            FETCH c_jugadoresEquipo INTO v_nombreEquipo,v_numJugadoresEquipo;
            EXIT WHEN c_jugadoresEquipo%NOTFOUND;
            
            IF v_numJugadoresEquipo <2 THEN
                RAISE e_numJugadoresEquipo;
            END IF;    
            
        END LOOP;
        CLOSE c_jugadoresEquipo;
        
    END IF;
    
    
EXCEPTION
        WHEN e_minEquipos THEN
            RAISE_APPLICATION_ERROR(-20001,'Error: No se puede cerrar la competicion si hay menos de 2 equipos inscritos.');
        WHEN e_numEquiposImpar THEN
            RAISE_APPLICATION_ERROR(-20002,'Error: No se puede cerrar la competicion si hay un numero de equipos impares.');
        WHEN e_numJugadoresEquipo THEN
            RAISE_APPLICATION_ERROR(-20003,'Error: No se puede cerrar la competicion si hay menos de 2 jugadores por equipo. Error en el Equipo: ' || v_nombreEquipo);
        WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20004,'Error desconocido: ' || SQLERRM);
END;

/*TRIGGER PARA BLOQUEAR LA INSERCION, ACTUALIZACION y BORRADO en las tablas jugadores y equipos
  En el caso en que la competicion esta cerrada y el 
  usuario quiera insertar,modificar o borrar datos de 
  las tablas jugadores y equipos salta una excepcion y cancela la ejecucion. 
*/
CREATE OR REPLACE TRIGGER cerrarCompeticionJugadores
BEFORE INSERT OR UPDATE OR DELETE ON jugadores
FOR EACH ROW
DECLARE
    v_estadoCompeticion competiciones.estado%type;
    e_noSePuedeInsertar exception;
    e_noSePuedeUpdatear exception;
    e_noSePuedeDeletear exception;

BEGIN
    SELECT estado into v_estadoCompeticion
    FROM competiciones;
    
    IF v_estadoCompeticion = 'cerrado' THEN
        IF INSERTING THEN
            RAISE e_noSePuedeInsertar;
        ELSIF UPDATING THEN
            RAISE e_noSePuedeUpdatear;
        ELSIF DELETING THEN
            RAISE e_noSePuedeDeletear;
        END IF;
    END IF;
    
EXCEPTION
    WHEN e_noSePuedeInsertar THEN
        RAISE_APPLICATION_ERROR(-20001,'ERROR: No se puede insertar nuevos jugadores si la competicion esta cerrada.');
    WHEN e_noSePuedeUpdatear THEN
        RAISE_APPLICATION_ERROR(-20002,'ERROR: No se puede actualizar los datos de los jugadores si la competicion esta cerrada.');
    WHEN e_noSePuedeDeletear THEN
        RAISE_APPLICATION_ERROR(-20003,'ERROR: No se puede eliminar jugadores si la competicion esta cerrada.');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004,'Error desconocido: ' || SQLERRM);
END;

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER cerrarCompeticionEquipos
BEFORE INSERT OR UPDATE OR DELETE ON equipos
FOR EACH ROW
DECLARE
    v_estadoCompeticion competiciones.estado%type;
    e_noSePuedeInsertar exception;
    e_noSePuedeUpdatear exception;
    e_noSePuedeDeletear exception;

BEGIN
    SELECT estado into v_estadoCompeticion
    FROM competiciones;
    
    IF v_estadoCompeticion = 'cerrado' THEN
        IF INSERTING THEN
            RAISE e_noSePuedeInsertar;
        ELSIF UPDATING THEN
            RAISE e_noSePuedeUpdatear;
        ELSIF DELETING THEN
            RAISE e_noSePuedeDeletear;
        END IF;
    END IF;
    
EXCEPTION
    WHEN e_noSePuedeInsertar THEN
        RAISE_APPLICATION_ERROR(-20001,'ERROR: No se puede insertar nuevos equipos si la competicion esta cerrada.');
    WHEN e_noSePuedeUpdatear THEN
        RAISE_APPLICATION_ERROR(-20002,'ERROR: No se puede actualizar los datos de los equipos si la competicion esta cerrada.');
    WHEN e_noSePuedeDeletear THEN
        RAISE_APPLICATION_ERROR(-20003,'ERROR: No se puede eliminar equipos si la competicion esta cerrada.');
     WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004,'Error desconocido: ' || SQLERRM);
END;

