INSERT INTO USUARIOS (TIPOUSUARIO,NOMBRE, CONTRASENA) VALUES ('administrador','admin1','admin1');
INSERT INTO USUARIOS (TIPOUSUARIO,NOMBRE, CONTRASENA) VALUES ('administrador','admin2','admin2');
INSERT INTO USUARIOS (TIPOUSUARIO,NOMBRE, CONTRASENA) VALUES ('administrador','admin3','admin3');

INSERT INTO USUARIOS (TIPOUSUARIO,NOMBRE, CONTRASENA) VALUES ('usuario','usr1','usr1');
INSERT INTO USUARIOS (TIPOUSUARIO,NOMBRE, CONTRASENA) VALUES ('usuario','usr2','usr2');
INSERT INTO USUARIOS (TIPOUSUARIO,NOMBRE, CONTRASENA) VALUES ('usuario','usr3','usr3');


INSERT INTO roles (rol) VALUES ('Duelista');
INSERT INTO roles (rol) VALUES ('Centinela');
INSERT INTO roles (rol) VALUES ('Iniciador');
INSERT INTO roles (rol) VALUES ('Controlador');
INSERT INTO roles (rol) VALUES ('Asesino');
INSERT INTO roles (rol) VALUES ('Mago');

INSERT INTO competiciones (estado) VALUES (DEFAULT);

commit;


