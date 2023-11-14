

FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/khaddem-4.0.jar /app/

EXPOSE 8089

CMD ["java", "-jar", "khaddem-4.0.jar"]