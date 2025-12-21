-- Insertamos 2 usuarios
INSERT INTO usuarios
(id, nombre, email, username, password, estatus, fecha_registro)
VALUES
    (2, 'Jose Garcia', 'josegarcia@gmail.com', 'jgarcia', '{noop}master', 1, '2019-06-10');

INSERT INTO usuarios
(id, nombre, email, username, password, estatus, fecha_registro)
VALUES
    (3, 'Marisol Salinas Rodarte', 'marisol@itinajero.net', 'marisol', '{noop}mari123', 1,
     '2019-06-10');


-- Insertamos los roles para los usuarios

INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES (2, 1); -- PERFIL SUPERVISOR
INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES (3, 2); -- PERFIL ADMINISTRADOR