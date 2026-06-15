# PokeTrade - Backend

## 📌 Descripción del proyecto
PokeTrade es un sistema backend desarrollado con Spring Boot bajo una arquitectura de microservicios, que permite gestionar la compra y venta de cartas Pokémon. El sistema funciona como un marketplace donde los usuarios pueden registrarse, publicar cartas en venta y realizar compras de cartas publicadas por otros usuarios.

## 👨‍💻 Integrantes
- Kevin Carazas
- Brian Huenten
- Kevin Aguilera

## 🧩 Microservicios implementados

| Microservicio | Descripción | Puerto local |
|---|---|---|
| `apiGateway` | Punto de entrada único que enruta las peticiones hacia los microservicios correspondientes | 8090 |
| `usuario-services` | Gestión de usuarios: registro, consulta, actualización y eliminación | 8081 |
| `PokeTrade` | Gestión de cartas, publicaciones y compras | 8080 |

## ⚙️ Funcionalidades implementadas

### 👤 Usuario
- Registrar usuario
- Obtener una lista de los usuarios
- Buscar al usuario por el id
- Actualizar el usuario
- Eliminar el usuario

### 🃏 Cartas
- Listar cartas
- Registrar cartas
- Buscar carta por el id
- Eliminar carta por el id

### 🗒️ Publicaciones
- Crear publicaciones
- Listar publicaciones
- Eliminar una publicación por el id

### 🛒 Compras
- Comprar cartas
- Listar cartas compradas

### ⚠️ Manejo de errores
- Manejo de validaciones con `@Valid`
- Manejo global de excepciones
- Control de errores con códigos HTTP apropiados (400, 404, 409, 503)

## 🌐 Rutas principales del API Gateway

El Gateway expone un único punto de entrada y redirige las peticiones a los microservicios correspondientes:

| Ruta del Gateway | Microservicio destino | Ejemplo |
|---|---|---|
| `/usuarios/**` | `usuario-services` | `/usuarios/api/v1/usuario` → `usuario-services/api/v1/usuario` |
| `/poketrade/**` | `PokeTrade` | `/poketrade/api/v1/carta` → `PokeTrade/api/v1/carta` |


## 📑 Documentación Swagger

### Local
- Usuario Services: `http://localhost:8081/swagger-ui/index.html`
- PokeTrade: `http://localhost:8080/swagger-ui/index.html`

### Remoto (Railway)
- Usuario Services: `https://usuario-services-production.up.railway.app/doc/swagger-ui/index.html#/`
- PokeTrade: `https://poketrade-production.up.railway.app/doc/swagger-ui/index.html#/`

## ▶️ Ejecución local

### 1️⃣ Clonar el repositorio
```
git clone https://github.com/kecarazas/PokeTrade.git
```
### 2️⃣ Configurar base de datos
Crear una base de datos en MySQL:
```
CREATE DATABASE PokeTrade;
```
### 3️⃣ Ejecutar migraciones
El proyecto usa Flyway, por lo que las tablas y datos se crearán automáticamente al iniciar la aplicación.

### 4️⃣ Ejecutar cada microservicio
Desde tu IDE (IntelliJ / VS Code), ejecuta cada microservicio por separado con el perfil `dev`:
```
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
Orden recomendado de inicio: `usuario-services` → `PokeTrade` → `apiGateway`.

### 5️⃣ Probar la API

Puedes probar los endpoints directamente contra cada microservicio o a través del Gateway.

**Directo (sin Gateway):**
POST http://localhost:8080/api/v1/usuario

**A través del Gateway:**
POST http://localhost:8090/usuarios/api/v1/usuario

#### Ejemplos de uso

**Crear usuario**

POST http://localhost:8080/api/v1/usuario
```
{
"username": "Ghost",
"nombre": "mario",
"apellido": "dominguez",
"email": "mario@gmail.com",
"password": "mario123"
}
```
**Listar cartas**

GET http://localhost:8080/api/v1/carta

Muestra una lista con todas las cartas disponibles.

**Listar publicaciones**

GET http://localhost:8080/api/v1/publicacion

Muestra las cartas que están actualmente en venta.

**Comprar una carta**

POST http://localhost:8080/api/v1/compra
```
{
"cantidad": 60,
"username": "TIENDA",
"publicacionId": 1
}
```
**Listar compras realizadas**

GET http://localhost:8080/api/v1/compra

Muestra las cartas que se han comprado.

## ☁️ Ejecución remota (Railway)

El proyecto está desplegado en Railway, con los tres microservicios corriendo de forma independiente:

- https://usuario-services-production.up.railway.app
- https://poketrade-production.up.railway.app
- https://apigateway-production-19ef.up.railway.app

Las peticiones a producción deben hacerse a través del Gateway, por ejemplo:
```
GET https://apigateway-production-19ef.up.railway.app/poketrade/api/v1/carta
GET https://apigateway-production-19ef.up.railway.app/usuarios/api/v1/usuario
```
