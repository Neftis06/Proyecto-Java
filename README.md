# 🛍️ Retail Service API

Microservicio backend desarrollado con **Spring Boot** para la gestión integral de un sistema de comercio minorista (Retail), implementando buenas prácticas de arquitectura, manejo global de excepciones, persistencia con JPA/Hibernate, documentación interactiva y procesamiento asíncrono en segundo plano.


## 🚀 Tecnologías y Herramientas
* **Java 17+**
* **Spring Boot** (Web, Data JPA, Validation)
* **H2 Database** (Base de datos en memoria)
* **Lombok** (Reducción de código boilerplate)
* **MapStruct** (Mapeo eficiente entre Entidades y DTOs)
* **Springdoc OpenAPI / Swagger UI** (Documentación interactiva)
* **Spring Task (`@Async`)** (Procesamiento asíncrono no bloqueante)


## 📂 Arquitectura del Proyecto

El proyecto sigue una estructura en capas desacoplada:

```text
src/main/java/com/axity/curso/retailservice/
├── controller/       # Endpoints REST (Products, PurchaseOrders)
├── service/          # Lógica de negocio (Síncrona y @Async)
├── repository/       # Interfaces de Spring Data JPA
├── model/            # Entidades JPA (Product, PurchaseOrder)
├── dto/              # Objetos de Transferencia de Datos (DTOs)
├── mapper/           # Interfaces de conversión MapStruct
└── exception/        # Manejador global de errores y excepciones
```

## ✨ Características Principales

**1. CRUD Completo con Borrado Lógico:**

* Operaciones completas para ```Product``` y ```PurchaseOrder```.

*  Soporte para actualización total (```PUT```), actualización parcial (```PATCH```), borrado lógico (```soft delete``` mediante bandera) y borrado físico (```hard delete```).

**2. Database Seeding Automático:**

*  Inicialización de datos de prueba al arrancar la aplicación mediante ```CommandLineRunner```, poblando la base de    datos con registros dinámicos de productos y órdenes.

**3. Manejo Global de Excepciones:**

* Centralizado mediante ```@RestControllerAdvice```.

* Excepciones personalizadas (```ResourceNotFoundException```, etc.) que transforman errores en respuestas HTTP estructuradas (```404```, ```400```, ```409```) con formato JSON limpio (```timestamp```, ```status```, ```error```, ```message```, ```path```).

**4. Procesamiento Asíncrono (```@Async```):**

* Hilos secundarios configurados para delegar tareas pesadas o de auditoría (envío de correos simulados, registros de auditoría de productos y órdenes) sin bloquear el hilo principal de las respuestas HTTP.

**5. Documentación Interactiva (Swagger):**

* Interfaz gráfica integrada para probar todos los endpoints en tiempo real.

## ⚙️ Cómo Ejecutar el Proyecto Localmente

**1. Clonar o abrir el repositorio en tu entorno (VS Code):**

```bash
cd retailservice
```

**2. Compilar y ejecutar usando Maven:**
```bash
mvn clean spring-boot:run
```

**3. Acceder a la aplicación:**

* La API corre en `http://localhost:8080`
* Swagger UI: Explora y prueba los endpoints en: `http://localhost:8080/swagger-ui/index.html`
* Consola H@ (DDB): `http://localhost:8080/h2-console`
   * _JDBC URL:_ `jdbc:h2:mem:retaildb`
   * _User:_ `sa`
   * _Password: (en blanco)_

##  🛡️ Códigos de Respuesta y Manejo de Errores

La API responde estandarizadamente ante fallos de negocio o recursos no encontrados:

```bash
{
  "timestamp": "2026-10-29T01:00:00.000",
  "status": 404,
  "error": "Not Found",
  "message": "Orden no encontrada o eliminada con ID: 999",
  "path": "/api/orders/999"
}
```