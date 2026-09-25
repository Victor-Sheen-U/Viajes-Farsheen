# 🐬 Guía Completa de Configuración de Base de Datos con XAMPP

Este documento explica cómo está configurada y cómo restaurar o consultar la base de datos MySQL/MariaDB en **XAMPP** para el proyecto **Viajes Farsheen**.

---

## 📌 1. Información de Conexión en XAMPP

| Parámetro | Valor Configurado | Notas |
| :--- | :--- | :--- |
| **Servidor / Host** | `localhost` o `127.0.0.1` | Local |
| **Puerto en este entorno** | **`3307`** (o `3306` estándar) | En esta máquina XAMPP escucha en el puerto `3307` |
| **Base de Datos** | `viajes_farsheen_db` | Creada y con seeds iniciales |
| **Usuario** | `root` | Superusuario por defecto en XAMPP |
| **Contraseña** | *(vacía / sin contraseña)* | Estándar de XAMPP |
| **Driver JDBC** | `com.mysql.cj.jdbc.Driver` | MySQL Connector J 8.x / 9.x |

> 💡 **Nota Importante sobre el Puerto:**
> En este entorno Linux, el servicio XAMPP MariaDB se encuentra escuchando en el puerto **3307** (`/opt/lampp/bin/mysql`).
> La URL JDBC en `src/main/resources/application.properties` ya está configurada con:
> ```properties
> spring.datasource.url=jdbc:mysql://localhost:3307/viajes_farsheen_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
> ```
> Si en tu computador personal XAMPP corre en el puerto estándar `3306`, simplemente cambia `3307` por `3306` en dicho archivo.

---

## 🚀 2. Iniciar Servicios de XAMPP

### En Linux:
```bash
sudo /opt/lampp/lampp startmysql
# o iniciar todo XAMPP:
sudo /opt/lampp/lampp start
```

### En Windows:
1. Abrir **XAMPP Control Panel**.
2. Hacer clic en **Start** junto a **Apache** y **MySQL**.

---

## 🗄️ 3. Importar el Script SQL

El proyecto incluye el script completo en:
`database/database.sql`

### Opción A: Desde Terminal (Automático)
```bash
/opt/lampp/bin/mysql -h 127.0.0.1 -P 3307 -u root < database/database.sql
```

### Opción B: Desde phpMyAdmin
1. Abre tu navegador web en: `http://localhost/phpmyadmin` (o el puerto de tu Apache).
2. Haz clic en la pestaña **Importar** (Import).
3. Selecciona el archivo `database/database.sql` de este proyecto.
4. Presiona el botón **Continuar** (Go).

---

## 📊 4. Estructura de Tablas y Relaciones

El script genera 4 tablas relacionadas con integridad referencial:

```
┌──────────────┐         1:N          ┌──────────────┐
│   destinos   │ ───────────────────< │    viajes    │
└──────────────┘                      └──────┬───────┘
                                             │ 1:N
                                             ▼
┌──────────────┐         1:N          ┌──────────────┐
│   clientes   │ ───────────────────< │   reservas   │
└──────────────┘                      └──────────────┘
```

1. **`destinos`**: Almacena los destinos turísticos (nombre, país, ciudad, descripción, clima).
2. **`viajes`**: Ofertas turísticas con fechas, cupos disponibles, precio y relación `destino_id` (`FOREIGN KEY` hacia `destinos.id`).
3. **`clientes`**: Información personal y de contacto con restricciones `UNIQUE` en `email` y `documento_identidad`.
4. **`reservas`**: Asocia a un cliente con un viaje, almacena número de personas, precio total liquidado, fecha y estado (`CONFIRMADA`, `CANCELADA`).

---

## 🔍 5. Consultas de Verificación Rápida

Puedes ejecutar en MySQL o phpMyAdmin:

```sql
USE viajes_farsheen_db;

-- 1. Ver destinos
SELECT id, nombre, ciudad, pais FROM destinos;

-- 2. Ver viajes disponibles con nombre del destino
SELECT v.id, v.titulo, v.precio, v.cupos_disponibles, v.estado, d.nombre AS destino
FROM viajes v
JOIN destinos d ON v.destino_id = d.id;

-- 3. Ver clientes registrados
SELECT id, nombre, apellido, email, documento_identidad FROM clientes;

-- 4. Ver reservas activas con detalle
SELECT r.id, r.fecha_reserva, r.numero_personas, r.precio_total, r.estado,
       CONCAT(c.nombre, ' ', c.apellido) AS cliente, v.titulo AS viaje
FROM reservas r
JOIN clientes c ON r.cliente_id = c.id
JOIN viajes v ON r.viaje_id = v.id;
```
