webflux 5:

server: 8080

client: 8081

step 1: build and run 2 applications

step 2: send request by postman

    GET http://localhost:8081/          (blocking)

    GET http://localhost:8081/1         (non-blocking)

    POST http://localhost:8081/
    {
        "id": 1,
        "name": "cat"
    }
    PUT http://localhost:8081/
    {
        "id": 1,
        "name": "dog"
    }

I added security in server -> so *_ POST http://localhost:8081/ _* is not available


![async picture](Async.png)