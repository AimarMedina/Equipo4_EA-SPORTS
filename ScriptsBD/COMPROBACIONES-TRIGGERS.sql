/*COMPROBACIONES TRIGGER noMasUnaCompeticion - COMPETICIONES*/
    INSERT INTO competiciones (estado) 
        VALUES (DEFAULT); //SI DEJA POR NO HAY NINGUNA COMPETICION CREADA
        
    INSERT INTO competiciones (estado) 
        VALUES (DEFAULT); //NO DEJA POR YA HAY UNA COMPETICION CREADA

/*COMPROBACIONES TRIGGER cerrarCompeticion - COMPETICIONES */
    UPDATE competiciones SET estado = 'abierto'; //Abrimos primero para luego comprobar si nos deja cerrar sin equipos
    UPDATE competiciones SET estado = 'cerrado'; //NO DEJA POR QUE NO HAY MINIMO 2 EQUIPOS
    
    // CREAMOS 2 EQUIPOS
        INSERT INTO equipos (nombre,fechafund) 
            VALUES ('Prueba1',to_date('10/10/2020','dd/MM/yyyy'));
            
        INSERT INTO equipos (nombre,fechafund) 
            VALUES ('Prueba2',to_date('10/09/2020','dd/MM/yyyy'));
    
    UPDATE competiciones SET estado = 'cerrado'; //NO DEJA POR QUE NO HAY MINIMO 2 JUGADORES POR EQUIPO
    
    // CREAMOS 1 EQUIPO MAS
        INSERT INTO equipos (nombre,fechafund) 
            VALUES ('Prueba3',to_date('10/11/2020','dd/MM/yyyy'));
    
    UPDATE competiciones SET estado = 'cerrado'; //NO DEJA POR QUE NO HAY UN NUMERO IMPAR DE EQUIPOS
    
    // ELIMINAMOS 1 EQUIPO y AÑADIMOS 2 JUGADORES EN CADA EQUIPO
        DELETE FROM equipos WHERE lower(nombre) = 'prueba1';
    
    INSERT INTO jugadores (nombre,idEquipo) 
        VALUES ('Prueba1',3);
        
    INSERT INTO jugadores (nombre,idEquipo) 
        VALUES ('Prueba2',2);
        
    INSERT INTO jugadores (nombre,idEquipo) 
        VALUES ('Prueba3',3);
        
    INSERT INTO jugadores (nombre,idEquipo)     
    VALUES ('Prueba4',2);
    
    UPDATE competiciones SET estado = 'cerrado'; // SI QUE DEJA POR QUE LA COMPETICION CUMPLE CON TODOS LOS REQUISITOS
    
/*COMPROBACIONES TRIGGER cerrarCompeticionJugador - COMPETICIONES */
    INSERT INTO jugadores (nombre,idEquipo) 
        VALUES ('Prueba5',2); //NO DEJA POR QUE LA COMPETICION ESTA CERRADA
        
    UPDATE jugadores set nombre = 'JugadorModificado' 
        WHERE lower(nombre) = 'prueba4'; //NO DEJA POR QUE LA COMPETICION ESTA CERRADA
        
    DELETE FROM jugadores 
        WHERE lower(nombre) = 'prueba1'; //NO DEJA POR QUE LA COMPETICION ESTA CERRADA

/*COMPROBACIONES TRIGGER cerrarCompeticionEquipo - COMPETICIONES */
    INSERT INTO equipos (nombre,fechafund) 
        VALUES ('Prueba1',to_date('10/10/2020','dd/MM/yyyy')); //NO DEJA POR QUE LA COMPETICION ESTA CERRADA
        
    UPDATE equipos set nombre = 'EquipoModificado' 
        WHERE lower(nombre) = 'prueba3'; //NO DEJA POR QUE LA COMPETICION ESTA CERRADA
        
    DELETE FROM equipos 
        WHERE lower(nombre) = 'prueba3'; //NO DEJA POR QUE LA COMPETICION ESTA CERRADA

/*COMPROBACIONES TRIGGER salarioMin - JUGADORES*/
    // ABRIMOS LA COMPETICION PARA PODER INSERTAR JUGADORES
        UPDATE competiciones SET estado = 'abierto';

    INSERT INTO jugadores (nombre,idEquipo,sueldo) 
        VALUES ('Prueba5',3,1100); //NO DEJA POR QUE NO LLEGA AL SALARIO MINIMO (1184)
        
    INSERT INTO jugadores (nombre,idEquipo,sueldo) 
        VALUES ('Prueba6',3,1200); //SI DEJA POR QUE LLEGA AL SALARIO MINIMO (1184)

/*COMPROBACIONES TRIGGER nickNameDuplicadoJugador - JUGADORES*/
    INSERT INTO jugadores (nombre,idEquipo,sueldo,nickname) 
        VALUES ('Prueba7',3,1200,'Nick1'); //SI DEJA POR QUE NO HAY NINGUN 'Nick1'
    INSERT INTO jugadores (nombre,idEquipo,sueldo,nickname) 
        VALUES ('Prueba8',3,1200,'Nick1'); //NO DEJA POR QUE YA HAY UN 'Nick1'
    
/*COMPROBACIONES TRIGGER maxJugadoresEquipo - JUGADAORES/EQUIPOS*/
    //AÑADIMOS 2 JUGADORES MÁS
        INSERT INTO jugadores (nombre,idEquipo,sueldo,nickname)
            VALUES ('Prueba9',3,1200,'Nick2');  
            
        INSERT INTO jugadores (nombre,idEquipo,sueldo,nickname) 
            VALUES ('Prueba10',3,1200,'Nick3');
        
    INSERT INTO jugadores (nombre,idEquipo,sueldo,nickname) 
        VALUES ('Prueba11',3,1200,'Nick4'); //NO DEJA POR QUE EL MAXIMO DE JUGADORES POR EQUIPOS ES 6

/*COMPROBACIONES TRIGGER mayorDe13 - JUGADORES*/
    INSERT INTO jugadores (nombre,idEquipo,fechanac) 
        VALUES ('Prueba11',2,to_date('10/10/2018','dd/MM/yyyy')); //NO DEJA POR QUE ES MENOR DE 13 AÑOS
    
/*COMPROBACIONES TRIGGER equiposFechaFundInterval - EQUIPOS*/
    INSERT INTO equipos (nombre,fechafund) 
        VALUES ('Prueba4',to_date('10/10/2002','dd/MM/yyyy')); //NO DEJA POR QUE LA FECHA NO ES SUPERIOR AL 02/06/2020
        
    INSERT INTO equipos (nombre,fechafund) 
        VALUES ('Prueba5',to_date('10/10/2026','dd/MM/yyyy')); //NO DEJA POR QUE LA FECHA ES SUPERIOR A LA ACTUAL
        
    INSERT INTO equipos (nombre,fechafund) 
        VALUES ('Prueba6',to_date('10/10/2020','dd/MM/yyyy')); //SI QUE DEJA POR QUE LA FECHA ESTA DENTRO DEL INTERVALO


/*COMPROBACIONES TRIGGER nombreDuplicadoEquipo - EQUIPOS*/
    INSERT INTO equipos (nombre,fechafund) 
        VALUES ('Prueba6',to_date('10/10/2020','dd/MM/yyyy')); //NO DEJA POR QUE HAY UN EQUIPO CON ESE NOMBRE