FROM openjdk:11-jre-slim

COPY target/Kadem.jar /app.jar

EXPOSE 8089

CMD ["java", "-jar", "/app.jar"]