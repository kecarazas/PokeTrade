# PokeTrade - Backend 
## 📌 Descripción del proyecto <br>
PokeTrade es una aplicacion desarrollada con Spring Boot que permite gestionar la compra y venta de cartas Pokémon. <br>
El sistema funciona como un marketplace donde los usuarios pueden registrarse, publicar cartas y realizar compras. <br>
## 👨‍💻 Integrantes <br>
- Kevin Carazas
- Brian Huenten
- Kevin Aguilera
## ⚙️ Funcionalidades implementadas <br>
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
- eliminar carta por el id
### 🗒️ Publicaciones 
- Crear publicaciones
- Listar publicaciones
- Eliminar una publicacion por el id
### 🛒 Comprar
- Comprar cartas
- listar cartas compradas
### ⚠️ Manejo de errores
- Manejo de validaciones con @Valid
- Manejo global de excepciones
- Control de errores
## ▶️ Pasos para ejecutar el proyecto
### 1️⃣ Clonar el repositorio
git clone https://github.com/tu-usuario/PokeTrade.git
### 2️⃣ Configurar base de datos
Crear una base de datos en MySQL: <br>
- CREATE DATABASE PokeTrade;
### 3️⃣ Ejecutar migraciones
El proyecto usa Flyway, por lo que las tablas y datos se crearán automáticamente al iniciar la aplicación. <br>
### 4️⃣ Ejecutar la aplicación
Desde tu IDE (IntelliJ / VS Code):
- mvn spring-boot:run
### 5️⃣ Probar la API
Puedes usar Postman o similar. <br>
Ejemplo endpoint: <br>
POST http://localhost:8080/api/v1/usuario
### 6️⃣ En Postman
POST http://localhost:8080/api/v1/usuario <br>
{ <br>
"username": "Ghost", <br>
"nombre":"mario", <br>
"apellido":"dominguez", <br>
"email": "mario@gmail.com", <br>
"password":"mario123" <br>
} <br>
JSON para crear al usuario <br>
---
GET http://localhost:8080/api/v1/carta <br>
nos muestra una lista con todas las cartas que hay 
---
GET http://localhost:8080/api/v1/publicacion <br>
nos muestra si hay una carta en venta
---
POST http://localhost:8080/api/v1/compra <br>
{ <br>
"cantidad":60, <br>
"username":"TIENDA", <br>
"publicacionId":1 <br>
} <br>
JSON para comprar una carta
--- 
GET http://localhost:8080/api/v1/compra <br>
Nos muestra la carta que hemos comprado