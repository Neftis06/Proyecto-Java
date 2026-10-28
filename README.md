# 🛒 Retail Order Service

Microservicio backend centralizado diseñado para el sector del comercio minorista (retail). Este sistema gestiona de manera eficiente el catálogo de productos y procesa pedidos de compra de forma transaccional, equilibrando operaciones sincrónicas (críticas) y tareas asincrónicas en segundo plano (notificaciones, auditorías).

## 🚀 Arquitectura y Tecnologías

El proyecto está construido bajo una **Arquitectura por Capas** (Layered Architecture) siguiendo los principios SOLID y patrones de código limpio, garantizando un bajo acoplamiento mediante inyección de dependencias y el uso intensivo de DTOs (`records`).

*   **Lenguaje:** Java 21 (LTS)
*   **Framework Core:** Spring Boot 3.x
*   **Exposición REST:** Spring Web
*   **Persistencia:** Spring Data JPA / Hibernate
*   **Base de Datos:** H2 Database (En memoria, perfil de desarrollo)
*   **Mapeo de Datos:** MapStruct
*   **Boilerplate:** Lombok
*   **Validaciones:** Jakarta Bean Validation
*   **Gestor de Dependencias:** Maven

## 📋 Características Principales

1.  **Catálogo de Productos:** CRUD completo para gestionar inventario, precios y SKUs. Implementa *Soft Delete* (borrado lógico) para preservar la integridad de datos históricos.
2.  **Procesamiento de Pedidos:** Gestión transaccional de órdenes de compra con asociación relacional 1:N (Pedido-Ítems) y N:1 (Ítem-Producto).
3.  **Flujos Sincrónicos vs Asincrónicos:** 
    *   *Sincrónico:* Verificación de inventario, cálculo de totales, creación de orden.
    *   *Asincrónico:* Eventos no bloqueantes como notificaciones de compra y registros de auditoría de almacén.
4.  **Manejo Global de Excepciones:** Intercepción centralizada (`@RestControllerAdvice`) que transforma excepciones de dominio (`StockInsuficienteException`, `RecursoNoEncontradoException`) en respuestas HTTP estandarizadas y semánticas (RFC 7807 / Problem Detail).

## 📂 Estructura del Proyecto

El código está modularizado en los siguientes paquetes principales dentro de `src/main/java/com/axity/curso/retailservice`:

*   `/controller`: Endpoints REST y exposición de la API.
*   `/dto`: Objetos inmutables de transferencia de datos (`records`) para Request y Response.
*   `/exception`: Clases de error de negocio y manejador global de excepciones.
*   `/model`: Entidades JPA de dominio (`Product`, `PurchaseOrder`, `OrderItem`).
*   `/repository`: Interfaces de Spring Data JPA.
*   `/service`: Lógica de negocio (interfaces y sus implementaciones en `/impl`), con separación de flujos sincrónicos y asincrónicos.

## 🛠️ Instalación y Ejecución Local

### Prerrequisitos
*   [JDK 21](https://adoptium.net/) instalado.
*   No es necesario instalar Maven, el proyecto incluye el *Maven Wrapper* (`mvnw`).

### Pasos para iniciar

1. Clona este repositorio:
   ```bash
   git clone <url-del-repositorio>
   cd retailservice
   ```

2. Compila e inicia la aplicación usando el wrapper de Maven:
   ```bash
   # En Linux/macOS
   ./mvnw spring-boot:run

   # En Windows
   mvnw.cmd spring-boot:run
   ```

3. El servicio se levantará por defecto en `http://localhost:8080`.

## 🗄️ Base de Datos H2 (Consola Web)

El proyecto utiliza una base de datos en memoria que se recrea en cada ejecución. Puedes visualizar las tablas y hacer consultas SQL directamente desde tu navegador.

*   **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
*   **JDBC URL:** `jdbc:h2:mem:retaildb`
*   **Usuario:** `sa`
*   **Contraseña:** *(dejar en blanco)*

## 🧪 Pruebas (Testing)

El ecosistema cuenta con pruebas unitarias y de integración que validan los flujos críticos. Para ejecutar la suite de pruebas:

```bash
./mvnw test
```