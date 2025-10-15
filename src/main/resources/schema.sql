CREATE TABLE personas.persona (
	id BIGINT auto_increment NOT NULL,
	nombre varchar(100) NOT NULL,
	correo varchar(100) NOT NULL,
	edad INT NOT NULL,
	CONSTRAINT persona_pk PRIMARY KEY (id),
	CONSTRAINT email_unique UNIQUE KEY (correo)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bootcamp_persona` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_persona` bigint NOT NULL,
  `id_bootcamp` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bootcamp_persona_persona_FK` (`id_persona`),
  CONSTRAINT `bootcamp_persona_persona_FK` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

