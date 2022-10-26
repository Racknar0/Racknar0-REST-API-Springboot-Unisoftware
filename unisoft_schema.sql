CREATE TABLE clientes (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	dni VARCHAR(15),
	nombre VARCHAR(100),
	apellido VARCHAR(100),
	fecha_nacimiento DATE
);
	
	
CREATE TABLE venta (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	fecha_venta DATE,
	total DOUBLE,
	id_cliente INT,
	CONSTRAINT fk_id_cliente FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

CREATE TABLE productos(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	sku VARCHAR(50),
	descripcion VARCHAR(150),
	precio_compra DOUBLE,
	precio_venta DOUBLE,
	stock INT,
	ultima_compra DATE
);
	
CREATE TABLE detalle_venta(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	cantidad INT,
	subtotal DOUBLE,
	id_venta INT,
	id_producto INT,
	CONSTRAINT fk_id_producto FOREIGN KEY (id_producto) REFERENCES productos(id),
	CONSTRAINT fk_id_venta FOREIGN KEY (id_venta) REFERENCES venta(id)
);