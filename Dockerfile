#FROM adoptopenjdk/openjdk11:alpine-jre
FROM openjdk:17-alpine

ARG JAR_FILE=target/tim-1.0.0.jar
ARG DATA=data/timdb.mv.db

# cd /opt/app
WORKDIR /opt/app

# cp target/tim-1.0.0 /opt/app/app.jar
COPY ${JAR_FILE} app.jar
COPY ${DATA} /opt/app/data/timdb.mv.db

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]