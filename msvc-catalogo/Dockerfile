FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./target/msvc-catalogo.jar .

EXPOSE 8003

CMD ["java", "-Dspring.profiles.active=dev", "-jar", "msvc-catalogo.jar"]