CREATE TABLE editorial
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(100) NOT NULL,
    direccion     VARCHAR(200),
    ciudad        VARCHAR(100),
    estado        VARCHAR(100),
    codigo_postal VARCHAR(10)
);

CREATE TABLE libro
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo       VARCHAR(200) NOT NULL,
    isbn         VARCHAR(20),
    editorial_id BIGINT,

    FOREIGN KEY (editorial_id)
        REFERENCES editorial (id)
);

CREATE TABLE autor
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre   VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL
);

CREATE TABLE libro_autor
(
    libro_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,

    PRIMARY KEY (libro_id, autor_id),

    FOREIGN KEY (libro_id)
        REFERENCES libro (id),

    FOREIGN KEY (autor_id)
        REFERENCES autor (id)
);