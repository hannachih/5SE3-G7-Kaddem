

FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/Kadem.jar /app/

EXPOSE 8089

CMD ["java", "-jar", "Kadem.jar"]