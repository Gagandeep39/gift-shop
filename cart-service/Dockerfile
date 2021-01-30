FROM maven:3.6.3-jdk-8 AS build
WORKDIR /app
RUN mvn clean package -DskipTests
ADD target/*.jar app.jar

FROM openjdk:8-jre-alpine
COPY --from=build /app .
EXPOSE 9300
ENTRYPOINT [ "java", "-jar", "app.jar" ]
