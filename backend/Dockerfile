FROM openjdk:17-jdk-alpine
MAINTAINER yauhen.bichel@gmail.com
RUN addgroup -S url-shortener && adduser -S backend -G url-shortener
USER backend:url-shortener
ARG JAR_FILE=build/libs/url-shortener-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
