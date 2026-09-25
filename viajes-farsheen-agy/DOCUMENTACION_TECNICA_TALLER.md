# 📘 Documentación Técnica del Taller: Sistema de Gestión de Reservas de Viajes

> **Materia / Taller:** Construcción de API REST con Java y Spring Boot  
> **Proyecto:** Viajes Farsheen (`viajes-farsheen-agy`)  
> **Tecnologías:** Java 21, Spring Boot 3.3.4, Spring Data JPA, Hibernate, MapStruct 1.6.2, OpenAPI Swagger 2.6.0, MySQL (XAMPP), Lombok  
> **Patrón Arquitectónico:** Arquitectura Multicapa (Layered Architecture / MVC adaptado a REST)  

---

## 📑 1. Introducción y Visión General del Proyecto

El sistema **Viajes Farsheen** es una API RESTful empresarial diseñada para gestionar el ciclo de vida completo de ofertas turísticas y reservas. Permite a los administradores gestionar destinos y paquetes turísticos con control de cupos en tiempo real, y a los clientes explorar el catálogo, consultar disponibilidad, formalizar reservas y cancelarlas liberando automáticamente los cupos.

---

## 🏛️ 2. Enfoque Arquitectónico y Justificación

### 2.1 Arquitectura Seleccionada: Arquitectura en Capas (Layered Architecture / MVC para REST)

Para este proyecto se implementó una **Arquitectura en 4 Capas Claras** con desacoplamiento estricto de responsabilidades:

```
┌─────────────────────────────────────────────────────────────┐
│                    Capas del Sistema                        │
├──────────────────────────┬──────────────────────────────────┤
│ Capa de Presentación     │ Controllers (@RestController)    │
│ (presentationLayer)      │ Exception Handler (@Advice)      │
├──────────────────────────┼──────────────────────────────────┤
│ Capa de Negocio          │ DTOs (CreateDTO, UpdateDTO, DTO) │
│ (businessLayer)          │ Services & Implementations       │
├──────────────────────────┼──────────────────────────────────┤
│ Capa de Persistencia     │ DAOs Intermedios                 │
│ (persistenceLayer)       │ Mappers (MapStruct)              │
│                          │ Repositories (JpaRepository)     │
│                          │ Entidades JPA (@Entity)          │
├──────────────────────────┼──────────────────────────────────┤
│ Capa de Configuración    │ OpenApiConfig                    │
│ (config)                 │                                  │
└──────────────────────────┴──────────────────────────────────┘
```

### 2.2 Comparación: Layered Architecture vs. Domain-Driven Design (DDD)

* **Layered Architecture (Seleccionada):**
  - **Ventajas:** Alta cohesión, separación intuitiva de responsabilidades (Presentación $\rightarrow$ Negocio $\rightarrow$ Persistencia), muy adecuada para APIs REST centradas en operaciones CRUD transaccionales y de negocio ágil.
  - **Uso en el proyecto:** Permite que los controladores solo se comuniquen con la capa de servicio usando DTOs, evitando filtrar entidades a la capa web. Los DAOs aíslan al servicio del acceso directo a la base de datos y encapsulan la transformación con MapStruct.
* **Domain-Driven Design (DDD):**
  - **Características:** Centrado en modelos de dominio ricos (Entities con lógica de negocio encapsulada, Value Objects, Aggregates y Bounded Contexts).
  - **Razón de selección de Capas sobre DDD:** El taller persigue validar el flujo REST, ORM JPA, mapeo compilado con MapStruct y manejo HTTP transparente. Una arquitectura en capas proporciona la máxima claridad didáctica y mantenibilidad sin la complejidad accidental de agregar agregados y eventos de dominio complejos.

---

## 🧩 3. Modelado de las 4 Entidades

```
┌──────────────┐         1:N          ┌──────────────┐
│   Destino    │ ───────────────────< │    Viaje     │
└──────────────┘                      └──────┬───────┘
                                             │ 1:N
                                             ▼
┌──────────────┐         1:N          ┌──────────────┐
│   Cliente    │ ───────────────────< │   Reserva    │
└──────────────┘                      └──────────────┘
```

1. **`DestinoEntity` (`destinos`):**
   - Atributos: `id`, `nombre`, `pais`, `ciudad`, `descripcion`, `clima`.
   - Propósito: Representa los lugares geográficos y atractivos turísticos.
2. **`ViajeEntity` (`viajes`):**
   - Atributos: `id`, `titulo`, `descripcion`, `precio`, `duracionDias`, `fechaSalida`, `fechaLlegada`, `cuposDisponibles`, `estado`, `destino` (`@ManyToOne`).
   - Propósito: Paquetes y salidas turísticas con control de cupos.
3. **`ClienteEntity` (`clientes`):**
   - Atributos: `id`, `nombre`, `apellido`, `email`, `telefono`, `documentoIdentidad`.
   - Propósito: Usuarios que consultan y realizan reservas con unicidad en email y documento.
4. **`ReservaEntity` (`reservas`):**
   - Atributos: `id`, `fechaReserva`, `numeroPersonas`, `precioTotal`, `estado`, `cliente` (`@ManyToOne`), `viaje` (`@ManyToOne`).
   - Propósito: Transacción de reserva con liquidación automática del precio total.

