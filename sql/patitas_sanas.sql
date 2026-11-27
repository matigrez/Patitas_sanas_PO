DROP DATABASE IF EXISTS patitas_sanas;
CREATE DATABASE patitas_sanas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE patitas_sanas;

CREATE TABLE tutor (
                       rut VARCHAR(12) NOT NULL,
                       nombre VARCHAR(100) NOT NULL,
                       correo VARCHAR(100) NOT NULL,
                       numero VARCHAR(15) NOT NULL,
                       PRIMARY KEY (rut)
) ENGINE=InnoDB;

CREATE TABLE mascota (
                         codigo VARCHAR(20) NOT NULL,
                         rut_tutor VARCHAR(12) NOT NULL,
                         especie VARCHAR(20) NOT NULL,
                         nombre VARCHAR(60) NOT NULL,
                         meses INT NOT NULL,
                         peso DECIMAL(5,2) NOT NULL,
                         estado_clinico VARCHAR(30) NOT NULL,
                         es_vacunable BOOLEAN NOT NULL DEFAULT FALSE,
                         PRIMARY KEY (codigo),
                         CONSTRAINT fk_mascota_tutor
                             FOREIGN KEY (rut_tutor) REFERENCES tutor(rut)
                                 ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE perro (
                       codigo VARCHAR(20) NOT NULL,
                       tamano VARCHAR(20) NOT NULL,
                       adiestramiento VARCHAR(20) NOT NULL,
                       PRIMARY KEY (codigo),
                       CONSTRAINT fk_perro_mascota
                           FOREIGN KEY (codigo) REFERENCES mascota(codigo)
                               ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE gato (
                      codigo VARCHAR(20) NOT NULL,
                      estilo_vida VARCHAR(20) NOT NULL,
                      pelaje VARCHAR(20) NOT NULL,
                      PRIMARY KEY (codigo),
                      CONSTRAINT fk_gato_mascota
                          FOREIGN KEY (codigo) REFERENCES mascota(codigo)
                              ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE vacuna (
                        id_vacuna INT AUTO_INCREMENT NOT NULL,
                        codigo_mascota VARCHAR(20) NOT NULL,
                        nombre_vacuna VARCHAR(80) NOT NULL,
                        PRIMARY KEY (id_vacuna),
                        CONSTRAINT fk_vacuna_mascota
                            FOREIGN KEY (codigo_mascota) REFERENCES mascota(codigo)
                                ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;