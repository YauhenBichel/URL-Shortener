# URL Shortener 

## How to run
> docker compose up
> docker compose up --watch //for development 
> docker compose up -d AND docker compose stop

## compose.yml
Defines 2 services: 
    - backend with Java Spring RESTful service
    - frontend with React app
Uses images, built from Dockerfiles in backend and frontend folders

## Backend Container

>docker build -t bichelyauhen/url-shortener-docker . OR >docker build --build-arg JAR_FILE=build/libs/url-shortener-0.0.1-SNAPSHOT.jar -t bichelyauhen/url-shortener-docker .

>docker run -p 8080:8080 bichelyauhen/url-shortener-docker

## Frontend Container

>docker build -t bichelyauhen/url-shortener-react-docker .

>docker run -p 3000:3000 bichelyauhen/url-shortener-react-docker