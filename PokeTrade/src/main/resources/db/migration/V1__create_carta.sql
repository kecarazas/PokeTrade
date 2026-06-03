CREATE TABLE carta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    ps INT,
    tipo VARCHAR(20) NOT NULL,
    habilidad VARCHAR(35),
    descripcion_habilidad VARCHAR(100),
    coste_energia INT,
    retirada INT,
    ataque VARCHAR(35),
    dano_ataque INT,
    descripcion_ataque VARCHAR(100),
    debilidad VARCHAR(50) NOT NULL,
    resistencia INT,
    rareza VARCHAR(50) NOT NULL
);

CREATE TABLE publicacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    tipo_vendedor VARCHAR(50) NOT NULL,
    carta_id INT NOT NULL,
    usuario_id INT NOT NULL,

    FOREIGN KEY (carta_id) REFERENCES carta(id)
);

