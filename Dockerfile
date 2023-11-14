FROM maven as build
WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:11.0
WORKDIR /app
COPY --from=build /app/target/Kadem.jar /app/

EXPOSE 8089

CMD ["java", "-jar", "Kadem.jar"]