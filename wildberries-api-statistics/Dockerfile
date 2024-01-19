FROM openjdk:8-jdk-alpine
LABEL maintainer="ctac_shamsheev@gmail.com"
EXPOSE 8080
WORKDIR ./
COPY target/*.jar .
ENTRYPOINT ["java","-jar","wildberries-api-statistics-0.0.1-SNAPSHOT.jar"]

#FROM openjdk:8-jdk-alpine as builder
#WORKDIR /app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY ./src ./src
#RUN ./mvnw clean install -DskipTests=true
#
#FROM openjdk:8-jdk-alpine
#LABEL maintainer="ctac_shamsheev@gmail.com"
#EXPOSE 8080
#WORKDIR /app
#COPY --from=builder /app/target/*.jar /app/*.jar
#ENTRYPOINT ["java","-jar","/app/wildberries-api-statistics-0.0.1-SNAPSHOT.jar"]