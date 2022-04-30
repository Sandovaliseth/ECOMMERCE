/* Tabla productos */
INSERT INTO productos (nombre, descripcion, imagen, precio, cantidad) VALUES('Televisor Samsung', 'Resolucion: 1080p, LCD 50 pulgadas', 'default.jpg', 1240000, 340);
INSERT INTO productos (nombre, descripcion, imagen, precio, cantidad) VALUES('Portátil', 'Procesador: AMD RYZEN5', 'default.jpg', 2600000, 50);
INSERT INTO productos (nombre, descripcion, imagen, precio, cantidad) VALUES('Medias', 'Tallas: 35, 36, 37, 38, 39, 40', 'default.jpg', 8000, 40);
INSERT INTO productos (nombre, descripcion, imagen, precio, cantidad) VALUES('Jugos', 'De diferentes sabores', 'default.jpg', 7500, 20);
INSERT INTO productos (nombre, descripcion, imagen, precio, cantidad) VALUES('Mouse', 'Gamer', 'default.jpg', 15000, 70);
INSERT INTO productos (nombre, descripcion, imagen, precio, cantidad) VALUES('Nevera', 'Tienes 515 Litros de capacidad para adaptar todos los víveres que tu y tu familia necesitan en un solo lugar', 'default.jpg', 760000, 35);

/* Tabla usuarios o clientes */
INSERT INTO usuarios (nombre, apellido, email, direccion, telefono) VALUES('Liseth', 'Sandoval', 'liseth@correo.com', 'Cll 12 #32-18', 6483296);
INSERT INTO usuarios (nombre, apellido, email, direccion, telefono) VALUES('Marly', 'Pabon', 'marlyp@correo.com', 'Cll 23 #34-9', 6483296);
INSERT INTO usuarios (nombre, apellido, email, direccion, telefono) VALUES('Brayan', 'Ochoa', '8choa@correo.com', 'Cll 10 #15-20', 6483296);
INSERT INTO usuarios (nombre, apellido, email, direccion, telefono) VALUES('Maybrith', 'Sandoval', 'sandovalm@correo.com', 'Cll 5 #18-25', 6483296);

/* Tabla pedido */
INSERT INTO pedidos (numero, fechaCreacion, fechaRecibido, total) VALUES('1038', '23/07/22', '23/07/22', 32000);
INSERT INTO pedidos (numero, fechaCreacion, fechaRecibido, total) VALUES('1039', '08/04/22', '23/07/22', 58000);
INSERT INTO pedidos (numero, fechaCreacion, fechaRecibido, total) VALUES('1040', '14/11/12', '23/07/22', 35000);

/*Creamos los usuario del sistema*/
INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');