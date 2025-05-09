--EQUIPO 4

-- TRIGGER COMPETICION
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

-- TRIGGERS EQUIPO
/*TRIGGER PARA COMPROBAR FECHA DE FUNDACION
    En el caso de que se ponga una fecha que no este
    entre el 02/06/2020 (fecha de salida de VALORANT)
    y la fecha acutal (abmos excluidos) genera una exceptcion
    y no deja insertar el equipo
*/
CREATE OR REPLACE TRIGGER equiposFechaFundInterval
BEFORE INSERT OR UPDATE OF fechafund ON EQUIPOS
    FOR EACH ROW
DECLARE
v_fechaMin Date := to_date('02/06/2020','DD/MM/YYYY');
    e_fechaIncorrecta exception;
BEGIN
    if :NEW.fechafund<v_fechaMin OR :NEW.fechafund>SYSDATE then
        RAISE e_fechaIncorrecta;
END IF;
EXCEPTION
        WHEN e_fechaIncorrecta THEN
            RAISE_APPLICATION_ERROR(-20001,'LA FECHA DEBE DE ESTAR COMPRENDIDA ENTRE EL 02/06/2020 Y LA FECHA ACTUAL EXCLUIDOS AMBOS');
END;

/*TRIGGER PARA COMPROBAR EL NOMBRE
  En el caso de que se ponga un nombre ya
  escogido por un equipo salta una excepcion y no deja insertar
*/

CREATE OR REPLACE TRIGGER nombreDuplicadoEquipo
    FOR INSERT OR UPDATE ON EQUIPOS
                             COMPOUND TRIGGER

                             -- Variables del trigger compuesto
                             v_nombre equipos.nombre%TYPE;
e_nombreduplicado EXCEPTION;

BEFORE EACH ROW IS
BEGIN
    IF INSERTING OR (UPDATING AND :NEW.nombre != :OLD.nombre) THEN
        v_nombre := :NEW.nombre;
END IF;
END BEFORE EACH ROW;

    AFTER STATEMENT IS
        v_total NUMBER;
BEGIN
        IF v_nombre IS NOT NULL THEN
SELECT COUNT(*) INTO v_total
FROM EQUIPOS
WHERE LOWER(nombre) = LOWER(v_nombre);

IF v_total > 1 THEN
                RAISE e_nombreduplicado;
END IF;
END IF;

EXCEPTION
        WHEN e_nombreduplicado THEN
            RAISE_APPLICATION_ERROR(-20002, 'Error: Ya existe un equipo con ese nombre');
END AFTER STATEMENT;
END nombreDuplicadoEquipo;

-- TRIGGERS JUGADOR

/*TRIGGER PARA COMPROBAR EL SALARIO MINIMO
  En el caso de que se ponga un salario
  inferior a 1184 se pondra como valor minimo dicha cantidad
*/
CREATE OR REPLACE TRIGGER salarioMin
BEFORE INSERT OR UPDATE OF sueldo ON JUGADORES
    FOR EACH ROW
DECLARE
e_salarioMin exception;
BEGIN
    IF :NEW.sueldo < 1184 THEN
        RAISE e_salarioMin;
end if;

EXCEPTION
    WHEN e_salarioMin THEN
        RAISE_APPLICATION_ERROR(-20001,'Error: El salario no puede ser menor a 1184€');
WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004,'Error desconocido: ' || SQLERRM);
END;
/*TRIGGER PARA COMPROBAR EL NICKNAME
  En el caso de que se ponga un nickname ya
  escogido por un jugador salta una excepcion y no deja insertar
*/

CREATE OR REPLACE TRIGGER nickNameDuplicadoJugador
    FOR INSERT OR UPDATE ON jugadores
                             COMPOUND TRIGGER

                             -- Variables del trigger compuesto
                             v_nickname jugadores.nickname%TYPE;
e_nicknameduplicado EXCEPTION;

BEFORE EACH ROW IS
BEGIN
    IF INSERTING OR (UPDATING AND :NEW.nickname != :OLD.nickname) THEN
        v_nickname := :NEW.nickname;
END IF;
END BEFORE EACH ROW;

    AFTER STATEMENT IS
        v_total NUMBER;
BEGIN
        IF v_nickname IS NOT NULL THEN
SELECT COUNT(*) INTO v_total
FROM jugadores
WHERE LOWER(nickname) = LOWER(v_nickname);

IF v_total > 1 THEN
                RAISE e_nicknameduplicado;
END IF;
END IF;

EXCEPTION
        WHEN e_nicknameduplicado THEN
            RAISE_APPLICATION_ERROR(-20002, 'Error: Ya existe un jugador con ese nickname');
END AFTER STATEMENT;
END nickNameDuplicadoJugador;


/*TRIGGER PARA COMPROBAR LA CANTIDAD DE JUGADORES POR EQUIPOS
  En el caso de que se añada un jugador con un idEquipo igual a otros 6 jugadores salta una excepcion y no deja insertar
*/

CREATE OR REPLACE TRIGGER maxJugadoresEquipo
FOR INSERT OR UPDATE ON jugadores
                         COMPOUND TRIGGER

                         v_idEquipo jugadores.idEquipo%TYPE;
e_max_jugadores EXCEPTION;

BEFORE EACH ROW IS
BEGIN

    IF INSERTING OR (UPDATING AND :NEW.idEquipo != :OLD.idEquipo) THEN
        v_idEquipo := :NEW.idEquipo;
END IF;
END BEFORE EACH ROW;

AFTER STATEMENT IS
        v_total NUMBER;
BEGIN
        IF v_idEquipo IS NOT NULL THEN
SELECT COUNT(*) INTO v_total
FROM jugadores
WHERE idEquipo = v_idEquipo;

IF v_total > 6 THEN
                RAISE e_max_jugadores;
END IF;
END IF;

EXCEPTION
        WHEN e_max_jugadores THEN
            RAISE_APPLICATION_ERROR(-20004, 'ERROR: No se pueden inscribir más jugadres en el equipo: ' || v_idEquipo || ', max jugadores: 6');
WHEN OTHERS THEN
            RAISE_APPLICATION_ERROR(-20004,'Error desconocido: ' || SQLERRM);
END AFTER STATEMENT;
END maxJugadoresEquipo;


/*TRIGGER PARA COMPROBAR EDAD DEL JUGADOR
  En el caso de que se añada un jugador que tenga menos de 13 años salta una excepcion y no deja insertar
*/

CREATE OR REPLACE TRIGGER mayorDe13
BEFORE INSERT OR UPDATE ON jugadores
                            FOR EACH ROW
DECLARE
e_edadIncorrecta exception;
BEGIN
    IF TRUNC(MONTHS_BETWEEN(SYSDATE,:NEW.fechanac) / 12)<13 THEN
        RAISE e_edadIncorrecta;
END IF;

EXCEPTION
    WHEN e_edadIncorrecta THEN
        RAISE_APPLICATION_ERROR(-20001,'Error: El jugador no puede ser menos de 13 años.');
WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20004,'Error desconocido: ' || SQLERRM);
END;



