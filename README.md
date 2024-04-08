# Demo Consultas Medicas


El proyecto consta de un backend en Spring Boot 3.2.4 y un frontend en Angular 17.3.1. 

El backend se encarga de la gestión de usuarios y consultas médicas, mientras que el frontend se encarga 
de la visualización de los datos y la interacción con el usuario.

Los servicios estan completos, estructurados y siguiendo buenas practicas de programación 
como Clean Architecture, Dependency Injection, manejo de contratos (interfaces) DTOs, 
servicios, repositorios, configuraciones y mappers, se expone solo la información
necesaria para el front y se oculta los detalles de implementación.

## Los servicios incluidos son:

- Crear un Doctor
- Actualizar un Doctor
- Eliminar un Doctor
- Obtener un Doctor por ID
- Listar todos los Doctores (con paginación)
- Listar consultas medicas de un Doctor
- Listar pacientes de un Doctor
- Crear consulta medica
- Actualizar consulta medica
- Eliminar consulta medica


- Crear un Paciente
- Actualizar un Paciente
- Eliminar un Paciente
- Listar todos los Pacientes
- Listar consultas medicas de un Paciente


- Subir un archivo al servidor
- Listar archivos subidos al servidor

Al ejecutar el proyecto por ejemplo en el puerto 8080, se puede acceder a la documentación de los servicios en la siguiente URL:

```
http://localhost:8080/swagger-ui/index.html
```

## El estado del frontend:

- Listado de Doctores
- Listado de Pacientes
- Listado de Consultas Medicas por Doctor y Paciente

## Requisitos
- necesitas tener instalado Java 11 o superior y Maven 3.6.3 o superior
- mysql 8.0.26 o superior
- node 18.13.0 o superior

## Configuración


### Static Resources
se puede configurar la ruta de almacenamiento en el archivo `src/main/resources/application.properties`

```
storage.location=c:/carpeta-para-archivos
```

### CORS

Se puede configurar los parámetros de cors en el archivo `src/main/resources/application.properties`

```
cors.path-pattern=/**
cors.allowed-origins=http://localhost:4200
cors.allowed-methods=GET,POST,PUT,DELETE
cors.allowed-headers=*
cors.allow-credentials=true
```

### Base de Datos

Las credenciales de la base de datos se pueden configurar en el archivo `src/main/resources/application.properties`

```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/demo_consultas
spring.datasource.username=root
spring.datasource.password=root
```

# Frontend
El proyecto angular se encuentra en la carpeta `src/main/resources/angular-client`


## Development server
El endpoint de los servicios se puede configurar en el archivo `src/environments/environment.ts`



# Base de Datos
La base de datos puede generarse y poblarse ejecutando el script `src/main/resources/database_scripts.sql`

El esquema de la base de datos es el siguiente:

## Diagrama de Clases

### Tabla: users
| Campo       | Tipo         | Notas          |
|-------------|--------------|----------------|
| id          | BIGINT       | PK, Auto-Incr  |
| type        | VARCHAR(31)  | Not Null       |
| address     | VARCHAR(255) |                |
| birth_date  | DATETIME(6)  |                |
| email       | VARCHAR(255) |                |
| first_name  | VARCHAR(255) |                |
| image       | VARCHAR(255) |                |
| last_name   | VARCHAR(255) |                |
| specialty   | VARCHAR(255) |                |

### Tabla: medical_consultations
| Campo       | Tipo         | Notas                         |
|-------------|--------------|-------------------------------|
| id          | BIGINT       | PK, Auto-Incr                 |
| date        | DATETIME(6)  |                               |
| diagnostic  | VARCHAR(255) |                               |
| treatment   | VARCHAR(255) |                               |
| doctor_id   | BIGINT       | FK -> Users(id)               |
| patient_id  | BIGINT       | FK -> Users(id)               |

### Relaciones
•  `medical_consultations` tiene una relación de muchos a uno con `users` a través de `doctor_id`.

•  `medical_consultations` tiene una relación de muchos a uno con `users` a través de `patient_id`.

•  `users` implementa una estrategia de mapeo de herencia llamada TPH, el campo `type` actúa como el discriminador que determina el tipo de usuario.

•  La tabla `users` implementa una estrategia de mapeo de herencia llamada TPH, donde una única tabla representa toda la jerarquía de clases. El campo `type` actúa como el discriminador que determina el tipo de usuario.

## Jerarquía de Clases
•  `User` (Clase Abstracta)

•  `Patient` (Clase Concreta)

•  `Doctor` (Clase Concreta)

## Explicación de la Estrategia TPH
En la tabla `users`, el campo `type` es el discriminador que identifica si el registro pertenece a un `Patient` o a un `Doctor`. Dependiendo del valor de este campo, se aplican las reglas de negocio correspondientes a cada tipo de usuario. Por ejemplo:

•  Si `type` es 'Patient', entonces el registro representa un paciente.

•  Si `type` es 'Doctor', entonces el registro representa un médico.



