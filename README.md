# Pizzería App

Una aplicación para gestionar pedidos y menús de una pizzería, desarrollada con Spring Boot y SQL.

---

## Descripción

Pizzería App es una solución backend que permite:

- Administrar un menú de pizzas.
- Gestionar pedidos de clientes.
- Consultar datos en tiempo real mediante una API REST.

El proyecto está diseñado como base para una aplicación de gestión de pizzerías, con un enfoque modular y ampliable.

---

## Tecnologías Utilizadas

- **Backend**: Java 17 con Spring Boot (Spring Data JPA, Spring Security).
- **Base de Datos**: PostgreSQL.
- **Cliente de API**: Postman.
- **Gestor de Dependencias**: Maven.

---

## Características Principales

- CRUD para la gestión de pizzas (crear, leer, actualizar y eliminar).
- Gestión de pedidos con información de cliente y estado del pedido.
- Autenticación y autorización básica con Spring Security.
- API REST documentada para consumir los servicios.

---

## Requisitos Previos

Para ejecutar este proyecto, necesitas:

- **Java 17 o superior**.
- **Maven** para gestionar dependencias.
- **MySQL** configurado.
- **Postman** (opcional, para probar la API).

---

## Instalación y Ejecución

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/tu-usuario/pizzeria-app.git
   cd pizzeria-app
   ```

2. **Configurar la base de datos**:

   - Crea una base de datos en PostgreSQL.
   - Edita el archivo `application.properties` en `src/main/resources` con los detalles de tu base de datos:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_base_datos
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_password
     ```

3. **Compilar y ejecutar la aplicación**:

   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la API**:

   - Por defecto, la API estará disponible en `http://localhost:8080`.

---

## Endpoints Principales

### Gestión de Pizzas

- **GET** `/pizzas`: Obtiene la lista de pizzas.
- **POST** `/pizzas`: Crea una nueva pizza.
  ```json
  {
    "nombre": "Pepperoni",
    "precio": 10.99
  }
  ```
- **PUT** `/pizzas/{id}`: Actualiza una pizza existente.
- **DELETE** `/pizzas/{id}`: Elimina una pizza.

### Gestión de Pedidos

- **GET** `/pedidos`: Obtiene la lista de pedidos.
- **POST** `/pedidos`: Crea un nuevo pedido.
  ```json
  {
    "cliente": "Juan Perez",
    "direccion": "Calle Principal 123",
    "pizzas": [
      {
        "id": 1,
        "cantidad": 2
      }
    ]
  }
  ```

---

## Pruebas

Si implementaste pruebas, ejecuta:

```bash
mvn test
```

---

## Mejoras Futuras

- Agregar una interfaz gráfica para clientes y administradores.
- Integración con un sistema de pagos.
- Implementación de notificaciones en tiempo real (por ejemplo, usando WebSocket).
- Soporte para múltiples sucursales de la pizzería.

---

## Autor

**Tu Nombre**\
[https://github.com/OravlaYor](https://github.com/OravlaYor) | [https://www.linkedin.com/in/royalvarodr/](https://www.linkedin.com/in/royalvarodr/)

---

##

