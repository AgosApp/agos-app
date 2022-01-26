FROM openjdk:15
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} docker-image-agos.jar
ENTRYPOINT ["java","-jar","docker-image-agos.jar"]
