webflux 5:

server: 8080
client: 8081

step 1: build and run 2 applications
step 2: send request by postman
    GET http://localhost:8081/
    GET http://localhost:8081/1
    POST http://localhost:8081/
    PUT http://localhost:8081/

I added security in server -> so *_ GET http://localhost:8081/ _* is not available