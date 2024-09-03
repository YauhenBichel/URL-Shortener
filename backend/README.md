## How to run
Please use compose in root folder

## Build
>./gradlew run
>./gradlew build
>./gradlew build --scan

## Container
>docker build -t bichelyauhen/url-shortener-webserver . 
   OR >docker build --build-arg JAR_FILE=build/libs/url-shortener-0.0.1-SNAPSHOT.jar -t bichelyauhen/url-shortener-webserver .
>docker run -p 8081:8080 bichelyauhen/url-shortener-webserver

## Redis
>docker pull redis
>docker run --name some-redis -d redis
