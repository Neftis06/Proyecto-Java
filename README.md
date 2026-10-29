# 🛍️ Retail Service API

Microservicio backend desarrollado con **Spring Boot** para la gestión integral de un sistema de comercio minorista (Retail). Implementa buenas prácticas de arquitectura, manejo de excepciones, procesamiento asíncrono en segundo plano, y está diseñado para ser desplegado en entornos Cloud Native mediante **Docker** y **Kubernetes**.

## 🚀 Tecnologías y Herramientas

* **Java 21** (Compatible con Java 17)

* **Spring Boot** (Web, Data JPA, Validation)

* **H2 Database** (Base de datos embebida con persistencia en volumen)

* **MapStruct & Lombok** (Mapeo eficiente y reducción de código boilerplate)

* **Springdoc OpenAPI** (Documentación interactiva con Swagger UI)

* **Docker & Docker Compose** (Contenerización)

* **Kubernetes** (Orquestación local con Docker Desktop)

## 📂 Arquitectura del Proyecto

El proyecto sigue una estructura en capas y separa la infraestructura del código fuente:

```
retailservice/
├── src/main/java/.../retailservice/
│   ├── controller/       # Endpoints REST
│   ├── service/          # Lógica de negocio (Síncrona y @Async)
│   ├── repository/       # Interfaces Spring Data JPA
│   └── exception/        # Manejador global de errores
├── k8s/                  # Manifiestos de Kubernetes
│   ├── config-secret.yaml # ConfigMaps y Secrets
│   ├── pvc.yaml           # Volumen persistente (PersistentVolumeClaim)
│   ├── deployment.yaml    # Declaración de Pods y réplicas
│   └── service.yaml       # Balanceador de carga
├── Dockerfile            # Construcción de la imagen
├── docker-compose.yml    # Orquestación ágil para entorno local
└── pom.xml

```

## ✨ Características Principales

1. **CRUD Completo y Resiliente**

   * Operaciones para `Product` y `PurchaseOrder` con soporte de *soft delete* y *hard delete*.

2. **Procesamiento Asíncrono (`@Async`)**

   * Tareas secundarias (auditoría, notificaciones) delegadas a hilos independientes para no bloquear el flujo HTTP principal.

3. **Manejo Global de Excepciones**

   * Respuestas estructuradas JSON con códigos `400`, `404`, `409` gestionadas por `@RestControllerAdvice`.

4. **Infraestructura Cloud Native**

   * Despliegue en contenedores, inyección de variables de entorno mediante `ConfigMap`/`Secret`, y persistencia de base de datos a través de volúmenes compartidos.

## ⚙️ Cómo Ejecutar el Proyecto

El microservicio está configurado para operar internamente en el puerto **`8090`**. Puedes levantarlo de 3 maneras distintas:

### Opción 1: Desarrollo Local (Maven)

Ideal para programar y hacer pruebas rápidas.

```
mvn clean spring-boot:run

```

* **Swagger UI:** `http://localhost:8090/swagger-ui/index.html`

* **H2 Console:** `http://localhost:8090/h2-console` *(JDBC: jdbc:h2:mem:testdb)*

### Opción 2: Docker Compose

Ideal para levantar todo el entorno con un solo comando.

```
# Construye la imagen y levanta el contenedor en segundo plano
docker-compose up -d --build

```

* **Swagger UI:** `http://localhost:8090/swagger-ui/index.html`

### Opción 3: Kubernetes (Docker Desktop)

Ideal para simular un entorno de producción (QA/Ops).

```
# 1. Construir la imagen localmente
docker build -t retail-api-local:v1.0 .

# 2. Aplicar los manifiestos en orden
kubectl apply -f k8s/config-secret.yaml
kubectl apply -f k8s/pvc.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

```

* **Swagger UI:** `http://localhost/swagger-ui/index.html` *(El servicio LoadBalancer redirige el puerto externo 80 hacia el puerto 8080 interno del pod).*
