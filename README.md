# TIM: Aplicación de gestión de facturas

## TIM SQL CONSOLE

```
https://127.0.0.1:9000/tim-console
```

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
    - IntelliJ IDEA
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
mvn -Dtest=CustomerIT test
mvn -Dtest=CustomerIT,EmployeeIT test
```

### Ejecución de un caso de uso concreto

```
mvn -Dtest=CustomerIT#testCustomerCreation test
```

## Generar informe de test unitarios

### Se generan los ficheros .xml de los tests unitarios
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

## Generar informe de test de integración

La ejecución de los tests de integración ejecutan antes los tests unitarios.

### Se generan los ficheros .xml de los tests de integración
```
mvn integration-test | mvn verify
```

Si solo se quiere ejecutar los test de integración: 

```
mvn failsafe:integration-test
```

### Se convierten los .xml en .html sin CSS ni imágenes

```
mvn surefire-report:failsafe-report-only
```

## Generar informes de test unitarios y de integración
```
mvn clean verify surefire-report:report-only surefire-report:failsafe-report-only site -DgenerateReports=false
```

## Entregable

```
mvnw clean package
```
Fichero jar disponible en: /target

```
java -jar .\target\tim-0.0.1-SNAPSHOT.jar
```

## Runtime personalizado
```
jlink --compress=2 --no-man-pages --no-header-files --module-path "${path}\jdk-17.0.1\jmods" --add-modules java.base,java.logging,java.sql,java.desktop,java.management,java.naming,jdk.unsupported --output ./tim-runtime
```

## Dockerize
```
#elimina todas las imágenes que no se esten usando
docker image prune -a 
#eliminar imágenes <none>
docker rmi $(docker images dangling=true -q)
docker images --quiet --filter=dangling=true | xargs --no-run-if-empty docker rmi

#crear imagen tim:1.0
docker build -t tim:1.0 .

#ejecutar imagen tim:1.0. Crear contenedor
docker run -d -p 9000:9000 -t tim:1.0
#acceder http://localhost:9000

#detener contenedor
docker ps
docker stop <CONTAINER ID>
```
