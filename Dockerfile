FROM gradle:8.12.1-jdk21-alpine AS build
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
RUN gradle clean bootJar --no-daemon

FROM openjdk:21-jdk-slim AS layer-extractor
WORKDIR application
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM openjdk:21-jdk-slim
WORKDIR application
COPY --from=layer-extractor application/dependencies/ ./
COPY --from=layer-extractor application/snapshot-dependencies/ ./
COPY --from=layer-extractor application/spring-boot-loader/ ./
COPY --from=layer-extractor application/application/ ./
EXPOSE 8081
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]