---

## 🎯 4. Cumplimiento de Historias de Usuario (HU1 a HU5)

### HU1: Consultar Viajes Disponibles
- **Endpoint:** `GET /api/v1/viajes`
- **Código HTTP:** `200 OK`
- **Lógica:** Retorna la lista de todos los viajes registrados con su destino enriquecido (`destinoNombre`).

### HU2: Ver Detalles de un Viaje
- **Endpoint:** `GET /api/v1/viajes/{id}`
- **Códigos HTTP:**
  - `200 OK`: Si el viaje existe.
  - `404 Not Found`: Si el ID no existe (`ResourceNotFoundException`).

### HU3: Realizar una Reserva
- **Endpoint:** `POST /api/v1/reservas`
- **Payload:**
  ```json
  {
    "clienteId": 1,
    "viajeId": 1,
    "numeroPersonas": 2
  }
  ```
- **Códigos HTTP y Validaciones:**
  - `201 Created`: Si cliente y viaje existen y hay cupos suficientes. Se calculan `precioTotal = viaje.precio * numeroPersonas`, se descuentan los cupos del viaje y se asigna estado `CONFIRMADA`. Si los cupos llegan a 0, el viaje pasa a `AGOTADO`.
  - `400 Bad Request`: Si el viaje no tiene cupos suficientes o no está disponible.
  - `404 Not Found`: Si el cliente o el viaje no existen en el sistema.

### HU4: Consultar Reservas del Cliente
- **Endpoint:** `GET /api/v1/reservas/cliente/{clienteId}`
- **Códigos HTTP:**
  - `200 OK`: Retorna el historial de reservas asociadas a ese cliente.
  - `404 Not Found`: Si el cliente no existe.

### HU5: Cancelar una Reserva
- **Endpoint:** `DELETE /api/v1/reservas/{id}`
- **Códigos HTTP y Lógica:**
  - `204 No Content`: Cancela la reserva y reintegra los cupos reservados de regreso al viaje asociado. Si el viaje estaba `AGOTADO`, pasa de nuevo a `DISPONIBLE`.
  - `404 Not Found`: Si la reserva no existe.

---

## 🛡️ 5. Manejo Centralizado de Excepciones

Mediante `@RestControllerAdvice` en `GlobalExceptionHandler`:
- `ResourceNotFoundException` $\rightarrow$ `404 Not Found`
- `BadRequestException` $\rightarrow$ `400 Bad Request`
- `MethodArgumentNotValidException` $\rightarrow$ `400 Bad Request` (con detalle campo por campo de validaciones Jakarta `@NotBlank`, `@Min`, etc.)
- `Exception` genérica $\rightarrow$ `500 Internal Server Error`

Estructura estándar de error JSON:
```json
{
  "timestamp": "2026-09-25T11:06:23.186",
  "status": 404,
  "error": "Recurso no encontrado",
  "message": "Viaje no encontrado con ID: 999"
}
```

---

## 📖 6. Documentación OpenAPI / Swagger

La especificación Swagger UI se genera automáticamente y está accesible en:
- Interfaz interactiva: `http://localhost:8080/swagger-ui.html`
- JSON OpenAPI: `http://localhost:8080/v3/api-docs`

Cada controlador cuenta con anotaciones:
- `@Tag` clasificando las entidades.
- `@Operation` resumiendo cada endpoint.
- `@ApiResponses` con los códigos de estado `200`, `201`, `204`, `400`, `404`, `500`.

---

## 🧪 7. Pruebas Funcionales Realizadas y Resultados

Todas las pruebas fueron ejecutadas sobre la aplicación activa conectada a la base de datos MySQL en XAMPP:

| Prueba | Método | Endpoint | Entrada / Condición | Código Esperado | Código Obtenido | Resultado |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **HU1** | GET | `/api/v1/viajes` | Catálogo general | 200 OK | 200 OK | ✅ EXITOSO |
| **HU2** | GET | `/api/v1/viajes/1` | ID existente | 200 OK | 200 OK | ✅ EXITOSO |
| **HU2 (Error)** | GET | `/api/v1/viajes/999` | ID inexistente | 404 Not Found | 404 Not Found | ✅ EXITOSO |
| **HU3** | POST | `/api/v1/reservas` | `{"clienteId":1,"viajeId":1,"numeroPersonas":2}` | 201 Created | 201 Created | ✅ EXITOSO |
| **HU3 (Error)** | POST | `/api/v1/reservas` | `numeroPersonas: 999` (sin cupos) | 400 Bad Request | 400 Bad Request | ✅ EXITOSO |
| **HU4** | GET | `/api/v1/reservas/cliente/1` | Cliente existente | 200 OK | 200 OK | ✅ EXITOSO |
| **HU4 (Error)** | GET | `/api/v1/reservas/cliente/999` | Cliente inexistente | 404 Not Found | 404 Not Found | ✅ EXITOSO |
| **HU5** | DELETE | `/api/v1/reservas/3` | Cancelar reserva y devolver cupos | 204 No Content | 204 No Content | ✅ EXITOSO |
| **HU5 (Error)** | DELETE | `/api/v1/reservas/999` | Reserva inexistente | 404 Not Found | 404 Not Found | ✅ EXITOSO |
