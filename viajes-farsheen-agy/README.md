# 🌍 Viajes Farsheen - Sistema de Gestión de Reservas de Viajes

API RESTful completa construida con **Java 21**, **Spring Boot 3.3.4**, **Spring Data JPA**, **MapStruct**, **OpenAPI Swagger** y base de datos relacional **MySQL en XAMPP**.

---

## 🚀 Inicio Rápido

### 1. Requisitos Previos
* **Java 21+** instalado.
* **XAMPP** con MySQL / MariaDB activo.

### 2. Base de Datos en XAMPP
El proyecto se encuentra preconfigurado para la base de datos `viajes_farsheen_db`.
En este entorno Linux, el servicio de MariaDB de XAMPP escucha en el puerto **`3307`** (o `3306` en entornos Windows o estándar).

Puedes importar el script con:
```bash
/opt/lampp/bin/mysql -h 127.0.0.1 -P 3307 -u root < database/database.sql
```
*(O a través de phpMyAdmin importando el archivo `database/database.sql`)*.

Para más detalles, consulta [INSTRUCCIONES_XAMPP.md](./INSTRUCCIONES_XAMPP.md).

### 3. Compilar y Ejecutar la Aplicación

```bash
# Compilar y ejecutar pruebas
./gradlew build

# Iniciar la aplicación
./gradlew bootRun
```
*También puedes ejecutar el jar directamente:*
```bash
java -jar build/libs/viajes-farsheen-agy-1.0.0.jar
```

La aplicación arrancará en el puerto **`8080`**.

---

## 📖 Documentación Interactiva (Swagger UI)

Con la aplicación en ejecución, ingresa desde tu navegador a:
* **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* **OpenAPI Docs (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📮 Colección de Postman

Se incluye el archivo listo para importar en Postman o Insomnia:
* **Ruta:** [`postman_collection.json`](./postman_collection.json)

Contiene todas las solicitudes organizadas por carpetas (Destinos, Clientes, Viajes, Reservas, Swagger), cubriendo casos exitosos (200, 201, 204) y casos de error (400, 404).

---

## 🏛️ Arquitectura de Paquetes (Layered Architecture)

```text
src/main/java/com/viajes/reservas/
│
├── ViajesReservasApplication.java          <-- Clase Principal (@SpringBootApplication)
│
├── config/
│   └── OpenApiConfig.java                  <-- Configuración de Swagger / OpenAPI
│
├── presentationLayer/
│   ├── controller/
│   │   ├── DestinoController.java          <-- Endpoints /api/v1/destinos
│   │   ├── ViajeController.java            <-- Endpoints /api/v1/viajes (HU1, HU2)
│   │   ├── ClienteController.java          <-- Endpoints /api/v1/clientes
│   │   └── ReservaController.java          <-- Endpoints /api/v1/reservas (HU3, HU4, HU5)
│   └── exception/
│       ├── GlobalExceptionHandler.java     <-- Manejador centralizado (@RestControllerAdvice)
│       ├── ResourceNotFoundException.java  <-- HTTP 404
│       └── BadRequestException.java        <-- HTTP 400
│
├── businessLayer/
│   ├── dto/
│   │   ├── destino/ (DestinoDTO, DestinoCreateDTO, DestinoUpdateDTO)
│   │   ├── viaje/   (ViajeDTO, ViajeCreateDTO, ViajeUpdateDTO)
│   │   ├── cliente/ (ClienteDTO, ClienteCreateDTO, ClienteUpdateDTO)
│   │   └── reserva/ (ReservaDTO, ReservaCreateDTO, ReservaUpdateDTO)
│   └── service/
│       ├── DestinoService.java & impl/DestinoServiceImpl.java
│       ├── ViajeService.java & impl/ViajeServiceImpl.java
│       ├── ClienteService.java & impl/ClienteServiceImpl.java
│       └── ReservaService.java & impl/ReservaServiceImpl.java
│
└── persistenceLayer/
    ├── entity/
    │   ├── DestinoEntity.java
    │   ├── ViajeEntity.java
    │   ├── ClienteEntity.java
    │   └── ReservaEntity.java
    ├── repository/
    │   ├── DestinoRepository.java
    │   ├── ViajeRepository.java
    │   ├── ClienteRepository.java
    │   └── ReservaRepository.java
    ├── mapper/
    │   ├── DestinoMapper.java
    │   ├── ViajeMapper.java
    │   ├── ClienteMapper.java
    │   └── ReservaMapper.java
    └── dao/
        ├── DestinoDAO.java
        ├── ViajeDAO.java
        ├── ClienteDAO.java
        └── ReservaDAO.java
```

---

## ⚡ Historias de Usuario Implementadas

* **HU1: Consultar Viajes Disponibles:** `GET /api/v1/viajes` (200 OK)
* **HU2: Ver Detalles de un Viaje:** `GET /api/v1/viajes/{id}` (200 OK / 404 Not Found)
* **HU3: Realizar una Reserva:** `POST /api/v1/reservas` (201 Created / 400 Bad Request / 404 Not Found)
  - Valida disponibilidad y cupos suficientes.
  - Liquida automáticamente el precio total (`precio * numPersonas`).
  - Descuenta los cupos disponibles y actualiza el estado a `AGOTADO` si llega a 0.
* **HU4: Consultar Reservas del Cliente:** `GET /api/v1/reservas/cliente/{clienteId}` (200 OK / 404 Not Found)
* **HU5: Cancelar una Reserva:** `DELETE /api/v1/reservas/{id}` (204 No Content / 404 Not Found)
  - Reintegra automáticamente los cupos al viaje.
  - Vuelve a colocar el viaje en `DISPONIBLE` si estaba `AGOTADO`.

---

## 🧪 Ejemplos con `curl`

### HU1 - Listar Viajes:
```bash
curl -s http://localhost:8080/api/v1/viajes
```

### HU2 - Ver Detalle de Viaje:
```bash
curl -s http://localhost:8080/api/v1/viajes/1
```

### HU3 - Crear Reserva:
```bash
curl -s -X POST http://localhost:8080/api/v1/reservas \
  -H "Content-Type: application/json" \
  -d '{"clienteId": 1, "viajeId": 1, "numeroPersonas": 2}'
```

### HU4 - Historial de Reservas del Cliente:
```bash
curl -s http://localhost:8080/api/v1/reservas/cliente/1
```

### HU5 - Cancelar Reserva:
```bash
curl -s -i -X DELETE http://localhost:8080/api/v1/reservas/1
```
