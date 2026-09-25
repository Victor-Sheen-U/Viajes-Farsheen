-- ==========================================================
-- SISTEMA DE GESTIÓN DE RESERVAS DE VIAJES - VIAJES FARSHEEN
-- BASE DE DATOS PARA XAMPP (MySQL / MariaDB)
-- Puerto estándar XAMPP en este entorno: 3307 (o 3306 estándar)
-- Usuario predeterminado: root | Contraseña: (vacía)
-- ==========================================================

CREATE DATABASE IF NOT EXISTS `viajes_farsheen_db`
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE `viajes_farsheen_db`;

-- Desactivar verificación de llaves foráneas para reinicialización limpia si es necesario
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `reservas`;
DROP TABLE IF EXISTS `viajes`;
DROP TABLE IF EXISTS `clientes`;
DROP TABLE IF EXISTS `destinos`;
SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------------------------------------
-- 1. Tabla: destinos
-- ----------------------------------------------------------
CREATE TABLE `destinos` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(100) NOT NULL,
    `pais` VARCHAR(50) NOT NULL,
    `ciudad` VARCHAR(50) NOT NULL,
    `descripcion` TEXT,
    `clima` VARCHAR(50),
    INDEX `idx_destino_pais` (`pais`),
    INDEX `idx_destino_ciudad` (`ciudad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------
-- 2. Tabla: viajes
-- ----------------------------------------------------------
CREATE TABLE `viajes` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `titulo` VARCHAR(255) NOT NULL,
    `descripcion` TEXT,
    `precio` DECIMAL(12, 2) NOT NULL,
    `duracion_dias` INT NOT NULL,
    `fecha_salida` DATE NOT NULL,
    `fecha_llegada` DATE NOT NULL,
    `cupos_disponibles` INT NOT NULL,
    `estado` VARCHAR(50) NOT NULL DEFAULT 'DISPONIBLE',
    `destino_id` BIGINT NOT NULL,
    CONSTRAINT `fk_viaje_destino` FOREIGN KEY (`destino_id`)
        REFERENCES `destinos` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    INDEX `idx_viaje_destino` (`destino_id`),
    INDEX `idx_viaje_estado` (`estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------
-- 3. Tabla: clientes
-- ----------------------------------------------------------
CREATE TABLE `clientes` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(80) NOT NULL,
    `apellido` VARCHAR(80) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `telefono` VARCHAR(30),
    `documento_identidad` VARCHAR(50) NOT NULL UNIQUE,
    INDEX `idx_cliente_email` (`email`),
    INDEX `idx_cliente_documento` (`documento_identidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------
-- 4. Tabla: reservas
-- ----------------------------------------------------------
CREATE TABLE `reservas` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `fecha_reserva` DATETIME NOT NULL,
    `numero_personas` INT NOT NULL,
    `precio_total` DECIMAL(12, 2) NOT NULL,
    `estado` VARCHAR(50) NOT NULL DEFAULT 'CONFIRMADA',
    `cliente_id` BIGINT NOT NULL,
    `viaje_id` BIGINT NOT NULL,
    CONSTRAINT `fk_reserva_cliente` FOREIGN KEY (`cliente_id`)
        REFERENCES `clientes` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_reserva_viaje` FOREIGN KEY (`viaje_id`)
        REFERENCES `viajes` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    INDEX `idx_reserva_cliente` (`cliente_id`),
    INDEX `idx_reserva_viaje` (`viaje_id`),
    INDEX `idx_reserva_estado` (`estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------------------------------------
-- DATOS INICIALES DE PRUEBA (SEEDS)
-- ----------------------------------------------------------

-- Destinos turísticos
INSERT INTO `destinos` (`id`, `nombre`, `pais`, `ciudad`, `descripcion`, `clima`) VALUES
(1, 'Cartagena Colonial y Playas', 'Colombia', 'Cartagena', 'Disfruta de la ciudad amurallada, historia colonial y las paradisiacas islas del Rosario.', 'Cálido Tropical'),
(2, 'San Andrés Islas Paradise', 'Colombia', 'San Andrés', 'Mar de los 7 colores, snorkel en arrecifes de coral y cultura raizal caribeña.', 'Tropical Cálido'),
(3, 'Medellín Primavera Eterna', 'Colombia', 'Medellín', 'Ciudad de la eterna primavera, cultura paisa, Guatapé y recorridos en metrocable.', 'Templado Primaveral'),
(4, 'Cancún y Riviera Maya', 'México', 'Cancún', 'Playas de arena blanca, ruinas mayas en Tulum y Chichén Itzá, y parques temáticos.', 'Tropical');

-- Viajes ofertados
INSERT INTO `viajes` (`id`, `titulo`, `descripcion`, `precio`, `duracion_dias`, `fecha_salida`, `fecha_llegada`, `cupos_disponibles`, `estado`, `destino_id`) VALUES
(1, 'Semana Santa en Cartagena', 'Paquete todo incluido con vuelos y hotel 4 estrellas frente a la playa de Bocagrande.', 1250000.00, 5, '2025-04-10', '2025-04-15', 15, 'DISPONIBLE', 1),
(2, 'Aventura Todo Incluido San Andrés', 'Vuelo directo, hotel todo incluido, vuelta a la isla en carrito de golf y tour a Johnny Cay.', 1800000.00, 6, '2025-05-01', '2025-05-07', 10, 'DISPONIBLE', 2),
(3, 'Ruta del Café y Primavera en Medellín', 'Recorrido por fincas cafeteras, tour por Comuna 13, ascenso a la Piedra del Peñol en Guatapé.', 850000.00, 4, '2025-06-12', '2025-06-16', 20, 'DISPONIBLE', 3),
(4, 'Escapada Maya Cancún', 'Vuelos internacionales, resort 5 estrellas todo incluido y excursión guiada a Chichén Itzá.', 3200000.00, 7, '2025-07-05', '2025-07-12', 8, 'DISPONIBLE', 4);

-- Clientes registrados
INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `email`, `telefono`, `documento_identidad`) VALUES
(1, 'Carlos', 'Gomez', 'carlos.gomez@mail.com', '+57 3001234567', '10203040'),
(2, 'Maria', 'Rodriguez', 'maria.rodriguez@mail.com', '+57 3119876543', '10987654'),
(3, 'Juan', 'Perez', 'juan.perez@mail.com', '+57 3205556677', '10123456');

-- Reservas registradas
INSERT INTO `reservas` (`id`, `fecha_reserva`, `numero_personas`, `precio_total`, `estado`, `cliente_id`, `viaje_id`) VALUES
(1, '2025-03-01 10:30:00', 2, 2500000.00, 'CONFIRMADA', 1, 1),
(2, '2025-03-02 14:15:00', 1, 1800000.00, 'CONFIRMADA', 2, 2);

-- Actualizar consecutivo auto_increment
ALTER TABLE `destinos` AUTO_INCREMENT = 5;
ALTER TABLE `viajes` AUTO_INCREMENT = 5;
ALTER TABLE `clientes` AUTO_INCREMENT = 4;
ALTER TABLE `reservas` AUTO_INCREMENT = 3;
