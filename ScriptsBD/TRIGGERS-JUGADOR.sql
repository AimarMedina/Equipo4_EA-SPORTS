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



