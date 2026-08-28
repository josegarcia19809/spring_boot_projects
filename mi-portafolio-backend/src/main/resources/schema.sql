DROP TABLE IF EXISTS personal_info;

CREATE TABLE personal_info
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre              VARCHAR(100) NOT NULL,
    apellido            VARCHAR(100) NOT NULL,
    titulo              VARCHAR(255) NOT NULL,
    descripcion_perfil  TEXT         NOT NULL,
    url_imagen_perfil   VARCHAR(500),
    anios_experiencia   INT,
    correo              VARCHAR(255),
    telefono            VARCHAR(50),
    url_linkedin        VARCHAR(500),
    url_github          VARCHAR(500)
);
