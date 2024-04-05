# Encontrar el Máximo Entero con Resto Específico

Este proyecto aborda el problema de encontrar el máximo entero dentro de un rango dado que deja un resto específico cuando se divide por otro número. Por ejemplo, dado x = 5, y = 2, y n = 20, el resultado sería k = 17, ya que 17 es el máximo entero dentro del rango (0 <= k <= 20) que deja un resto de 2 cuando se divide por 5.

## Entradas:
- `x` tipo entero
- `y` tipo entero
- `n` tipo entero

## Restricciones:
- para X, 2 <= X <=10^9
- para Y, 0 <= Y < X
- para N, y <= N <= 10^9

# Solución
Para resolver este problema, se proporciona una función eficiente:

```text
k = n - (n - y) % x
```


## Ejemplo
Para x = 5, y = 2, y n = 20, la solución sería:
```text
k = 20 - (20 - 2) % 5
k = 20 - 18 % 5
k = 20 - 3
k = 17
```



# Arquitectura
El proyecto sigue una arquitectura de puertos y adaptadores, con las siguientes carpetas principales:

## domain (Dominio)
En esta carpeta se define la interfaz funcional _`MaxIntegerWithRemainder`_, la cual tiene tres parámetros: `x`, `y`, y `n`. Se utiliza la anotación _`@FunctionalInterface`_ para asegurar que cumple con SAM (Single Abstract Method).

## application (Aplicación)
Aquí se definen los puertos de entrada y salida. _`FindMaxNumberUseCase`_ es un caso de uso que será utilizado desde la capa de adaptador. Además, en esta carpeta se encuentra la implementación concreta del caso de uso en el subdirectorio service. Aquí es donde se pueden usar puertos de salida, como guardar los datos en una base de datos.

## adapters (Adaptadores)
En esta carpeta se definen los puertos de entrada y salida. _`FindMaxNumberController`_ define los endpoints `GET` y `POST`, así como los DTO (RequestDTO para recibir datos en el endpoint y ResponseDTO para manejar la respuesta). También se encuentra _`ApiExceptionHandler`_, un manejador para validar errores utilizando la anotación _`@RestControllerAdvice`_ de Spring Boot. Para manejar las respuestas de error, se crea la clase _`StandardizedApiExceptionResponse`_, que intenta cumplir con la RFC 7807.


## Test
Este directorio contiene las pruebas para el adapter HTTP y el servicio.


# Ejecución
Para ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio.
2. Ejecuta los comandos mvn clean, mvn install y mvn package.
3. Busca la carpeta target y ejecuta java -jar findernum-0.0.1-SNAPSHOT.jar
4. Accede a http://localhost:8080/swagger-ui/index.html para probar la API.

También puedes ejecutar el proyecto en algún IDE. Asegúrate de configurar el entorno de desarrollo según sea necesario.


# Ejecutar con docker

1. Ejecutar `mvn package`
2. Mover de la carpeta targer el archivo findernum-0.0.1-SNAPSHOT.jar a la carpeta deploy
3. Ejecutar el comando `docker build -t finder_num .` para construir la imagen
4. Ejecutar el comando `docker run --rm -d -p 8080:8080 finder_num` para iniciar un contenedor y mapear el puerto

# Configuración Adicional
Si deseas cambiar la configuración de la aplicación, como el puerto en el que se ejecuta la API, puedes hacerlo en application.yml.










