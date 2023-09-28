# Ejecutar en Desarrollo
1. Clonar el proyecto
2. Crear una base de datos en postgresql con el nombre de ```vaccine_inventory```
3. Dirigirse al archivo ```application.properties``` y modificar los datos de conexión a la base de datos
3. Dirigirse al archivo ```application.properties``` y modificar los datos de relacionados con el JWT token como el secret y el tiempo de expiración
4. Ejecutar el proyecto en su IDE de preferencia
5. Por defecto se esta creando un usurio administrador con las siguientes credenciales:
  *Username: admin
  *Password: 1234
6. Iniciar sesión con las credenciales anteriores en el siguiente endpoint:
```http://localhost:8080/login``` con un POST y el body de esta petición debe ser JSON con la siguiente estructura:
```json
{
  "username": "admin",
  "password": "1234"
}
```
Eso nos devuelve un response en donde vamos a obtener el JWT, el mismo que tiene que ser utilizado en el header(Bearer Token) de las peticiones que se realicen a los endpoints que estan protegidos con el JWT token. Y ya se puede utilizar el resto de endpoints de la API.
# Requisitos
* Java 17
* Postgresql 15
# Documentación
La documentación de la API se encuentra en el siguiente endpoint:
```http://localhost:8080/swagger-ui.html```

# Esquema de Datos
![image](/RetoKruger.png)

# Stack Tecnológico Usado
* Spring Boot 3.0.11
* Spring Security
* Spring Data JPA
* Spring Web
* Spring Validation
* JWT Token
* Postgresql
* Swagger
* Lombok
