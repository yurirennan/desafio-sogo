FROM openjdk:8

WORKDIR /app/challenge/sogo

COPY /target/*.jar /app/challenge/sogo/sogo.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/challenge/sogo/sogo.jar"]