## How to run
Please use compose in root folder

## Build
>./gradlew run
>./gradlew build
>./gradlew build --scan

## Container
>docker build -t bichelyauhen/url-shortener-docker . 
   OR >docker build --build-arg JAR_FILE=build/libs/url-shortener-0.0.1-SNAPSHOT.jar -t bichelyauhen/url-shortener-docker .
>docker run -p 8081:8080 bichelyauhen/url-shortener-docker
