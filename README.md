# TIM: Aplicación de gestión de facturas

## Juego de iconos

```
https://fontawesome.com/v5.9/icons?d=gallery&p=2
```

## Diseño software

    - Arquitectura hexagonal
    - Domain-Driven Development (DDD)
    - Test-Driven Development (TDD)   

## Herramientas y tecnologías usadas

    - OpenJDK 17
    - Maven
    - Spring Boot    
    - Spring Framework
    - Spring Data JPA    
    - Thymeleaf
    - Bootstrap
    - Project Lombok
    - Eclipse
    - H2 Database
    
## Ejecución entorno desarrollo
```
mvn spring-boot:run
```


## Tests

### Ejecución de todos los test

```
mvn test

```

### Ejecución de un conjunto de casos de usos

```
mvn -Dtest=CustomerTests test
mvn -Dtest=CustomerTests,EmployeeTests test

```

### Ejecución de un caso de uso concreto

```
mvn -Dtest=CustomerTests#testCustomerCreation test


```

## Generar informe de testing

### Se generan los ficheros .xml de los tests
```
mvn test
```

### Se convierten los .xml en .html sin CSS ni imágenes

```
mvn surefire-report:report-only
```

### Genera los css y imágenes del informe

```
mvn site -DgenerateReports=false
```

## Entregable

```
mvnw clean package
```
Fichero jar disponible en: /target

```
java -jar .\target\tim-0.0.1-SNAPSHOT.jar
```
