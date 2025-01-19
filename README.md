# LiterAlura

![Static Badge](https://img.shields.io/badge/java-orange?style=for-the-badge) 
![Static Badge](https://img.shields.io/badge/base--de--datos-blue?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/'finalizado'-black?style=social)

## Índice

- [Descripción del proyecto](#descripción-del-proyecto)
- [Características de la aplicación y demostración](#características-de-la-aplicación-y-demostración)
- [Acceso al proyecto](#acceso-al-proyecto)
- [Tecnologías utilizadas](#tecnologías-utilizadas)
- [Conclusión](#conclusión)

# Descripción del proyecto

**LiterAlura** es una aplicación de gestión de libros basada en **Java** y **Spring Boot** que permite buscar, registrar y mostrar información sobre libros y autores. La aplicación utiliza JPA para la persistencia de datos y se conecta a una base de datos PostgreSQL. Este proyecto es parte de la formación de Java y Spring Framework de [AluraLATAM](https://www.aluracursos.com).

# Características de la aplicación y demostración

**LiterAlura** permite realizar las siguientes acciones:

- **Buscar libro**: Permite buscar un libro mediante datos de una API y guardarlo en el repositorio. Si el libro ya existe, se muestra un mensaje indicando que el libro ya está en el repositorio.
- **Mostrar libros buscados**: Muestra todos los libros que han sido guardados en el repositorio.
- **Mostrar autores registrados**: Muestra todos los autores que han sido guardados en el repositorio.
- **Mostrar autores registrados según año**: Permite ingresar un año y muestra todos los autores que estaban vivos en ese año.
- **Mostrar libros por idioma**: Permite ingresar un idioma y muestra todos los libros en ese idioma.
- **Mostrar libros por autor**: Permite ingresar el nombre de un autor y muestra todos los libros de ese autor.

A continuación se da un pequeño recorrido a la estructura del proyecto:

- **src/main/java/maru/challenge3/liter**:
  - **model**: Contiene las entidades JPA (`Datos`, `Libro`, `Autor`).
  - **repository**: Contiene los repositorios JPA (`LibroRepository`, `DatosRepository`, `AutorRepository`).
  - **principal**: Contiene la clase `Principal` que maneja la lógica principal de la aplicación.

# Acceso al proyecto

1. Clona el repositorio:

    ```bash
    git clone https://github.com/tu-usuario/literalura.git
    cd literalura
    ```

2. Configura la base de datos en el archivo `application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
    spring.datasource.username=tu-usuario
    spring.datasource.password=tu-contraseña
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3. Compila y ejecuta la aplicación:

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

# Tecnologías utilizadas

1. Lenguaje [Java (Versión 17)](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)
2. Framework [Spring Boot](https://spring.io/projects/spring-boot)
3. Base de datos [PostgreSQL](https://www.postgresql.org/)
4. Librerías utilizadas:
   - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
   - [Hibernate](https://hibernate.org/)
   - [Spring Web](https://spring.io/guides/gs/rest-service/)

# Conclusión

El proyecto LiterAlura representa un esfuerzo significativo en la implementación y aplicación de conocimientos adquiridos en Java y Spring Boot. A través de su desarrollo, se demostró la capacidad de integrar múltiples tecnologías para crear una aplicación funcional y útil que facilita la gestión de libros y autores.

LiterAlura no solo cumple con los objetivos académicos de la formación de Java y Spring Framework de AluraLATAM, sino que también ofrece una herramienta práctica para el usuario final. La colaboración en el grupo y la implementación de buenas prácticas de programación aseguran que el proyecto sea robusto y escalable.