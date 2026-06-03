CREATE TABLE compra (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    total DOUBLE NOT NULL,
    publicacion_id INT NOT NULL,
    usuario_id INT NOT NULL,

    FOREIGN KEY (publicacion_id) REFERENCES publicacion(id)
);