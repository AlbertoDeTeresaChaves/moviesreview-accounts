# Accounts Microservice
> Microservicio encargado de centralizar el ciclo de vida y la información base de los usuarios dentro del ecosistema del sistema. Todo ello dentro de una arquitectura por Capas por su facil aprendizaje

## Implementaciones
  * **Spring boot Actuator**
  * **SpringDoc OpenAPI**
  * **Spring Boot DevTools (Solo sera para entorno de desarrollo)**
  * **Lombok**
  * **Spring Data JPA**
  * **Validation**
  * **PostgreSQL Driver**
---

## Flujo del funcionamiento
<img width="1462" height="699" alt="image" src="https://github.com/user-attachments/assets/19d6b830-1eed-410c-8a5d-5b9f52427f25" />

1. **Peticion HTTP GET:** El cliente realiza una peticion `HTTP GET` para ver la lista de cuentas de usuario que hay en la pagina 0
2. **Controller:** El Controller recibe la peticion y le redirige la logica de negocio al `Accounts Service`
3. **Service → Repository:** Al recibirlo realiza su logica y llama a `AccountsRepository.findAll(pageable)` para obtener un Page<Accounts>
4. **Repository → Database:** Spring Data JPA ejecuta un `SELECT * FROM accounts` sobre PostgreSQL
5. **Service → Mapper:** El Service usa `AccountsMapper` para convertir cada `Account` en `AccountsResponseDto`
6. **Service → Controller:** El Service devuelve un Page<AccountsResponseDto>
7. **Controller → Cliente:** El `AccountsController` devuelve la respuesta en un JSON con un codigo **200 OK**  

---

## Uso de OpenAPI/Swagger
> Gracias a la documentacion de OpenAPI y el refuerzo de las herramientas de Swagger tenemos un vistazo en detalle sobre los endpoints, parametros, respuestas, modelos, etc.
> 
> Para ello solo debemos escribir en el navegador `IP DE ACCOUNTS:PORT/swagger-ui.html`
<img width="1852" height="913" alt="image" src="https://github.com/user-attachments/assets/63aae484-c45d-444d-836a-256f068849ab" />


---

## Contenerización con Docker & Cloud Native Builds
En este entorno, cada microservicio debe ser agnóstico a la máquina donde se ejecuta. Para ello recurrimos a **Docker**, abstrayendo el código, entorno de ejecución y dependencias en imágenes ligeras.

Para optimizar la generación de imágenes y evitar mantener archivos `Dockerfile` redundantes, delegué la responsabilidad a <a href="https://cloud.google.com/blog/products/application-development/introducing-jib-build-java-docker-images-better">**Google Jib**</a>. Herramienta que compila de forma nativa imágenes optimizadas para Java directamente hacia el daemon de Docker sin necesidad de configuraciones complejas.

Basta con incluir el plugin en el `pom.xml` y ejecutar:

```bash
mvn compile jib:dockerBuild
```
