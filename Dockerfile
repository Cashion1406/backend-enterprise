FROM openjdk:17-jdk-alpine
ADD /build/libs/backend-0.0.1-SNAPSHOT.jar backend.jar

ENV TZ=Asia/Ho_Chi_Minh

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]