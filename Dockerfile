FROM adoptopenjdk/openjdk11-openj9:alpine
RUN mkdir /opt/shareclasses
RUN mkdir /opt/app
COPY target/xmlparser-*.jar /opt/app/app.jar
CMD ["java", "-Xmx128m", "-XX:+IdleTuningGcOnIdle", "-Xtune:virtualized", "-Xscmx128m", "-Xscmaxaot100m",    "-jar", "/opt/app/app.jar"]