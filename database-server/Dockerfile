FROM maven:3.6.3-jdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
RUN cp ./target/*.jar app.jar

FROM openjdk:8-jre-alpine
COPY --from=build /app .
EXPOSE 9500 9550
ENTRYPOINT [ "java", "-jar", "app.jar" ]
