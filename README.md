# URL Shortener 

Design and implementation URL Shortener using 
- Java and Spring for backend development, 
- React for frontend, 
- Dockerfiles and compose.yml for containerization and running

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

>docker build -t bichelyauhen/url-shortener-docker . 

>docker run -p 8080:8080 bichelyauhen/url-shortener-docker

## Frontend Container

>docker build -t bichelyauhen/url-shortener-react-docker .

## Redis

### Periodic RDB snapshot

- save 60 1 # Save after 60 seconds if at least 1 key changed

