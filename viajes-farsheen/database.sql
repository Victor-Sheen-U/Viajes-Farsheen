-- ==========================================================
-- Script de creacion y poblado para la base de datos viajes-farsheen
-- Corriendo en MariaDB / MySQL de XAMPP en el puerto 3307
-- Usuario: root (sin contraseña)
-- ==========================================================

USE `viajes-farsheen`;

-- Desactivar llaves foraneas por si toca reiniciar las tablas
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `booking`;
DROP TABLE IF EXISTS `trip`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `destination`;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Tabla destination
CREATE TABLE `destination` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `country` VARCHAR(50) NOT NULL,
    `city` VARCHAR(50) NOT NULL,
    `description` TEXT,
    `weather` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Tabla trip
CREATE TABLE `trip` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL,
    `description` TEXT,
    `price` DECIMAL(12, 2) NOT NULL,
    `duration` INT NOT NULL,
    `departure_date` DATE NOT NULL,
    `arrival_date` DATE NOT NULL,
    `available_spots` INT NOT NULL,
    `state` VARCHAR(50) DEFAULT 'DISPONIBLE',
    `destination_id` BIGINT NOT NULL,
    CONSTRAINT `fk_trip_destination` FOREIGN KEY (`destination_id`)
        REFERENCES `destination` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Tabla customer
CREATE TABLE `customer` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(80) NOT NULL,
    `lastname` VARCHAR(80) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `phone` VARCHAR(30),
    `identity_document` VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. Tabla booking
CREATE TABLE `booking` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `booking_date` DATETIME NOT NULL,
    `number_of_persons` INT NOT NULL,
    `total_price` DECIMAL(12, 2) NOT NULL,
    `status` VARCHAR(50) NOT NULL DEFAULT 'CONFIRMADA',
    `customer_id` BIGINT NOT NULL,
    `trip_id` BIGINT NOT NULL,
    CONSTRAINT `fk_booking_customer` FOREIGN KEY (`customer_id`)
        REFERENCES `customer` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT `fk_booking_trip` FOREIGN KEY (`trip_id`)
        REFERENCES `trip` (`id`)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ==========================================================
-- Insertando datos de prueba (seeds)
-- ==========================================================

-- Destinos
INSERT INTO `destination` (`id`, `name`, `country`, `city`, `description`, `weather`) VALUES
(1, 'Cartagena Colonial y Playas', 'Colombia', 'Cartagena', 'Disfruta de la ciudad amurallada, historia colonial y las paradisiacas islas del Rosario.', 'Cálido Tropical'),
(2, 'San Andrés Islas Paradise', 'Colombia', 'San Andrés', 'Mar de los 7 colores, snorkel en arrecifes de coral y cultura raizal caribeña.', 'Tropical Cálido'),
(3, 'Medellín Primavera Eterna', 'Colombia', 'Medellín', 'Ciudad de la eterna primavera, cultura paisa, Guatapé y recorridos en metrocable.', 'Templado Primaveral'),
(4, 'Cancún y Riviera Maya', 'México', 'Cancún', 'Playas de arena blanca, ruinas mayas en Tulum y Chichén Itzá, y parques temáticos.', 'Tropical');

-- Viajes
INSERT INTO `trip` (`id`, `title`, `description`, `price`, `duration`, `departure_date`, `arrival_date`, `available_spots`, `state`, `destination_id`) VALUES
(1, 'Semana Santa en Cartagena', 'Paquete todo incluido con vuelos y hotel 4 estrellas frente a la playa de Bocagrande.', 1250000.00, 5, '2025-04-10', '2025-04-15', 15, 'DISPONIBLE', 1),
(2, 'Aventura Todo Incluido San Andrés', 'Vuelo directo, hotel todo incluido, vuelta a la isla en carrito de golf y tour a Johnny Cay.', 1800000.00, 6, '2025-05-01', '2025-05-07', 10, 'DISPONIBLE', 2),
(3, 'Ruta del Café y Primavera en Medellín', 'Recorrido por fincas cafeteras, tour por Comuna 13, ascenso a la Piedra del Peñol en Guatapé.', 850000.00, 4, '2025-06-12', '2025-06-16', 20, 'DISPONIBLE', 3),
(4, 'Escapada Maya Cancún', 'Vuelos internacionales, resort 5 estrellas todo incluido y excursión guiada a Chichén Itzá.', 3200000.00, 7, '2025-07-05', '2025-07-12', 8, 'DISPONIBLE', 4);

-- Clientes
INSERT INTO `customer` (`id`, `name`, `lastname`, `email`, `phone`, `identity_document`) VALUES
(1, 'Carlos', 'Gomez', 'carlos.gomez@mail.com', '+57 3001234567', '10203040'),
(2, 'Maria', 'Rodriguez', 'maria.rodriguez@mail.com', '+57 3119876543', '10987654'),
(3, 'Juan', 'Perez', 'juan.perez@mail.com', '+57 3205556677', '10123456');

-- Reservas
INSERT INTO `booking` (`id`, `booking_date`, `number_of_persons`, `total_price`, `status`, `customer_id`, `trip_id`) VALUES
(1, '2025-03-01 10:30:00', 2, 2500000.00, 'CONFIRMADA', 1, 1),
(2, '2025-03-02 14:15:00', 1, 1800000.00, 'CONFIRMADA', 2, 2);

-- Ajustar auto_increments
ALTER TABLE `destination` AUTO_INCREMENT = 5;
ALTER TABLE `trip` AUTO_INCREMENT = 5;
ALTER TABLE `customer` AUTO_INCREMENT = 4;
ALTER TABLE `booking` AUTO_INCREMENT = 3;
