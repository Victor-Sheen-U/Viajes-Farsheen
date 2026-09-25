# 📋 Informe Completo de Entrega: Sistema de Gestión de Reservas de Viajes (Viajes Farsheen)

**Estado del Proyecto:** ✅ **100% COMPLETADO, COMPILADO Y VALIDADO EN TIEMPO REAL**  
**Directorio del Nuevo Proyecto:** [`/home/victorsheen/Viajes-Farsheen/viajes-farsheen-agy`](file:///home/victorsheen/Viajes-Farsheen/viajes-farsheen-agy)  
**Proyecto de Referencia:** Sin modificaciones en Git ni alteraciones de código.

---

## 📌 1. Resumen de lo Realizado

Se construyó desde cero un proyecto nuevo, robusto y profesional basado estrictamente en la guía del taller, replicando la arquitectura multicapa modular de buenas prácticas solicitada por tu docente:

1. **Estructura Arquitectónica en 4 Capas (Layered Architecture / MVC REST):**
   - **`config`:** Configuración de OpenAPI 3.0 / Swagger UI.
   - **`presentationLayer`:** 4 Controladores REST con documentación exhaustiva (`@Tag`, `@Operation`, `@ApiResponses`) y Manejador Global de Excepciones (`@RestControllerAdvice`).
   - **`businessLayer`:** 12 DTOs (lectura, creación y actualización) con validaciones Jakarta (`@NotBlank`, `@Min`, `@DecimalMin`, etc.), 4 interfaces de servicio y sus implementaciones con lógica de negocio transaccional.
   - **`persistenceLayer`:** 4 Entidades JPA relacionales con Lombok, 4 Repositorios Spring Data JPA, 4 Mappers automáticos MapStruct y 4 DAOs intermedios de orquestación.

2. **Base de Datos XAMPP (MySQL / MariaDB):**
   - Se detectó que el servicio MariaDB de XAMPP en tu entorno corre sobre el puerto **`3307`** (con socket `/opt/lampp/var/mysql/mysql.sock`).
   - Se creó la base de datos `viajes_farsheen_db`.
   - Se generó el script SQL completo con integridad referencial (`ON DELETE RESTRICT ON UPDATE CASCADE`) y semillas de datos en [`database/database.sql`](file:///home/victorsheen/Viajes-Farsheen/viajes-farsheen-agy/database/database.sql).
   - Se ejecutó el script directamente sobre XAMPP y se poblaron destinos, viajes, clientes y reservas iniciales.

3. **Pruebas y Verificación Funcional:**
   - Compilación exitosa con Gradle 9 y Java 21 (`BUILD SUCCESSFUL`).
   - Ejecución de la aplicación con Tomcat en puerto `8080` conectada a XAMPP MySQL.
   - Verificación de las 5 Historias de Usuario con `curl` obteniendo exactamente los códigos HTTP requeridos (**200**, **201**, **204**, **400**, **404**).

4. **Entregables Adicionales:**
   - Colección completa de Postman lista para importar: [`postman_collection.json`](file:///home/victorsheen/Viajes-Farsheen/viajes-farsheen-agy/postman_collection.json).
   - Guía paso a paso de XAMPP: [`INSTRUCCIONES_XAMPP.md`](file:///home/victorsheen/Viajes-Farsheen/viajes-farsheen-agy/INSTRUCCIONES_XAMPP.md).
   - Documento técnico y justificación arquitectónica del taller: [`DOCUMENTACION_TECNICA_TALLER.md`](file:///home/victorsheen/Viajes-Farsheen/viajes-farsheen-agy/DOCUMENTACION_TECNICA_TALLER.md).
   - Manual general del proyecto: [`README.md`](file:///home/victorsheen/Viajes-Farsheen/viajes-farsheen-agy/README.md).

---

## 🗂️ 2. Estructura de Archivos del Proyecto

```text
viajes-farsheen-agy/
├── build.gradle                                            <-- Configuración Spring Boot 3.3.4, Java 21, MapStruct, Lombok, OpenAPI
├── settings.gradle
├── gradlew & gradlew.bat
├── postman_collection.json                                 <-- Colección lista para Postman / Insomnia
├── README.md                                               <-- Documentación principal
├── INSTRUCCIONES_XAMPP.md                                  <-- Manual detallado de base de datos
├── DOCUMENTACION_TECNICA_TALLER.md                         <-- Memoria técnica del taller
├── database/
│   └── database.sql                                        <-- Script DDL + DML completo para XAMPP
└── src/
    ├── main/
    │   ├── resources/
    │   │   ├── application.properties                      <-- Configurado para MySQL XAMPP (puerto 3307)
    │   │   └── application-h2.properties                   <-- Perfil opcional H2 en memoria
    │   └── java/com/viajes/reservas/
    │       ├── ViajesReservasApplication.java
    │       ├── config/
    │       │   └── OpenApiConfig.java
    │       ├── presentationLayer/
    │       │   ├── controller/
    │       │   │   ├── DestinoController.java              <-- /api/v1/destinos
    │       │   │   ├── ViajeController.java                <-- /api/v1/viajes (HU1, HU2)
    │       │   │   ├── ClienteController.java              <-- /api/v1/clientes
    │       │   │   └── ReservaController.java              <-- /api/v1/reservas (HU3, HU4, HU5)
    │       │   └── exception/
    │       │       ├── GlobalExceptionHandler.java         <-- Respuestas 400, 404, 500 estandarizadas
    │       │       ├── ResourceNotFoundException.java      <-- HTTP 404
    │       │       └── BadRequestException.java            <-- HTTP 400
    │       ├── businessLayer/
    │       │   ├── dto/
    │       │   │   ├── destino/ (DestinoDTO, DestinoCreateDTO, DestinoUpdateDTO)
    │       │   │   ├── viaje/   (ViajeDTO, ViajeCreateDTO, ViajeUpdateDTO)
    │       │   │   ├── cliente/ (ClienteDTO, ClienteCreateDTO, ClienteUpdateDTO)
    │       │   │   └── reserva/ (ReservaDTO, ReservaCreateDTO, ReservaUpdateDTO)
    │       │   └── service/
    │       │       ├── DestinoService.java & impl/DestinoServiceImpl.java
    │       │       ├── ViajeService.java & impl/ViajeServiceImpl.java
    │       │       ├── ClienteService.java & impl/ClienteServiceImpl.java
    │       │       └── ReservaService.java & impl/ReservaServiceImpl.java
    │       └── persistenceLayer/
    │           ├── entity/
    │           │   ├── DestinoEntity.java                  <-- Tabla destinos
    │           │   ├── ViajeEntity.java                    <-- Tabla viajes (@ManyToOne con Destino)
    │           │   ├── ClienteEntity.java                  <-- Tabla clientes
    │           │   └── ReservaEntity.java                  <-- Tabla reservas (@ManyToOne con Cliente y Viaje)
    │           ├── repository/
    │           │   ├── DestinoRepository.java
    │           │   ├── ViajeRepository.java
    │           │   ├── ClienteRepository.java
    │           │   └── ReservaRepository.java
    │           ├── mapper/
    │           │   ├── DestinoMapper.java                  <-- MapStruct Entity <-> DTO
    │           │   ├── ViajeMapper.java
    │           │   ├── ClienteMapper.java
    │           │   └── ReservaMapper.java
    │           └── dao/
    │               ├── DestinoDAO.java                     <-- Orquestación Repository + Mapper
    │               ├── ViajeDAO.java
    │               ├── ClienteDAO.java
    │               └── ReservaDAO.java
    └── test/java/com/viajes/reservas/
        └── ViajesReservasApplicationTests.java
```

---

## 🎯 3. Cobertura de las Historias de Usuario

| Historia de Usuario | Endpoint | Método | Códigos HTTP Validados | Comportamiento |
| :--- | :--- | :--- | :--- | :--- |
| **HU1: Consultar Viajes Disponibles** | `/api/v1/viajes` | `GET` | **`200 OK`** | Lista todos los viajes enriquecidos con nombre del destino. |
| **HU2: Ver Detalles de un Viaje** | `/api/v1/viajes/{id}` | `GET` | **`200 OK`** / **`404 Not Found`** | Retorna el detalle del viaje o error controlado 404 si no existe. |
| **HU3: Realizar una Reserva** | `/api/v1/reservas` | `POST` | **`201 Created`** / **`400 Bad Request`** / **`404 Not Found`** | Valida cupos y existencia, calcula el precio total multiplicando por personas, descuenta los cupos del viaje y guarda la reserva. |
| **HU4: Consultar Reservas del Cliente** | `/api/v1/reservas/cliente/{clienteId}` | `GET` | **`200 OK`** / **`404 Not Found`** | Retorna el historial de reservas de un cliente con detalles del viaje. |
| **HU5: Cancelar una Reserva** | `/api/v1/reservas/{id}` | `DELETE` | **`204 No Content`** / **`404 Not Found`** | Cancela la reserva, devuelve los cupos al viaje asociado y responde sin contenido. |

---

## 🌐 4. Acceso y URLs de Prueba

* **Swagger UI Interactivo:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* **Especificación OpenAPI (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
* **API Base:** `http://localhost:8080/api/v1`
