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



