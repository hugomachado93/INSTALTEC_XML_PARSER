FROM adoptopenjdk/openjdk11:alpine-slim
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ARG GCP_KEY=gcp-key/*.json
COPY ${JAR_FILE} app.jar
COPY ${GCP_KEY} gcp-key.json
ENV GOOGLE_APPLICATION_CREDENTIALS gcp-key.json
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]