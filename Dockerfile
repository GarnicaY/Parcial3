FROM openjdk:17
COPY "./target/parcial-1.jar" "app.jar"
EXPOSE 8035
ENTRYPOINT ["java", "-jar", "app.jar"]