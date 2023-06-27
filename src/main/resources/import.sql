/* Tabla productos */
INSERT INTO productos (id, nombre, descripcion, imagen, precio, cantidad) VALUES(1, 'Televisor Samsung', 'Resolucion: 1080p- LCD 50', 'default.jpg', 1240000, 340);
INSERT INTO productos (id, nombre, descripcion, imagen, precio, cantidad) VALUES(2, 'Portátil', 'Procesador: AMD RYZEN5', 'default.jpg', 2600000, 50);
INSERT INTO productos (id, nombre, descripcion, imagen, precio, cantidad) VALUES(3, 'Medias', 'Tallas: 35, 36, 37, 38, 39, 40', 'default.jpg', 8000, 40);
INSERT INTO productos (id, nombre, descripcion, imagen, precio, cantidad) VALUES(4, 'Jugos', 'De diferentes sabores', 'default.jpg', 7500, 20);
INSERT INTO productos (id, nombre, descripcion, imagen, precio, cantidad) VALUES(5, 'Mouse', 'Gamer', 'default.jpg', 15000, 70);
INSERT INTO productos (id, nombre, descripcion, imagen, precio, cantidad) VALUES(6, 'Nevera', 'Tienes 515 Litros de capacidad', 'default.jpg', 760000, 35);

/* Tabla usuarios */
INSERT INTO usuarios (id, usuario, contrasena, confirmarContrasena, rol) VALUES (1, 'admin', '123', '123', 'ADMINISTRADOR');
INSERT INTO usuarios (id, usuario, contrasena, confirmarContrasena, rol) VALUES (2, 'martha12', '1234', '1234', 'USUARIO');
INSERT INTO usuarios (id, usuario, contrasena, confirmarContrasena, rol) VALUES (3, 'Juan', '0123', '0123', 'USUARIO');

/* Tabla clientes */
INSERT INTO clientes (id, nombre, apellido, email, direccion, telefono, id_usuario) VALUES(1, 'Juan', 'Lopez', 'jlopez@correo.com', 'Cll 23 #18-9', 64832960, 2);
INSERT INTO clientes (id, nombre, apellido, email, direccion, telefono, id_usuario) VALUES(2, 'Martha', 'Martinez', 'martinez@correo.com', 'Cll 12 #32-18', 6483296, 3);